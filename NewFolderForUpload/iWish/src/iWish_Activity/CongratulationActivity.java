package iWish_Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.progect.iwish.R;

/** Raffaella*/

public class CongratulationActivity extends Activity {
	private ImageView imgbt_expressWish;
	private com.mikhaellopez.circularimageview.CircularImageView civ_user;
	private com.mikhaellopez.circularimageview.CircularImageView civ_type;
	private com.mikhaellopez.circularimageview.CircularImageView civ_bmi;
	private TextView tv_type;
	private TextView tv_bmi;
	
	  @Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.congratulation);
	    
	    civ_bmi = (com.mikhaellopez.circularimageview.CircularImageView)findViewById(R.id.civ_bmi);
	    civ_type = (com.mikhaellopez.circularimageview.CircularImageView)findViewById(R.id.civ_type);
	    civ_user = (com.mikhaellopez.circularimageview.CircularImageView)findViewById(R.id.civ_cerchio);
	    imgbt_expressWish = (ImageView)findViewById(R.id.img_bt_expressWish);
	    tv_bmi = (TextView)findViewById(R.id.tv_type);
	    tv_bmi = (TextView)findViewById(R.id.tv_bmi);
	   
	    imgbt_expressWish.setOnClickListener(new OnClickListener() {
	  		
	  		@Override
	  		public void onClick(View v) {
	  			startActivity(new Intent(CongratulationActivity.this,iWishActivity.class ));
	  		}
	  	});
	    
	  }
}
