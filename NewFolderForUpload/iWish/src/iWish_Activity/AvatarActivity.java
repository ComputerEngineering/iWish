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
	private Utente mUser;
	private TextView stampaNome;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.avatar);

		viewPager2 = (ViewPager) findViewById(R.id.view_pager_avatar);
		adapter = new ImagePagerAdapter();
		viewPager2.setAdapter(adapter);

		stampaNome = (TextView)findViewById(R.id.nomeUtente);
		Intent intent = getIntent();// Prendiamo l'intent passato da Register
		mUser = (Utente)intent.getSerializableExtra("u");// Prendiamo l'oggetto Utente passato tramite intent
		stampaNome.setText(mUser.getName());// Stampiamo il nome dell'utente passato  
		CirclePageIndicator titleIndicator = (CirclePageIndicator)findViewById(R.id.indicator2);//Bind the title indicator to the adapter
		titleIndicator.setViewPager(viewPager2);
		//		ImageView scorciatoia = (ImageView)findViewById(R.id.cerchio);
		final ImageButton fatto = (ImageButton)findViewById(R.id.done);
		fatto.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				fatto.setImageResource(R.drawable.botton_done2);//cambiamo il colore al bottone
				Intent intent2 = new Intent("iWish_Activity.GENDER");// Creiamo un nuovo intent passando il nome dell'intent (ma si poteva fare anche passando il nome della classe) 
				typeUser();//aggiorniamo i dati utente con il campo "typeUser"
				intent2.putExtra("u", mUser);//aggiungiamo questa nuova informazione nel nostro intent
				startActivity(intent2);//facciamo partire l'intent GENDER
			}
		});
	}
	private void typeUser() {
		if(viewPager2.getCurrentItem()==0){
			mUser.setTypeUser(LAZY);
		}else {
			mUser.setTypeUser(ACTIVE);
		}
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