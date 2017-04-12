package com.example.victor.hw3_cannonanimation_nguyenv19;

/**
 * Created by Victor on 4/3/2017.
 */

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by Victor on 3/31/2017.
 *
 * Based of Nuxull's class in Spots program
 *
 * Class for circles
 *
 */

public abstract class CustomCircle
{
    protected Paint paint = new Paint();
    protected int color;
    protected int radius;
    protected int x;
    protected int y;
    protected static final int targetRadius = 100;

    // Initializer for CustomCircle
    public CustomCircle(int initX, int initY, int initRadius, int initColor)
    {
        this.x = initX;
        this.y = initY;
        this.radius = initRadius;
        this.color = initColor;
        paint.setColor(color);
    }

    public void onDraw(Canvas canvas)
    {
        canvas.drawCircle(x, y, radius, paint);
    }

    // Draws bulleyes for the target
    public void ondrawTarget(Canvas canvas)
    {
        canvas.drawCircle(x, y, radius, paint);

        paint.setColor(Color.BLUE);
        canvas.drawCircle(x, y, targetRadius-15, paint);

        paint.setColor(Color.BLACK);
        canvas.drawCircle(x, y, targetRadius-25, paint);

        paint.setColor(Color.rgb(100, 250, 255));
        canvas.drawCircle(x, y, targetRadius-40, paint);

        paint.setColor(Color.WHITE);
        canvas.drawCircle(x, y, targetRadius-55, paint);

        paint.setColor(Color.RED);
        canvas.drawCircle(x, y, targetRadius-95, paint);
    }

    // Checks to see if two circles overlap
    public boolean overlapsCircle(CustomCircle otherCircle)
    {
        int x1;
        int x2;
        int y1;
        int y2;

        x1 = x;
        x2 = otherCircle.getX();
        y1 = y;
        y2 = otherCircle.getY();

        int distance = (int)Math.sqrt( (x2-x1)*(x2-x1) + (y2-y1)*(y2-y1) );
        return  (distance < (radius + otherCircle.getRadius()));
    }

    /*
    *
    *
    *   Getters for custom circle
    *
    */

    public int getX()
    {
        return this.x;
    }

    public int getY()
    {
        return this.y;
    }

    public int getRadius()
    {
        return this.radius;
    }
}

