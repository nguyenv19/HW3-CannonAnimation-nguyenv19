package com.example.victor.hw3_cannonanimation_nguyenv19;

import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;

import java.util.ArrayList;

/**
 * Created by Victor on 3/31/2017.
 *
 * Puts all the classes together to create the cannon animation
 *
 *
 */

public class CannonAnimator implements Animator
{
    public static final int SCREEN_WIDTH = 2048;
    public static final int SCREEN_HEIGHT = 1392;
    private int initVelocity = 95;
    private int gravity = 5;
    private int controlColors = Color.rgb(210, 180, 50);
    private Cannon myCannon;
    private ArrayList<CannonBall> cannonBalls;
    private ArrayList<Target> targets;
    private CannonControls[] cannonControls = null;

    // All the elements together on the animation canvas
    public CannonAnimator()
    {
        ///gravity = newG;
        cannonBalls = new ArrayList<>();
        targets = new ArrayList<>();
        myCannon = new Cannon();
        initializeTargets();
        initializeCannonControls();
    }

    // Creates the targets
    private void initializeTargets()
    {
        Target t1 = new Target(1500, 300);
        Target t2 = new Target(1900, 600);
        Target t3 = new Target(1550, 775);
        targets.add(t1);
        targets.add(t2);
        targets.add(t3);
    }

    // Creates the controls
    private void initializeCannonControls()
    {
        cannonControls = new CannonControls[9];
        cannonControls[0] = new CannonControls(SCREEN_WIDTH-175, SCREEN_HEIGHT-405, SCREEN_WIDTH, SCREEN_HEIGHT-270, "UP", controlColors, CannonControls.cannonUp, 55, 20);
        cannonControls[1] = new CannonControls(SCREEN_WIDTH-175, SCREEN_HEIGHT-270, SCREEN_WIDTH, SCREEN_HEIGHT-135, "FIRE", Color.RED, CannonControls.cannonFire, 37, 20);
        cannonControls[2] = new CannonControls(SCREEN_WIDTH-175, SCREEN_HEIGHT-135, SCREEN_WIDTH, SCREEN_HEIGHT, "DOWN", controlColors, CannonControls.cannonDown, 15, 20);
        cannonControls[3] = new CannonControls(SCREEN_WIDTH-425, SCREEN_HEIGHT-405, SCREEN_WIDTH-190, SCREEN_HEIGHT-270, "+ Gravity ", Color.GREEN, CannonControls.cannongravityUp, 5, 15);
        cannonControls[4] = new CannonControls(SCREEN_WIDTH-425, SCREEN_HEIGHT-135, SCREEN_WIDTH-190, SCREEN_HEIGHT, "- Gravity", Color.GREEN, CannonControls.cannongravityDown, 10, 15);
        cannonControls[5] = new CannonControls(SCREEN_WIDTH-425, SCREEN_HEIGHT-270, SCREEN_WIDTH-190, SCREEN_HEIGHT-135, "Gravity: " + gravity, 5, 15);
        cannonControls[6] = new CannonControls(SCREEN_WIDTH-675, SCREEN_HEIGHT-135, SCREEN_WIDTH-435, SCREEN_HEIGHT, "+ Velocity", Color.GREEN, CannonControls.cannonvelocityUp, 10, 15);
        cannonControls[7] = new CannonControls(SCREEN_WIDTH-1200, SCREEN_HEIGHT-135, SCREEN_WIDTH-975, SCREEN_HEIGHT, "- Velocity", Color.GREEN, CannonControls.cannonvelocityDown, 10, 15);
        cannonControls[8] = new CannonControls(SCREEN_WIDTH-970, SCREEN_HEIGHT-135, SCREEN_WIDTH-690, SCREEN_HEIGHT, "Velocity: " + initVelocity, 10, 15);
    }

    @Override
    public int interval()
    {
        return 10;
    }

    @Override
    public int backgroundColor()
    {
        return Color.WHITE;
    }

    @Override
    public boolean doPause()
    {
        return false;
    }

    @Override
    public boolean doQuit()
    {
        return false;
    }

    @Override
    public void tick(Canvas canvas)
    {
        // Draws each target
        for (Target t : targets)
        {
            t.onDraw(canvas);
            t.ondrawTarget(canvas);
        }

        // Draws the cannon balls
        for(CannonBall cb : cannonBalls)
        {
            // Removes the cannon ball if it reaches past the right side of the screen
            if(cb.getX() > SCREEN_WIDTH)
            {
                cb = null;
                cannonBalls.remove(cb);
            }

            // Animates the cannon ball trajectory
            else
            {
                adjustVectors(cb);
                cb.onDraw(canvas);
                for(Target t : targets)
                {
                    if (cb.overlapsCircle(t))
                    {
                        t.setTargetHit(true);
                    }
                }
            }
        }

        myCannon.onDraw(canvas);

        // Draws the controls
        for(CannonControls c : cannonControls)
        {
            c.onDraw(canvas);
        }
    }

    @Override
    public void onTouch(MotionEvent event)
    {
        int x = (int) event.getX();
        int y = (int) event.getY();
        for (CannonControls c: cannonControls)
        {
            // Gets what button the user presses and does that action
            if (c.containsPoint(x,y))
            {
                doAction(event, c.getAction());

                break;
            }
        }
    }

    public void doAction(MotionEvent event, int action)
    {
        switch (action)
        {
            // If user presses the "UP" button, cannon aims up
            case CannonControls.cannonUp:
                myCannon.rotate(-1);
                break;

            // If user presses the "FIRE" button, cannon fires a cannon ball
            case CannonControls.cannonFire:
                if (event.getAction() == MotionEvent.ACTION_DOWN)
                    fireCannon();
                break;

            // If user presses the "DOWN" button, cannon aims down
            case CannonControls.cannonDown:
                myCannon.rotate(1);
                break;

            // If user presses the "- Gravity" button, gravity decreases
            case CannonControls.cannongravityDown:
                --gravity;
                initializeCannonControls();
                break;

            // If user presses the "DOWN" button, cannon aims down
            case CannonControls.cannongravityUp:
                ++gravity;
                initializeCannonControls();
                break;

            case CannonControls.cannonvelocityUp:
                ++initVelocity;
                initializeCannonControls();
                break;

            case CannonControls.cannonvelocityDown:
                --initVelocity;
                initializeCannonControls();
                break;
        }
    }

    // Creates a new cannon ball when "Fire" is pressed
    public void fireCannon()
    {
        double angle = Math.toRadians(myCannon.getRotateAngle());
        double vix = Math.cos(angle) * initVelocity;
        double viy = Math.sin(angle) * initVelocity;
        CannonBall newBall = new CannonBall(myCannon.getcannonCenterX(), myCannon.getcannonCenterY(), (int)vix, (int)viy, 0, gravity);
        cannonBalls.add(newBall);
    }

    // Update the trajectory of the cannon ball
    public void adjustVectors(CannonBall cb)
    {
        cb.checkCollision(0, SCREEN_WIDTH*2, 0, SCREEN_HEIGHT);
        cb.accelerateBall();
        cb.updatePos();
    }
}
