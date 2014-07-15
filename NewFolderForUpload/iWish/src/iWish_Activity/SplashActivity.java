package iWish_Activity;


import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

import com.progect.iwish.R;



public class SplashActivity extends Activity{
	private static final int PROGRESS = 0x1;
	private String casa;
	
	private ProgressBar mProgress;
	private int mProgressStatus = 0;
	
	private Handler mHandler = new Handler();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.splash);
		
		mProgress = (ProgressBar) findViewById(R.id.progressBar);
		
		//start lenghy ioeration in a backgroud thread
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(mProgressStatus < 100){
				//	mProgressStatus = doWork();
					
					//update the progress bar
					mHandler.post(new Runnable() {
						
						@Override
						public void run() {
							mProgress.setProgress(mProgressStatus);
						}
					});
				}
			}
		}).start();
	}
}
