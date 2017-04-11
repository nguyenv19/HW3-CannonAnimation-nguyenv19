package com.example.victor.hw3_cannonanimation_nguyenv19;

/*
 * Created by Victor on 3/31/2017.
 *
 * Draws the cannon and the rotate method
 *
 *
 *
 */

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;

public class Cannon
{
    protected Paint cannonPaint = new Paint();
    private int cannonColor;

    private static final int top = (CannonAnimator.SCREEN_HEIGHT/2-50);
    private static final int bottom = (CannonAnimator.SCREEN_HEIGHT/2+50);
    private static final int left = 0;
    private static final int right = 200;
    private static final int cannonpivotY = CannonAnimator.SCREEN_HEIGHT/2;

    private static final int maxAngle = 90;
    private int rotateAngle;
    private RectF cannonBarrel;
    private Path cannonPath;

    // Creates the color and size for the cannon
    public void initCannon()
    {
        cannonPath = new Path();
        rotateAngle = 0;
        cannonColor = Color.rgb(210, 180, 50);
        cannonPaint.setColor(cannonColor);


    }

    // Creates the cannon
    public Cannon()
    {
        initCannon();
        cannonBarrel = new RectF(left, top, right, bottom);
        cannonPath.addRect(cannonBarrel, Path.Direction.CW);


    }

    // Draws the cannon
    public void onDraw(Canvas canvas)
    {
        canvas.drawPath(cannonPath, cannonPaint);
    }

    // Rotation for the cannon
    public void rotate(float degrees)
    {
        if ((rotateAngle <= maxAngle && degrees > 0) || (rotateAngle >= -maxAngle && degrees <0 ) )
        {
            Matrix m = new Matrix();
            m.setRotate(degrees, 0, cannonpivotY);
            cannonPath.transform(m);
            rotateAngle += degrees;
        }
    }

    // Gets what angle the cannon is currently at
    public int getRotateAngle()
    {
        return this.rotateAngle;
    }

    // Getter for the center of the cannon(x direction)
    public int getcannonCenterX()
    {
        RectF temp = new RectF();
        cannonPath.computeBounds(temp, true);
        return (int)temp.centerX();
    }

    // Getter for the center of the cannon(y direction)
    public int getcannonCenterY()
    {
        RectF temp = new RectF();
        cannonPath.computeBounds(temp, true);
        return (int)temp.centerY();
    }
}
