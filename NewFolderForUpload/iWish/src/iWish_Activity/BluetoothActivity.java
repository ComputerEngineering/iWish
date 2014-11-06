//**Alessandro*//
package iWish_Activity;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import com.progect.iwish.R;

/**
 * Activity for scanning available Bluetooth LE devices.
 */

public class BluetoothActivity extends Activity{
	public static final String EXTRAS_DEVICE_NAME = "DEVICE_NAME";
	public static final String EXTRAS_DEVICE_ADDRESS = "DEVICE_ADDRESS";
	private LeDeviceListAdapter mLeDeviceListAdapter;
	private BluetoothAdapter mBluetoothAdapter;
	private boolean mScanning;
	private Handler mHandler;
	private static final int REQUEST_ENABLE_BT = 1;
	private static final long SCAN_PERIOD = 5000; //Scanning for 5 seconds.
	private Button scan;
	private Button stop;
	private ProgressBar progress;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bluetooth_device);
		scan = (Button) findViewById(R.id.scanButton);
		stop = (Button) findViewById(R.id.stopButton);
		progress = (ProgressBar) findViewById(R.id.bluetooth_progress);
		mHandler = new Handler();
		// Use this check to determine whether BLE is supported on the device.
		if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
			Toast.makeText(this, "Bluetooth LE is not supported", Toast.LENGTH_SHORT).show();
			finish();
		}
		// Initializes a Bluetooth adapter.  For API level 18 and above, get a reference to
		// BluetoothAdapter through BluetoothManager.
		final BluetoothManager bluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
		mBluetoothAdapter = bluetoothManager.getAdapter();
		// Checks if Bluetooth is supported on the device.
		if (mBluetoothAdapter == null) {
			Toast.makeText(this, "Bluetooth not supported", Toast.LENGTH_SHORT).show();
			finish();
			return;
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		// Ensures Bluetooth is enabled on the device.  If Bluetooth is not currently enabled,
		// fire an intent to display a dialog asking the user to grant permission to enable it.
		if (!mBluetoothAdapter.isEnabled()) {
			Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
			startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
		}

		// Initializes list view adapter.
		mLeDeviceListAdapter = new LeDeviceListAdapter();
		ListView mListView = (ListView) findViewById(R.id.bluetooth_list);
		mListView.setAdapter(mLeDeviceListAdapter);
		final Intent intent = new Intent(this, ProgressActivity.class);
		mListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
			@Override  
			public void onItemClick(AdapterView<?> adattatore, final View componente, int position, long id){  
				final BluetoothDevice device = mLeDeviceListAdapter.getDevice(position);
				if (device == null){
					return;
				}
				intent.putExtra(EXTRAS_DEVICE_NAME, device.getName());
				intent.putExtra(EXTRAS_DEVICE_ADDRESS, device.getAddress());
				if (mScanning) {
					mBluetoothAdapter.stopLeScan(mLeScanCallback);
					mScanning = false;
				}
				startActivity(intent);
			}   
		});
		scanLeDevice(true);
		stop.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				scanLeDevice(false);			
			}
		});
		scan.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(!mScanning){
					mLeDeviceListAdapter.clear();
					scanLeDevice(true);					
				}

			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// User chose not to enable Bluetooth.
		if (requestCode == REQUEST_ENABLE_BT && resultCode == Activity.RESULT_CANCELED) {
			finish();
			return;
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	protected void onPause() {
		super.onPause();
		scanLeDevice(false);
		mLeDeviceListAdapter.clear();
	}

	// called to start the scan of the BLE
	private void scanLeDevice(final boolean enable) {
		if (enable) {
			// Stops scanning after a 5 seconds scan period.
			mHandler.postDelayed(new Runnable() {
				@Override
				public void run() {
					mScanning = false;  
					mBluetoothAdapter.stopLeScan(mLeScanCallback);
					progress.setVisibility(View.INVISIBLE);
					scan.setTextColor(Color.WHITE);
				}
			}, SCAN_PERIOD);
			mScanning = true;
			mBluetoothAdapter.startLeScan(mLeScanCallback);
			progress.setVisibility(View.VISIBLE);
			scan.setTextColor(Color.RED);
		} else {
			mScanning = false;
			mBluetoothAdapter.stopLeScan(mLeScanCallback);
			progress.setVisibility(View.INVISIBLE);
			scan.setTextColor(Color.WHITE);
		}


	}

	// Adapter for holding devices found through scanning.
	private class LeDeviceListAdapter extends BaseAdapter {
		private ArrayList<BluetoothDevice> mLeDevices;
		private LayoutInflater mInflator;
		public LeDeviceListAdapter() {
			super();
			mLeDevices = new ArrayList<BluetoothDevice>();
			mInflator = BluetoothActivity.this.getLayoutInflater();
		}
		public void addDevice(BluetoothDevice device) {
			if(!mLeDevices.contains(device)) {
				mLeDevices.add(device);
			}
		}
		public BluetoothDevice getDevice(int position) {
			return mLeDevices.get(position);
		}
		public void clear() {
			mLeDevices.clear();
		}
		@Override
		public int getCount() {
			return mLeDevices.size();
		}
		@Override
		public Object getItem(int i) {
			return mLeDevices.get(i);
		}
		@Override
		public long getItemId(int i) {
			return i;
		}
		@Override
		public View getView(int i, View view, ViewGroup viewGroup) {
			ViewHolder viewHolder;
			// General ListView optimization code.
			if (view == null) {
				view = mInflator.inflate(R.layout.listitem, null);
				viewHolder = new ViewHolder();
				viewHolder.deviceAddress = (TextView) view.findViewById(R.id.device_address);
				viewHolder.deviceName = (TextView) view.findViewById(R.id.device_name);
				view.setTag(viewHolder);
			} else {
				viewHolder = (ViewHolder) view.getTag();
			}
			BluetoothDevice device = mLeDevices.get(i);
			final String deviceName = device.getName();
			if (deviceName != null && deviceName.length() > 0){
				viewHolder.deviceName.setText(deviceName);
			}
			else{
				viewHolder.deviceName.setText("Unknown Device");
			}
			viewHolder.deviceAddress.setText(device.getAddress());
			return view;
		}
	}

	// Device scan callback.
	private BluetoothAdapter.LeScanCallback mLeScanCallback =
			new BluetoothAdapter.LeScanCallback() {
		@Override
		public void onLeScan(final BluetoothDevice device, int rssi, byte[] scanRecord) {
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					mLeDeviceListAdapter.addDevice(device);
					mLeDeviceListAdapter.notifyDataSetChanged();
				}
			});
		}
	};

	static class ViewHolder {
		TextView deviceName;
		TextView deviceAddress;
	}
}
