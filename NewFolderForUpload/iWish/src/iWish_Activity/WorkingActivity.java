package iWish_Activity;

import com.progect.iwish.R;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

/**Rafaella*/

public class WorkingActivity extends Activity{
	private ImageButton bt_sounds;
	private ImageButton bt_musics;
	private ImageButton bt_stop;
	private Image im_positionWorking;
	private Image im_typeWorkingMan;
	private Image im_timeWorking;
	private Image im_typeWorking;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.working);
	
	
	ImageButton ok = (ImageButton)findViewById(R.id.m_letsgo2);
	ok.setOnClickListener(new OnClickListener() {
	
		@Override
		public void onClick(View v) {
			startActivity(new Intent(WorkingActivity.this,ProfileActivity.class ));
		}
	});
	
	}

}

