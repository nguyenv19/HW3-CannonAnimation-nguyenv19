package com.example.victor.hw3_cannonanimation_nguyenv19;

import android.graphics.Color;

/**
 * Created by Victor on 3/31/2017.
 *
 *
 * Class that makes the targets
 *
 *
 */

public class Target extends CustomCircle
{
    private int color;

    // initializer for the target
    public Target(int initX, int initY)
    {
        super(initX, initY, targetRadius, Color.RED);
        color = Color.RED;
        paint.setColor(color);


    }

    // Makes the most outside cirlce flash green if hit by cannon ball
    public void setTargetHit(boolean isHit)
    {
        if(isHit)
        {
            color = Color.GREEN;

        }
        else
        {
            color = Color.RED;
        }
        paint.setColor(color);
    }

}
