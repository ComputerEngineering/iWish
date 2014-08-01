package iWish_Activity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.progect.iwish.R;
import com.viewpagerindicator.CirclePageIndicator;


public class TutorialActivity extends Activity {
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.tutorial);

    ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
    ImagePagerAdapter adapter = new ImagePagerAdapter();
    viewPager.setAdapter(adapter);
    
    
    //Bind the title indicator to the adapter
    CirclePageIndicator titleIndicator = (CirclePageIndicator)findViewById(R.id.indicator);
    titleIndicator.setViewPager(viewPager);
   
    
  }

  private class ImagePagerAdapter extends PagerAdapter {
	    private int[] mImages = new int[] {
	    		R.drawable.p1,
	    		R.drawable.p2,
	    		R.drawable.p3,
	    		R.drawable.p4,
	    		R.drawable.p5,
	    		R.drawable.p6,
	    		R.drawable.p7,
	    		R.drawable.p8,	
	    		R.drawable.p9
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
      Context context = TutorialActivity.this;
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
