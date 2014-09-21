package iWish_Activity;

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

/**Antonio* --- Miki */

public class WantToDoActivity extends Activity {
	  @Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.want_to_do);
   
	    
	  ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager_avatar);
	  ImagePagerAdapter adapter = new ImagePagerAdapter();
	  viewPager.setAdapter(adapter);
	  
	  
	  //Bind the title indicator to the adapter
	  CirclePageIndicator titleIndicator = (CirclePageIndicator)findViewById(R.id.indicator);
	  titleIndicator.setViewPager(viewPager);
	  
		ImageButton chiudi = (ImageButton)findViewById(R.id.chiudi_verde2);
		chiudi.setOnClickListener(new OnClickListener() {
		
			@Override
			public void onClick(View v) {
				startActivity(new Intent(WantToDoActivity.this,GoalActivity.class ));
			}
		});   
	  
	}

	private class ImagePagerAdapter extends PagerAdapter {
		    private int[] mImages = new int[] {
		    		R.drawable.run,
		    		R.drawable.ride,
		    		R.drawable.rise,
		    		R.drawable.swim
		 
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




