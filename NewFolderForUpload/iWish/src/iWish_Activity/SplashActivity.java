package iWish_Activity;
/** Raffaella*/

import com.progect.iwish.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import java.lang.ref.WeakReference;


public class SplashActivity extends Activity{
	 /** Called when the activity is first created. */
	
	/**
     * The key to use to save the isDone state variable
     */
    private static final String IS_DONE_KEY = "iWish_Activity.key.IS_DONE_KEY";

    /**
     * THe key to use to save the mStartTime state variable
     */
    private static final String START_TIME_KEY = "iWish_Activity.key.START_TIME_KEY";

    /**
     * The minimum interval we have to wait before going ahead
     */
    private static final long MIN_WAIT_INTERVAL = 1500L;

    /**
     * The maximum interval we can wait before going ahead
     */
    private static final long MAX_WAIT_INTERVAL = 3000L;

    /**
     * The What to use into the Handler to go to the next Activity
     */
    private static final int GO_AHEAD_WHAT = 1;

    /**
     * The time we consider as the start
     */
    private long mStartTime = -1L;

    /**
     * This variable is used to prevent the double launch of the next activity
     * or the launch when the current Activity has been closed.
     */
    private boolean mIsDone;
    
    /**
     * This is a static class that doesn't retain the reference to the container class thought
     * the this reference. The reference to the container Activity is make through the
     * WeakReference that is not counted as a valid reference by the GC
     */
    private static class UiHandler extends Handler {

        /**
         * The WeakReference to the Activity that uses it
         */
        private WeakReference<SplashActivity> mActivityRef;

        /**
         * Constructor with the sourceActivity reference
         *
         * @param srcActivity The Activity we need a reference of.
         */
        public UiHandler(final SplashActivity srcActivity) {
            // We just save the reference to the src Activity
            this.mActivityRef = new WeakReference<SplashActivity>(srcActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            // We get the reference to the sourceActivity
            final SplashActivity srcActivity = this.mActivityRef.get();
            
            switch (msg.what) {
                case GO_AHEAD_WHAT:
                    // We calculate le elapsed time to prevent the early launch of the next
                    // Activity
                    long elapsedTime = SystemClock.uptimeMillis() - srcActivity.mStartTime;
                    // We go ahead if not closed. We could use isDestroyed() but is available only
                    // from API Level 17. Then we need to use an instance variable.
                    if (elapsedTime >= MIN_WAIT_INTERVAL &&  !srcActivity.mIsDone) {
                        srcActivity.mIsDone = true;
                        srcActivity.goAhead();
                    }
                    break;
            }
        }

    }
    
    /**
     * This is the Handler we use to manage time interval
     */
    private UiHandler mHandler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        // If present we get the information about the startTime
        if (savedInstanceState != null) {
            this.mStartTime = savedInstanceState.getLong(START_TIME_KEY);
        }
        // We initialize the Handler
        mHandler = new UiHandler(this);
        // We get the reference of the ImageView to manage the touch event
        final ImageView logoImageView = (ImageView) findViewById(R.id.splash_screen);
        logoImageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                long elapsedTime = SystemClock.uptimeMillis() - mStartTime;
                if (elapsedTime >= MIN_WAIT_INTERVAL && !mIsDone) {
                    mIsDone = true;
                    goAhead();
                }
                return false;
            }
        });
    }
    
    @Override
    protected void onStart() {
        super.onStart();
        if (mStartTime == -1L) {
            // We set the time to consider as the start only if not already done
            // in the onStart() method
            mStartTime = SystemClock.uptimeMillis();
        }
        // We set the time for going ahead automatically
        final Message goAheadMessage = mHandler.obtainMessage(GO_AHEAD_WHAT);
        mHandler.sendMessageAtTime(goAheadMessage, mStartTime + MAX_WAIT_INTERVAL);
    }
    
    @Override
    protected void onStop() {
        super.onStop();
        mHandler.removeMessages(GO_AHEAD_WHAT);
    }
    
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // We save the state of the startTime value
        outState.putBoolean(IS_DONE_KEY, mIsDone);
        // We save the state of the isDone value
        outState.putLong(START_TIME_KEY, mStartTime);
    }
    
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // We restore the value of the isDone variable
        this.mIsDone = savedInstanceState.getBoolean(IS_DONE_KEY);
    }
    
    /**
     * Utility method that manages the transition to the FirstAccessActivity
     */
    private void goAhead() {
        // We create the explicit Intent
        final Intent intent = new Intent(this, TutorialActivity.class);
        // Launch the Intent
        startActivity(intent);
        // We finish the current Activity
        finish();
    }
	
}
