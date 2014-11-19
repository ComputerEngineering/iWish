package iWish_Activity;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYSeries;
import com.androidplot.xy.XYStepMode;
import com.progect.iwish.R;

import iWish_Bluetooth.BluetoothLeService;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Shader;
import android.os.Bundle;
import android.view.View;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.LinearGradient;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Matrix;
import android.graphics.RectF;

import com.androidplot.xy.BoundaryMode;

public class GraphicActivity extends Activity{

	private ArrayList<Double> lista = new ArrayList<Double>();
	private double X;
	private double Y;
	private boolean mConnected = false;
	private XYPlot plot;
	private Shader WHITE_SHADER = new LinearGradient(1, 1, 1, 1, Color.WHITE, Color.WHITE, Shader.TileMode.REPEAT);
	private boolean styleOn;
	//private String totBeats;
	private ArrayList<String> totBeats2;
	private boolean finitoBeatPassati;
	private int cominciaDa;
	private int y;
	private boolean mStarting;

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
				//heartRate.setText("--");

				//qualcosa da fare se si disconnette

			} 
			else if (BluetoothLeService.ACTION_GATT_SERVICES_DISCOVERED.equals(action)) {
				// Show all the supported services and characteristics on the user interface.
				//non più utilizzato non metto a video tutti i servizi
			} 
			else if (BluetoothLeService.ACTION_DATA_AVAILABLE.equals(action)) {
				//qui viene inviato alla TextView il dato aggiornato dei battiti cardiaci che ora si chiama heartRate
				if(mStarting){
					
					displayDataGraphic(intent.getStringExtra(BluetoothLeService.EXTRA_DATA));
				}	
			}
		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.graphic);
		//mHandler = new Handler();
		//mStarting = false;
		final Intent intent = getIntent();
		//totBeats = intent.getStringExtra("beats");
		totBeats2 = (ArrayList<String>) intent.getSerializableExtra("beats");
		
		mStarting = intent.getBooleanExtra("run", true);
		finitoBeatPassati = false;
		cominciaDa = (int) intent.getIntExtra("tempo", 0);
		y = 0;//(int) intent.getIntExtra("tempo", 0);
		if(totBeats2!=null){
			getArrayListBeats(totBeats2);
		}
		//mDeviceAddress = intent.getStringExtra(EXTRAS_DEVICE_ADDRESS);
		//mActivities = (Activities) intent.getSerializableExtra("a");

		// Start heartRate.
		plot = (XYPlot) findViewById(R.id.graphic);

		styleOn = true;
		//distanza tra righe verticali
		//plot.setDomainStep(XYStepMode.INCREMENT_BY_VAL, 10);
		//distanza tra righe orizzontali
		plot.setRangeStep(XYStepMode.INCREMENT_BY_VAL, 20);


		//colore sfondo grafico 
		plot.getGraphWidget().getGridBackgroundPaint().setShader(WHITE_SHADER);
		//colore line verticali
		plot.getGraphWidget().getDomainGridLinePaint().setColor(Color.rgb(255, 255, 255));
		//colore linee orizzontali
		plot.getGraphWidget().getRangeGridLinePaint().setColor(Color.rgb(0, 0, 0));
		plot.getGraphWidget().getRangeGridLinePaint().setPathEffect(new DashPathEffect(new float[]{3, 3}, 1));
		//valori numerici della y 
		plot.setRangeBoundaries(30, 210, BoundaryMode.FIXED);
		//valori numerici della x
		if(cominciaDa<60){
			plot.setDomainBoundaries(0, 60, BoundaryMode.FIXED);
		}
		else{
			plot.setDomainBoundaries(cominciaDa-60, cominciaDa, BoundaryMode.FIXED);
		}
		

		// scala dei numeri del grafico in decimale
		plot.setRangeValueFormat(new DecimalFormat("0"));
		plot.setDomainValueFormat(new DecimalFormat("0"));

		//linee verticali
		plot.setDomainStep(XYStepMode.SUBDIVIDE, 7);		
		//plot.setDomainStep(XYStepMode.INCREMENT_BY_VAL, 30);		
		// plot.setRangeStep(XYStepMode.INCREMENT_BY_VAL, 60);


		// qui c'era il for di riempimento lista


		//passo la lista
		XYSeries series = new SimpleXYSeries(lista, SimpleXYSeries.ArrayFormat.XY_VALS_INTERLEAVED,"Segnale");
		//colore linea grafico impostazioni
		LineAndPointFormatter seriesFormat = new LineAndPointFormatter(Color.rgb(0, 0, 0),0x000000, 0x000000, null);
		plot.clear();
		plot.addSeries(series, seriesFormat);

	}

	@Override
	protected void onResume() {
		super.onResume();
		registerReceiver(mGattUpdateReceiver, makeGattUpdateIntentFilter());
	}

	@Override
	protected void onPause() {
		super.onPause();
		unregisterReceiver(mGattUpdateReceiver);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

	}

	private static IntentFilter makeGattUpdateIntentFilter() {
		final IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction(BluetoothLeService.ACTION_GATT_CONNECTED);
		intentFilter.addAction(BluetoothLeService.ACTION_GATT_DISCONNECTED);
		intentFilter.addAction(BluetoothLeService.ACTION_GATT_SERVICES_DISCOVERED);
		intentFilter.addAction(BluetoothLeService.ACTION_DATA_AVAILABLE);
		return intentFilter;
	}


	private void displayDataGraphic(String dataGraphic){
		//qui devo aggiornare il grafico
		if(finitoBeatPassati){
			try{


				if(cominciaDa>60){
					y++;
					X = X + 1;
					lista.remove(0);
					lista.add(X);
					Y = (double) Integer.parseInt(dataGraphic);
					lista.remove(0);
					lista.add(Y);
					plot.setDomainBoundaries(0+y, 60+y, BoundaryMode.FIXED);

				}
				else{
					X = X + 1;
					lista.add(X);
					Y = (double) Integer.parseInt(dataGraphic);
					lista.add(Y);
				}

				//passo la lista
				XYSeries series = new SimpleXYSeries(lista, SimpleXYSeries.ArrayFormat.XY_VALS_INTERLEAVED,"segnale");
				//colore linea grafico impostazioni
				LineAndPointFormatter seriesFormat = new LineAndPointFormatter(Color.rgb(0, 0, 0),0x000000, 0x000000, null);
				plot.clear();
				plot.addSeries(series, seriesFormat);
				plot.redraw();
				cominciaDa++;
			} 
			catch (Exception e){ 
				e.printStackTrace();
			}

		}
	}

	private void getArrayListBeats(ArrayList<String> totBeat){

		String[] beatStr = totBeat.toArray(new String[totBeat.size()]);

		try{	
			for(int i=0; i<beatStr.length; i++){
				//cominciaDa++;
				if(i==0 && cominciaDa>60){
					//i = beatStr.length - 60 -1;
					X = cominciaDa - 60 -2; //- 50 -2;
					y = cominciaDa - 60 -1;// beatStr.length - 60 -1;
				}

				if(i==0 && cominciaDa<60){
					X = 0;
				}
				else{
					X = X + 1;
				}
				lista.add(X);
				Y = (double) Integer.parseInt(beatStr[i]);
				lista.add(Y);
			}

		} 
		catch (Exception e){ 
			e.printStackTrace();
		}

		finitoBeatPassati = true;

	}

	public void onGraphStyleToggle(View v) {

		RectF rect = plot.getGraphWidget().getGridRect();
		BitmapShader myShader = new BitmapShader(
				Bitmap.createScaledBitmap(
						BitmapFactory.decodeResource(
								getResources(),
								R.drawable.graph_background),
								1,
								(int) rect.height(),
								false),
								Shader.TileMode.REPEAT,
								Shader.TileMode.REPEAT);
		Matrix m = new Matrix();
		m.setTranslate(rect.left, rect.top);
		myShader.setLocalMatrix(m);
		if (styleOn){
			plot.getGraphWidget().getGridBackgroundPaint().setShader(
					myShader);
			styleOn = false;
		}
		else{
			plot.getGraphWidget().getGridBackgroundPaint().setShader(WHITE_SHADER);
			styleOn = true;
		}
		plot.redraw();

	}

}
