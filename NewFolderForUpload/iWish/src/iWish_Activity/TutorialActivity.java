package iWish_Activity;
/**Michela*/

import android.app.Activity;
import android.os.Bundle;

import android.widget.ImageSwitcher;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewSwitcher.ViewFactory;


import com.progect.iwish.R;

/**
 * Created by miki on 03/07/14.
 */
public class TutorialActivity extends Activity {

    private ImageSwitcher imageSwitcher;
    Button btnNext;
    TextView testo;

    // Array of Image IDs to Show In ImageSwitcher

   int imageIds[] ={R.drawable.tut1,R.drawable.tut2,R.drawable.tut3,R.drawable.tut4,R.drawable.tut5,R.drawable.tut6,R.drawable.tut7,R.drawable.tut8,R.drawable.tut9,};
    int messageCount=imageIds.length;
    // to keep current Index of ImageID array
    int currentIndex=-1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tutorial);
        // get The references
        btnNext=(Button)findViewById(R.id.buttonNext);
        imageSwitcher = (ImageSwitcher) findViewById(R.id.imageSwitcher1);
      //prova

      //  testo = (TextView) findViewById(R.id.andiamo);

        // Set the ViewFactory of the ImageSwitcher that will create ImageView object when asked
        imageSwitcher.setFactory(new ViewFactory() {

            public View makeView() {
                // TODO Auto-generated method stub

                // Create a new ImageView set it's properties
                ImageView imageView = new ImageView(getApplicationContext());
             //   imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                imageView.setScaleType(ImageView.ScaleType.CENTER);
                imageView.setLayoutParams(new ImageSwitcher.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
             //   imageView.setLayoutParams(new ImageSwitcher.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT));
                return imageView;
            }
        });

        // Declare the animations and initialize them
        Animation in = AnimationUtils.loadAnimation(this,android.R.anim.slide_in_left);
        Animation out = AnimationUtils.loadAnimation(this,android.R.anim.slide_out_right);

        // set the animation type to imageSwitcher
        imageSwitcher.setOutAnimation(out);
        imageSwitcher.setInAnimation(in);



        // ClickListener for NEXT button
        // When clicked on Button ImageSwitcher will switch between Images
        // The current Image will go OUT and next Image  will come in with specified animation
        btnNext.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                currentIndex++;
                // If index reaches maximum reset it
                if(currentIndex==messageCount)
                    currentIndex=0;
                imageSwitcher.setImageResource(imageIds[currentIndex]);
            }
        });

    }


}