package com.example.victor.hw3_cannonanimation_nguyenv19;

import android.graphics.Canvas;
import android.graphics.Color;
/**
 *
 * Created by Victor on 3/31/2017.
 *
 * Class for the attributes for cannon ball
 *
 *
 */

public class CannonBall extends CustomCircle
{
    private static final int radius = 50;
    private int vx;
    private int vy;
    private int ax;
    private int ay;

    // Initializer for the cannon ball
    public CannonBall(int initX, int initY, int initVX, int initVY, int initAX, int initAY)
    {
        super(initX, initY, radius, Color.BLACK);
        this.vx = initVX;
        this.vy = initVY;
        this.ax = initAX;
        this.ay = initAY;
    }

    // updates where the ball is based on the velocity
    public void updatePos()
    {
        x = x + vx;
        y = y + vy;
    }

    // Calculation for the velocity of the ball
    public void accelerateBall()
    {
        vx = vx + ax;
        vy = vy + ay;
    }

    // Checks to see if the ball hits the walls
    public void checkCollision(int leftBound, int rightBound, int topBound, int bottomBound)
    {
        int xLeft = x - radius;
        int xRight = x + radius;
        int yTop = y - radius;
        int yBottom = y + radius;

        // If it hits the left wall, bounces back
        if((xLeft < leftBound))
        {
            vx = -1*vx;
        }

        // If it hits the rigt wall, bounces back NOT REALLY NECESSARY BECAUSE IT SHOULD DISAPPEAR
        if((xRight > rightBound))
        {
            vx = -1*vx;
        }

        // If it hits the top wall, bounces back
        if((yTop > topBound))
        {
            vy = -1*vy;
        }

        // If it hits the bottom wall, bounces back
        if((yBottom < bottomBound))
        {
            vy = -1*vy;
        }
    }

    // Draws the cannon ball
    public void onDraw(Canvas canvas)
    {
        canvas.drawCircle(x, y, radius, paint);
    }
}
