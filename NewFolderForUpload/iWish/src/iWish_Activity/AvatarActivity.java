package iWish_Activity;
/**Michela*/


import iWish_Utente.Utente;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.progect.iwish.R;
import com.viewpagerindicator.CirclePageIndicator;


public class AvatarActivity extends Activity {
	private final String LAZY = "lazy";
	private final String ACTIVE = "active";
	private ViewPager viewPager2;
	private ImagePagerAdapter adapter;
	private Intent intent;
	private Intent intent2;
	private Utente mUser;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.avatar);

		viewPager2 = (ViewPager) findViewById(R.id.view_pager_avatar);
		adapter = new ImagePagerAdapter();
		viewPager2.setAdapter(adapter);
		intent=getIntent();
		final String pkg=getPackageName();
	//	mUser = (Utente)intent.getSerializableExtra(pkg+".myUtente");
		intent2=new Intent(getApplicationContext(), GenderActivity.class);


		//Bind the title indicator to the adapter
		CirclePageIndicator titleIndicator = (CirclePageIndicator)findViewById(R.id.indicator2);
		titleIndicator.setViewPager(viewPager2);

		ImageView scorciatoia = (ImageView)findViewById(R.id.cerchio);

		final ImageButton fatto = (ImageButton)findViewById(R.id.done);
		//  final TextView proviamoci = (TextView)findViewById(R.id.prova);
		fatto.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				typeUser();
				intent2.putExtra(pkg + " .Utente ", mUser);
				fatto.setImageResource(R.drawable.botton_done2);
				startActivity(intent2);
			}

			private void typeUser() {
				if(viewPager2.getCurrentItem()==0){
					mUser.setTypeUser("lazy");
				}else {
					mUser.setTypeUser("active");
				}
			}
		});
	}

	private class ImagePagerAdapter extends PagerAdapter {
		private int[] mImages = new int[] {
				R.drawable.short_lazy_ok,
				R.drawable.short_active_ok,

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