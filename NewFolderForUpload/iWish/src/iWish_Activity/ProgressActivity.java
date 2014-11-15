package iWish_Activity;
//**Alessandro*//

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import iWish_Activities.Activities;
import iWish_Bluetooth.BluetoothLeService;
import android.widget.ImageButton;
import android.app.Activity;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Chronometer.OnChronometerTickListener;

import com.progect.iwish.R;

//utilizzato prima nel tasto disconect per disconnettersi richiama in BluetoothLeService il disconnect dal server gatt
//mBluetoothLeService.disconnect();

public class ProgressActivity extends Activity{
	private Button heartRate;
	//private ImageButton menu;
	private ImageButton ok;
	private ImageButton stop;
	private Button pause;
	private final static String TAG = ProgressActivity.class.getSimpleName();
	public static final String EXTRAS_DEVICE_NAME = "DEVICE_NAME";
	public static final String EXTRAS_DEVICE_ADDRESS = "DEVICE_ADDRESS";
	private static final long START_HEART = 5000; //Start heart
	private String mDeviceAddress;
	private BluetoothLeService mBluetoothLeService;
	private boolean mConnected = false;
	private BluetoothGattCharacteristic mNotifyCharacteristic;
	private Handler mHandler;
	private Activities mActivities;
	private ImageView going;
	private Chronometer ch;
	private long milliseconds;
	private SimpleDateFormat timeFormat;
	private String hmmss;
	private TextView heartMax;
	private TextView heartMin;
	private TextView heartMed;
	private boolean mStarting;


	// Code to manage Service lifecycle.
	private final ServiceConnection mServiceConnection = new ServiceConnection() {
		@Override
		public void onServiceConnected(ComponentName componentName, IBinder service) {
			mBluetoothLeService = ((BluetoothLeService.LocalBinder) service).getService();
			if (!mBluetoothLeService.initialize()) {
				Log.e(TAG, "Unable to initialize Bluetooth");
				finish();
			}
			// Automatically connects to the device upon successful start-up initialization.
			mBluetoothLeService.connect(mDeviceAddress);
		}
		@Override
		public void onServiceDisconnected(ComponentName componentName) {
			mBluetoothLeService = null;
		}
	};

	// Handles various events fired by the Service.
	// ACTION_GATT_CONNECTED: connected to a GATT server.
	// ACTION_GATT_DISCONNECTED: disconnected from a GATT server.
	// ACTION_GATT_SERVICES_DISCOVERED: discovered GATT services.
	// ACTION_DATA_AVAILABLE: received data from the device.  This can be a result of read
	//                        or notification operations.
	private final BroadcastReceiver mGattUpdateReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			final String action = intent.getAction();
			if (BluetoothLeService.ACTION_GATT_CONNECTED.equals(action)) {
				mConnected = true;
				//posso sostituirlo con qualche avviso di connessione al BLE
				//updateConnectionState(R.string.connected);

			} 
			else if (BluetoothLeService.ACTION_GATT_DISCONNECTED.equals(action)) {
				mConnected = false;
				//posso sostituirlo con qualche avviso di disconnessione al BLE
				//updateConnectionState(R.string.disconnected);

				//aggiornava l'attuale testo dei battiti che sono ora in heartRate
				//mDataField.setText(R.string.no_data);
				heartRate.setText("--");

			} 
			else if (BluetoothLeService.ACTION_GATT_SERVICES_DISCOVERED.equals(action)) {
				// Show all the supported services and characteristics on the user interface.
				//non più utilizzato non metto a video tutti i servizi
			} 
			else if (BluetoothLeService.ACTION_DATA_AVAILABLE.equals(action)) {
				//qui viene inviato alla TextView il dato aggiornato dei battiti cardiaci che ora si chiama heartRate
				displayData(intent.getStringExtra(BluetoothLeService.EXTRA_DATA),intent.getStringExtra(BluetoothLeService.EXTRA_MAX),
						    intent.getStringExtra(BluetoothLeService.EXTRA_MIN),
						    intent.getStringExtra(BluetoothLeService.EXTRA_MED));
			}
		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.progress);
		mHandler = new Handler();
		mStarting = false;
		final Intent intent = getIntent();
		mDeviceAddress = intent.getStringExtra(EXTRAS_DEVICE_ADDRESS);
		mActivities = (Activities) intent.getSerializableExtra("a");
		going = (ImageView)findViewById(R.id.going);
		heartMax =(TextView)findViewById(R.id.heart_max);
		heartMin =(TextView)findViewById(R.id.heart_min);
		heartMed =(TextView)findViewById(R.id.heart_med);
		//menu = (ImageButton)findViewById(R.id.bott_omino);
		ok = (ImageButton)findViewById(R.id.m_lets_start);
		stop = (ImageButton) findViewById(R.id.m_stop);
		pause = (Button) findViewById(R.id.pause);
		stop.setVisibility(View.INVISIBLE);
		pause.setVisibility(View.INVISIBLE);
		heartRate = (Button)findViewById(R.id.heart_rate);
		if(mDeviceAddress != null){
			Intent gattServiceIntent = new Intent(this, BluetoothLeService.class);
			bindService(gattServiceIntent, mServiceConnection, BIND_AUTO_CREATE);
		}
		milliseconds =0;
		ch = (Chronometer) findViewById(R.id.chronometer);
		timeFormat = new SimpleDateFormat("H:mm:ss");
		timeFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		hmmss = timeFormat.format(milliseconds);	
		ch.setText(hmmss);
		setOnClickButton();
		setOnChronometer();
		setImage();

		// Start heartRate.
		mHandler.postDelayed(new Runnable() {
			@Override
			public void run() {
				startHeart();
			}
		}, START_HEART);
	}

	@Override
	protected void onResume() {
		super.onResume();
		registerReceiver(mGattUpdateReceiver, makeGattUpdateIntentFilter());
		if (mBluetoothLeService != null && mDeviceAddress != null) {
			final boolean result = mBluetoothLeService.connect(mDeviceAddress);
			Log.d(TAG, "Connect request result=" + result);
		}

	}

	@Override
	protected void onPause() {
		super.onPause();
		unregisterReceiver(mGattUpdateReceiver);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		unbindService(mServiceConnection);
		mBluetoothLeService = null;
	}

	@Override
	public void onBackPressed() {
		// your code.
		Intent intent3 = new Intent(ProgressActivity.this, ProfileActivity.class );
		//eventuale uso adesso non serve
		//intent3.putExtra("a", mActivities);
		startActivity(intent3);
		//finish();
	}

	private void updateConnectionState(final int resourceId) {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				//textView che non c'è più aggiornata in caso di connessione utile per altre azioni alla connessione
				//mConnectionState.setText(resourceId);
			}
		});
	}

	//aggiorna i battiti cardiaci
	private void displayData(String data, String max, String min, String med) {
		if (data != null) {
			//vecchi dato che aggiornava i battiti adesso heartRate
			//mDataField.setText(data);
			heartRate.setText(data);
			if(mStarting){
			//	MinMaxMed(data);
				heartMax.setText(max);
				heartMin.setText(min);
				heartMed.setText(med);
			}
		}
	}

	private static IntentFilter makeGattUpdateIntentFilter() {
		final IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction(BluetoothLeService.ACTION_GATT_CONNECTED);
		intentFilter.addAction(BluetoothLeService.ACTION_GATT_DISCONNECTED);
		intentFilter.addAction(BluetoothLeService.ACTION_GATT_SERVICES_DISCOVERED);
		intentFilter.addAction(BluetoothLeService.ACTION_DATA_AVAILABLE);
		return intentFilter;
	}

	private void setOnClickButton(){

		/*	menu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//da controllare come ritornare al menu
				//startActivity(new Intent(ProgressActivity.this, ProfileActivity.class ));	
				milliseconds = SystemClock.elapsedRealtime() - ch.getBase();
				ch.stop();

			}
		});*/

		ok.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//da definire cosa fa allo start Let's Start
				if(mBluetoothLeService!=null){
					mBluetoothLeService.setActivitiesAndSession(mActivities);
					mBluetoothLeService.onStart();
				}
				else{
					//gestire il caso senza bluetooth di session e di activities
				}
				mStarting = true;
				ok.setVisibility(View.INVISIBLE);
				stop.setVisibility(View.VISIBLE);
				pause.setVisibility(View.VISIBLE);
				ch.setBase(SystemClock.elapsedRealtime() - milliseconds);
				ch.start();
			}
		});
		
		stop.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//da definire cosa fa stop
				if(mBluetoothLeService!=null){
					mBluetoothLeService.onStop();
				}
				ch.stop();
				pause.setVisibility(View.INVISIBLE);
			}
		});
		
		pause.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//da definire cosa fa pause
				
			}
		});

		heartRate.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(!mStarting){
					// selezione del dispositivo bluetooth
					Intent intent2 = new Intent(ProgressActivity.this, BluetoothActivity.class );
					intent2.putExtra("a", mActivities);
					startActivity(intent2);
				}
			}
		});

	}

	private void setOnChronometer(){

		ch.setOnChronometerTickListener(new OnChronometerTickListener() {
			@Override
			public void onChronometerTick(Chronometer chronometer) {
				long aux = SystemClock.elapsedRealtime() - chronometer.getBase();
				hmmss = timeFormat.format(aux);	
				chronometer.setText(hmmss);
			}
		});

	}

	private void setImage(){
		if(mActivities!=null){
			if(mActivities.getTipoAttivita().equals("run")){
				going.setImageResource(R.drawable.going_run);
			}
			if(mActivities.getTipoAttivita().equals("ride")){
				going.setImageResource(R.drawable.going_ride);
			}
			if(mActivities.getTipoAttivita().equals("gym")){
				going.setImageResource(R.drawable.going_gym);
			}
		}
	}

	private void startHeart() {
		//nuovo if che sostituisce il bottone connect quindi connessione in automatico
		if(mDeviceAddress != null){
			//ritorna il servizio Heart Rate
			BluetoothGattService mBluetoothGattService = mBluetoothLeService.getSupportedGattService();
			//richiede la characteristic Heart Rate Measurement caratteristica dei battiti cardiaci
			final BluetoothGattCharacteristic characteristic = mBluetoothGattService.getCharacteristic(BluetoothLeService.UUID_HEART_RATE_MEASUREMENT);
			final int charaProp = characteristic.getProperties();
			if ((charaProp | BluetoothGattCharacteristic.PROPERTY_READ) > 0) {
				// If there is an active notification on a characteristic, clear
				// it first so it doesn't update the data field on the user interface.
				if (mNotifyCharacteristic != null) {
					mBluetoothLeService.setCharacteristicNotification(mNotifyCharacteristic, false);
					mNotifyCharacteristic = null;
				}
				mBluetoothLeService.readCharacteristic(characteristic);
			}
			if ((charaProp | BluetoothGattCharacteristic.PROPERTY_NOTIFY) > 0) {
				mNotifyCharacteristic = characteristic;
				mBluetoothLeService.setCharacteristicNotification(characteristic, true);
			}
		}

	}

}
