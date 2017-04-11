package com.example.victor.hw3_cannonanimation_nguyenv19;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.LinearLayout;


/**
 * CannonMainActivity
 *
 * This is the activity for the cannon animation. It creates a AnimationCanvas
 * containing a particular Animator object
 *
 * @author Andrew Nuxoll
 * @version September 2012
 * @author Victor Nguyen
 * @version April 2017
 *
 * Part B:
 * Ball bounces off the walls
 * There can be an arbitrary amount of balls on the screen
 * Velocity and Gravity can be adjusted by the user
 *
 *
 *
 */
public class CannonMainActivity extends Activity //AppCompatActivity
{

    //private SeekBar gBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cannon_main);

        // Lock screen in landscape
        super.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);

        /*
        // Seekbar to adjust gravity
        gBar = (SeekBar) findViewById(R.id.seekBar);
        gBar.setMax(25);
        gBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {

            }
        });
        */


        // Create the cannon animation canvas and place it in the main layout
        Animator cannonAnim = new CannonAnimator();
        AnimationCanvas myCannon = new AnimationCanvas(this, cannonAnim);
        LinearLayout mainLayout = (LinearLayout) this.findViewById(R.id.topLevel);
        mainLayout.addView(myCannon);


    }


}
