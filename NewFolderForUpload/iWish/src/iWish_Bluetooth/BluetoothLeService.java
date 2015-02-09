package iWish_Bluetooth;

import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import iWish_Activities.Activities;
import iWish_Control.ControlActivities;
import iWish_Control.ControlConnection;
import iWish_Control.ControlSession;
import iWish_Session.Session;

import java.util.ArrayList;
import java.util.UUID;
/**
 * Service for managing connection and data communication with a GATT server hosted on a
 * given Bluetooth LE device.
 */
public class BluetoothLeService extends Service {

	//servono per individuare il servizio e la caratteristica del servizio in un dispostitivo bt 
	public static String HEART_RATE_MEASUREMENT =       "00002a37-0000-1000-8000-00805f9b34fb"; // Heart Rate Measurement
	public static String HEART_RATE_SERVICE =           "0000180d-0000-1000-8000-00805f9b34fb"; // Heart Rate Service
	public static String CLIENT_CHARACTERISTIC_CONFIG = "00002902-0000-1000-8000-00805f9b34fb"; // Client Characteristic Config

	private final static String TAG = BluetoothLeService.class.getSimpleName();
	private BluetoothManager mBluetoothManager;
	private BluetoothAdapter mBluetoothAdapter;
	private String mBluetoothDeviceAddress;
	private BluetoothGatt mBluetoothGatt;
	private int mConnectionState = STATE_DISCONNECTED;
	private static final int STATE_DISCONNECTED = 0;
	private static final int STATE_CONNECTING = 1;
	private static final int STATE_CONNECTED = 2;
	public final static String ACTION_GATT_CONNECTED = "iWish_Activity.ACTION_GATT_CONNECTED";
	public final static String ACTION_GATT_DISCONNECTED = "iWish_Activity.ACTION_GATT_DISCONNECTED";
	public final static String ACTION_GATT_SERVICES_DISCOVERED = "iWish_Activity.ACTION_GATT_SERVICES_DISCOVERED";
	public final static String ACTION_DATA_AVAILABLE = "iWish_Activity.ACTION_DATA_AVAILABLE";
	public final static String ACTION_DATA_ALL = "iWish_Activity.ACTION_DATA_ALL";     //lo utilizzo per inviare tutti i dati al grafico da definire
	public final static String EXTRA_DATA = "iWish_Activity.EXTRA_DATA";
	public final static String EXTRA_MAX = "iWish_Activity.EXTRA_MAX";
	public final static String EXTRA_MIN = "iWish_Activity.EXTRA_MIN";
	public final static String EXTRA_MED = "iWish_Activity.EXTRA_MED";
	public final static UUID UUID_HEART_RATE_MEASUREMENT = UUID.fromString(BluetoothLeService.HEART_RATE_MEASUREMENT);
	public final static UUID UUID_HEART_RATE_SERVICE = UUID.fromString(BluetoothLeService.HEART_RATE_SERVICE);

	private Activities mActivities;
	private Session mSession;
	private int sommaMed;
	private int divisoreMedia;
	private int hMin;
	private int hMax;
	private int hMed;
	private boolean mStarting;
	private long tempo;
	private int startDateTime;
	private int stopDateTime;
	private int durataTempo;
	private String totBeats;
	private int tempoInPausa;
	private int inizioPausa;
	//private String totBeatsLocal;
	private int beatsSelect;
	private ArrayList<String> totBeatsLocal;

	// Implements callback methods for GATT events that the app cares about.
	// For example, connection change and services discovered.
	private final BluetoothGattCallback mGattCallback = new BluetoothGattCallback() {

		@Override
		public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
			String intentAction;
			if (newState == BluetoothProfile.STATE_CONNECTED) {
				intentAction = ACTION_GATT_CONNECTED;
				mConnectionState = STATE_CONNECTED;
				broadcastUpdate(intentAction);
				Log.i(TAG, "Connected to GATT server.");
				// Attempts to discover services after successful connection.
				Log.i(TAG, "Attempting to start service discovery:" + mBluetoothGatt.discoverServices());
			} 
			else if (newState == BluetoothProfile.STATE_DISCONNECTED) {
				intentAction = ACTION_GATT_DISCONNECTED;
				mConnectionState = STATE_DISCONNECTED;
				Log.i(TAG, "Disconnected from GATT server.");
				broadcastUpdate(intentAction);
			}
		}

		@Override
		public void onServicesDiscovered(BluetoothGatt gatt, int status) {
			if (status == BluetoothGatt.GATT_SUCCESS) {
				broadcastUpdate(ACTION_GATT_SERVICES_DISCOVERED);
			} else {
				Log.w(TAG, "onServicesDiscovered received: " + status);
			}
		}
		@Override
		public void onCharacteristicRead(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
			if (status == BluetoothGatt.GATT_SUCCESS) {
				broadcastUpdate(ACTION_DATA_AVAILABLE, characteristic);
			}
		}

		@Override
		public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
			broadcastUpdate(ACTION_DATA_AVAILABLE, characteristic);
		}
	};

	private void broadcastUpdate(final String action) {
		final Intent intent = new Intent(action);
		sendBroadcast(intent);
	}

	private void broadcastUpdate(final String action, final BluetoothGattCharacteristic characteristic) {
		final Intent intent = new Intent(action);
		// This is special handling for the Heart Rate Measurement profile.  Data parsing is
		// carried out as per profile specifications:
		// http://developer.bluetooth.org/gatt/characteristics/Pages/CharacteristicViewer.aspx?u=org.bluetooth.characteristic.heart_rate_measurement.xml
		if (UUID_HEART_RATE_MEASUREMENT.equals(characteristic.getUuid())) {
			int flag = characteristic.getProperties();
			int format = -1;
			if ((flag & 0x01) != 0) {
				format = BluetoothGattCharacteristic.FORMAT_UINT16;
				Log.d(TAG, "Heart rate format UINT16.");
			} else {
				format = BluetoothGattCharacteristic.FORMAT_UINT8;
				Log.d(TAG, "Heart rate format UINT8.");
			}
			final int heartRate = characteristic.getIntValue(format, 1);
			Log.d(TAG, String.format("Received heart rate: %d", heartRate));
			//passo il battito attuale
			intent.putExtra(EXTRA_DATA, String.valueOf(heartRate));
			//imposto max min e med

			if(mStarting){
				MinMaxMed(String.valueOf(heartRate));
				if(beatsSelect%5==0){
					setBeatsTot(String.valueOf(heartRate));
				}
				beatsSelect++;
				setBeatsTotLocal(String.valueOf(heartRate));
			}
			intent.putExtra(EXTRA_MAX, ""+hMax);
			intent.putExtra(EXTRA_MIN, ""+hMin);
			intent.putExtra(EXTRA_MED, ""+hMed);

		} else {
			// For all other profiles, writes the data formatted in HEX.
			final byte[] data = characteristic.getValue();
			if (data != null && data.length > 0) {
				final StringBuilder stringBuilder = new StringBuilder(data.length);
				for(byte byteChar : data){
					stringBuilder.append(String.format("%02X ", byteChar));
				}
				intent.putExtra(EXTRA_DATA, new String(data) + "\n" + stringBuilder.toString());
			}
		}
		sendBroadcast(intent);
	}

	public class LocalBinder extends Binder {
		public BluetoothLeService getService() {
			return BluetoothLeService.this;
		}
	}

	@Override
	public IBinder onBind(Intent intent) {
		return mBinder;
	}

	@Override
	public boolean onUnbind(Intent intent) {
		// After using a given device, you should make sure that BluetoothGatt.close() is called
		// such that resources are cleaned up properly.  In this particular example, close() is
		// invoked when the UI is disconnected from the Service.
		close();
		return super.onUnbind(intent);
	}

	private final IBinder mBinder = new LocalBinder();

	/**
	 * Initializes a reference to the local Bluetooth adapter.
	 * @return Return true if the initialization is successful.
	 */
	public boolean initialize() {
		//dati per la media il max e il min
		sommaMed = 0;
		divisoreMedia = 0;
		hMin = 300;
		hMax = 30;
		hMed = 30;
		totBeats = "";
		tempoInPausa =0;
		inizioPausa =0;
		//totBeatsLocal = "";
		mStarting=false;
		beatsSelect=0;
		totBeatsLocal = new ArrayList<String>();

		// For API level 18 and above, get a reference to BluetoothAdapter through
		// BluetoothManager.
		if (mBluetoothManager == null) {
			mBluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
			if (mBluetoothManager == null) {
				Log.e(TAG, "Unable to initialize BluetoothManager.");
				return false;
			}
		}
		mBluetoothAdapter = mBluetoothManager.getAdapter();
		if (mBluetoothAdapter == null) {
			Log.e(TAG, "Unable to obtain a BluetoothAdapter.");
			return false;
		}
		return true;
	}

	/**
	 * Connects to the GATT server hosted on the Bluetooth LE device.
	 *
	 * @param address The device address of the destination device.
	 *
	 * @return Return true if the connection is initiated successfully. The connection result
	 *         is reported asynchronously through the
	 *         {@code BluetoothGattCallback#onConnectionStateChange(android.bluetooth.BluetoothGatt, int, int)}
	 *         callback.
	 */
	public boolean connect(final String address) {
		if (mBluetoothAdapter == null || address == null) {
			Log.w(TAG, "BluetoothAdapter not initialized or unspecified address.");
			return false;
		}
		// Previously connected device.  Try to reconnect.
		if (mBluetoothDeviceAddress != null && address.equals(mBluetoothDeviceAddress) && mBluetoothGatt != null) {
			Log.d(TAG, "Trying to use an existing mBluetoothGatt for connection.");
			if (mBluetoothGatt.connect()) {
				mConnectionState = STATE_CONNECTING;
				return true;
			} else {
				return false;
			}
		}
		final BluetoothDevice device = mBluetoothAdapter.getRemoteDevice(address);
		if (device == null) {
			Log.w(TAG, "Device not found.  Unable to connect.");
			return false;
		}
		// We want to directly connect to the device, so we are setting the autoConnect
		// parameter to false.
		mBluetoothGatt = device.connectGatt(this, false, mGattCallback);
		Log.d(TAG, "Trying to create a new connection.");
		mBluetoothDeviceAddress = address;
		mConnectionState = STATE_CONNECTING;
		return true;
	}

	/**
	 * Disconnects an existing connection or cancel a pending connection. The disconnection result
	 * is reported asynchronously through the
	 * {@code BluetoothGattCallback#onConnectionStateChange(android.bluetooth.BluetoothGatt, int, int)}
	 * callback.
	 */
	public void disconnect() {
		if (mBluetoothAdapter == null || mBluetoothGatt == null) {
			Log.w(TAG, "BluetoothAdapter not initialized");
			return;
		}
		mBluetoothGatt.disconnect();
	}

	/**
	 * After using a given BLE device, the app must call this method to ensure resources are
	 * released properly.
	 */
	public void close() {
		if (mBluetoothGatt == null) {
			return;
		}
		mBluetoothGatt.close();
		mBluetoothGatt = null;
	}

	/**
	 * Request a read on a given {@code BluetoothGattCharacteristic}. The read result is reported
	 * asynchronously through the {@code BluetoothGattCallback#onCharacteristicRead(android.bluetooth.BluetoothGatt, android.bluetooth.BluetoothGattCharacteristic, int)}
	 * callback.
	 *
	 * @param characteristic The characteristic to read from.
	 */
	public void readCharacteristic(BluetoothGattCharacteristic characteristic) {
		if (mBluetoothAdapter == null || mBluetoothGatt == null) {
			Log.w(TAG, "BluetoothAdapter not initialized");
			return;
		}
		mBluetoothGatt.readCharacteristic(characteristic);
	}

	/**
	 * Enables or disables notification on a give characteristic.
	 *
	 * @param characteristic Characteristic to act on.
	 * @param enabled If true, enable notification.  False otherwise.
	 */
	public void setCharacteristicNotification(BluetoothGattCharacteristic characteristic, boolean enabled) {
		if (mBluetoothAdapter == null || mBluetoothGatt == null) {
			Log.w(TAG, "BluetoothAdapter not initialized");
			return;
		}
		mBluetoothGatt.setCharacteristicNotification(characteristic, enabled);
		// This is specific to Heart Rate Measurement.
		if (UUID_HEART_RATE_MEASUREMENT.equals(characteristic.getUuid())) {
			BluetoothGattDescriptor descriptor = characteristic.getDescriptor(UUID.fromString(BluetoothLeService.CLIENT_CHARACTERISTIC_CONFIG));
			descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
			mBluetoothGatt.writeDescriptor(descriptor);
		}
	}

	//ritorna il GattService che si chiama heart rate service e ha characteristic Heart Rate Measurement
	public BluetoothGattService getSupportedGattService(){
		if (mBluetoothGatt == null){
			return null;
		}
		return mBluetoothGatt.getService(UUID_HEART_RATE_SERVICE);
	}

	public void setActivitiesAndSession(Activities attivita){
		mActivities = attivita;
		mSession = new Session();
		mSession.setKeyActivities(mActivities.getKeyActivities());
		mSession.setStartDateActivities(mActivities.getStartDate());
		tempo = System.currentTimeMillis();
		startDateTime = (int) (tempo/1000); 
		mSession.setStartDate(startDateTime);
		
	}

	public void onStart(){
		mStarting = true;
	}

	public void onStop(){
		if(!mStarting){
			tempoInPausa = tempoInPausa + ((int) (System.currentTimeMillis()/1000) - inizioPausa);
		}
		mStarting = false;
		mSession.setBattitiMax(hMax);
		mSession.setBattitiMin(hMin);
		mSession.setBattitiMed(hMed);
		mSession.setBattiti(totBeats);
		long fine = System.currentTimeMillis();
		int fineTime = (int) (fine/1000);
		int durataTempo = fineTime - startDateTime - tempoInPausa;
		mSession.setDurataTempo(durataTempo);
		try {
			Log.i("BluetoothLeService", "PRIMA DI INSERIRE NEL DB SESSION");
			ControlSession.getIstanceControlSession().saveOnDbSession(mSession, getApplicationContext());
			Log.i("BluetoothLeService", "DOPO L'INSERIMENTO NEL DB SESSION");
			ControlConnection.getIstanceControlConnection().onInsertSession(totBeats);
			Log.i("BluetoothLeService", "DOPO L'INSERIMENTO online");
		} catch (Exception e) {
			//Toast.makeText(c ,"errore di salvataggio", Toast.LENGTH_LONG).show();
			Log.i("BluetoothLeService", "errore INSERIMENTO NEL DB SESSION");
			e.printStackTrace();
		}
		Log.i("WeightActivity", "SALVATAGGIO SUL DB ANDATO A BUON FINE");
	}
	
	public void onPause(){
		if(mStarting){
			mStarting = false;
			inizioPausa =(int) (System.currentTimeMillis()/1000);
		}
		else{
			mStarting = true;
			tempoInPausa = tempoInPausa + ((int) (System.currentTimeMillis()/1000) - inizioPausa);
		}
	}

	private void MinMaxMed(String data){
		int dato;

		try {
			dato = Integer.parseInt(data);
			if(dato != 0){
				divisoreMedia++;
				sommaMed += dato;
			}
			if(dato < hMin && dato!=0){
				hMin = dato;
				if(hMax == 30){
					hMax = dato;
				}
			}
			else{
				if(dato > hMax){
					hMax = dato;
				}
			}
			hMed = sommaMed / divisoreMedia;
		} 
		catch (Exception e){ 
			e.printStackTrace();
		}
	}
	
	private void setBeatsTot(String beat){
		if(!beat.equals("0")){
			totBeats = totBeats + beat + ",";
		}
	}
	
	private void setBeatsTotLocal(String beat){
		if(!beat.equals("0")){
			if(totBeatsLocal.size() < 62){
				totBeatsLocal.add(beat);
			}
			else{
				totBeatsLocal.add(beat);
				totBeatsLocal.remove(0);
			}
			//totBeatsLocal = totBeatsLocal + beat + ",";
		}
	}
	
	public ArrayList<String> getBeatsTotLocal(){
		return totBeatsLocal;
	}
	
}