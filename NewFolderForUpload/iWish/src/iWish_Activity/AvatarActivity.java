package iWish_Activity;
/**Michela*/

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader.TileMode;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.progect.iwish.R;
import com.viewpagerindicator.CirclePageIndicator;


public class AvatarActivity extends Activity {
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.avatar);
/*   
    Button bot1 = (Button)findViewById(R.id.bot_1);
    Button bot2 = (Button)findViewById(R.id.bot_2);
    Button bot3 = (Button)findViewById(R.id.bot_3);   
    private Button[] bottoni = new Button[]{ bot1,bot2, bot3	};
    
  

    ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
    ImagePagerAdapter adapter = new ImagePagerAdapter();
    viewPager.setAdapter(adapter);
  //  viewPager.setBottom(bottoni);
    


   
    
    //Bind the title indicator to the adapter
    CirclePageIndicator titleIndicator = (CirclePageIndicator)findViewById(R.id.indicator);
    titleIndicator.setViewPager(viewPager);
   
    
   */ 
   
  
  Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pinguino);
  Bitmap circleBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);

  BitmapShader shader = new BitmapShader (bitmap,  TileMode.CLAMP, TileMode.CLAMP);
  Paint paint = new Paint();
  paint.setShader(shader);

  Canvas c = new Canvas(circleBitmap);
  c.drawCircle(bitmap.getWidth()/2, bitmap.getHeight()/2, bitmap.getWidth()/2, paint);

  
 // Context context = AvatarActivity.this;
  ImageView imageViewProva = (ImageView) findViewById(R.id.imm_cerchio);
  imageViewProva.setImageBitmap(circleBitmap);
  
  
  
  
}

  private class ImagePagerAdapter extends PagerAdapter {
	    private int[] mImages = new int[] {
	    		R.drawable.a1,
	    		R.drawable.a2,
	    		R.drawable.a3
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
      Context context = AvatarActivity.this;
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
