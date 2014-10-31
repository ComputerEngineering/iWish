package iWish_Activity;
/**Antonio* --- Miki */
import iWish_Activities.Activities;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.progect.iwish.R;
import com.viewpagerindicator.CirclePageIndicator;


public class WantToDoActivity extends Activity {
	private Activities mActivities;
	private String RUN = "run";
	private String RIDE = "ride";
	private String GYM = "gym";
	private ViewPager viewPager;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.want_to_do);

		viewPager = (ViewPager) findViewById(R.id.view_pager_to_do);
		ImagePagerAdapter adapter = new ImagePagerAdapter();
		viewPager.setAdapter(adapter);
		mActivities = new Activities();

		//Bind the title indicator to the adapter
		CirclePageIndicator titleIndicator = (CirclePageIndicator)findViewById(R.id.indicator);
		titleIndicator.setViewPager(viewPager);

		final ImageButton fatto = (ImageButton)findViewById(R.id.done);

		fatto.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Stub di metodo generato automaticamente

				typeActivities();
				/** l'activity della scelta dei km viene saltata in caso si sceglie come tipo di allenamento 
				 * Gym visto che in palestra i km non servono */
				if(mActivities.getTipoAttivita().equals(GYM)){
					Intent intent = new Intent(WantToDoActivity.this, StartingActivity.class);
					intent.putExtra("a", mActivities);//aggiungiamo questa nuova informazione nel nostro intent
					fatto.setImageResource(R.drawable.botton_done2);
					startActivity(intent);//facciamo partire l'intent GoalActivity
				}else{
					Intent intent = new Intent(WantToDoActivity.this, GoalActivity.class);
					intent.putExtra("a", mActivities);//aggiungiamo questa nuova informazione nel nostro intent
					fatto.setImageResource(R.drawable.botton_done2);
					startActivity(intent);//facciamo partire l'intent GoalActivity
				}
			}
		});
	}
	private void typeActivities() {
		if(viewPager.getCurrentItem()==0){
			mActivities.setTipoAttivita(RUN);
		}
		if(viewPager.getCurrentItem()==1){
			mActivities.setTipoAttivita(RIDE);
		}
		if(viewPager.getCurrentItem()==2){
			mActivities.setTipoAttivita(GYM);
		}
	}
	private class ImagePagerAdapter extends PagerAdapter {
		private int[] mImages = new int[] {
				R.drawable.to_do_run_ok,
				R.drawable.to_do_ride_ok,
				R.drawable.to_do_rise_ok		 
		};
		@Override
		public int getCount() {
			return mImages.length;
		}
		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == ((ImageView) object);
		}
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			Context context = WantToDoActivity.this;
			ImageView imageView = new ImageView(context);
			imageView.setImageResource(mImages[position]);
			((ViewPager) container).addView(imageView, 0);
			return imageView;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			((ViewPager) container).removeView((ImageView) object);
		}
	}
}




