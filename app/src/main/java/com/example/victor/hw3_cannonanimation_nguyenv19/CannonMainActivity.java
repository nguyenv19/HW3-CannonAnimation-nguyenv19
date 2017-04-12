package com.example.victor.hw3_cannonanimation_nguyenv19;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.LinearLayout;

/*
 *  External Citation
 *      Date: March 31, 2017
 *      Problem: Did not know how to rotate the cannon, and had trouble painting the cannonballs
 *      Resource: Cole French
 *      Solution: Cole showed me matrix and how to use it
 *          and told me to make a class that draws rectangles that act as buttons/controls.
 *          He also told me how to make an abstract class that draws the target and cannonball
 *
 *
 *
 *
 */



/*
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
 *
 * Part B:
 * Ball bounces off the walls
 * There can be an arbitrary amount of balls on the screen
 * Velocity and Gravity can be adjusted by the user
 */
public class CannonMainActivity extends Activity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cannon_main);

        // Lock screen in landscape
        super.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);

        // Create the cannon animation canvas and place it in the main layout
        Animator cannonAnim = new CannonAnimator();
        AnimationCanvas myCannon = new AnimationCanvas(this, cannonAnim);
        LinearLayout mainLayout = (LinearLayout) this.findViewById(R.id.topLevel);
        mainLayout.addView(myCannon);
    }
}
