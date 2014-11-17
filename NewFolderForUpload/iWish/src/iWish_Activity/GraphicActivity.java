package iWish_Activity;

import iWish_Bluetooth.BluetoothLeService;
import android.app.Activity;
import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

public class GraphicActivity extends Activity{
	private BluetoothLeService mBluetoothLeService;
	private final static String TAG = GraphicActivity.class.getSimpleName();
	private String mDeviceAddress;
	
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

}
