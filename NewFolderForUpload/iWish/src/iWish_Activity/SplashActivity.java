package iWish_Activity;
/** Raffaella*/

import com.progect.iwish.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SplashActivity extends Activity{
	protected static final int TIMER_RUNTIME = 5000; // in ms --> 10s
	protected boolean mbActive;
	protected ProgressBar mProgressBar;
	protected TextView loading;
	protected TextView completato;
	private int percentuale;

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		setUpViews();

		completato.setText("  loading...");
		loading.setText("0");

		final Thread timerThread = new Thread() {
			@Override
			public void run() {
				mbActive = true;
				try {
					int waited = 0;
					while(mbActive && (waited < TIMER_RUNTIME)) {
						sleep(250);
						if(mbActive) {
							waited += 250;
							updateProgress(waited);

							runOnUiThread(new Runnable() { 
								@Override
								public void run() {
									updateText();
								}

							});
						}
					}
					sleep(500);
				} catch(InterruptedException e) {
					// do nothing
				} finally {
					onContinue();
				}
			}
		};
		timerThread.start();
	}
	private void setUpViews() {
		mProgressBar = (ProgressBar)findViewById(R.id.progress_bar);
		loading = (TextView) findViewById(R.id.loading);
		completato = (TextView) findViewById(R.id.completato);
	}
	@Override
	public void onDestroy() {
		super.onDestroy();
	}
	public void updateProgress(final int timePassed) {
		if(null != mProgressBar) {
			final int progress = mProgressBar.getMax() * timePassed / TIMER_RUNTIME;// Ignore rounding error here
			mProgressBar.setProgress(progress);
		}
	}
	public void updateText(){
		percentuale = percentuale +5;
		if(percentuale > 96){
			completato.setText("completed loading");
		}
		String bbb =  String.valueOf(percentuale);
		loading.setText(bbb);

	}   
	public void onContinue() {
		// perform any final actions here
		startActivity(new Intent(SplashActivity.this, LoginRegActivity.class ));
		// affinché non si possa più rientrare in splash inseriamo:
		finish();
	}
}