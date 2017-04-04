package com.example.victor.hw3_cannonanimation_nguyenv19;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by Victor on 3/31/2017.
 *
 * Sets up the control buttons for the cannon
 *
 */

public class CannonControls
{
    public static final int cannonUp = 1;
    public static final int cannonDown = -1;
    public static final int cannonFire = 0;
    private Paint rectPaint = new Paint();
    private Paint labelPaint = new Paint();
    private Rect rect;
    private String label;
    private int labelX;
    private int labelY;
    private int action;

    public CannonControls(int initLeft, int initTop, int initRight, int initBottom, String controlLabel, int initColor, int initAction)
    {
        rect = new Rect(initLeft, initTop, initRight, initBottom);
        rectPaint.setColor(initColor);
        this.label = controlLabel;
        labelPaint.setColor(Color.BLACK);
        labelPaint.setTextSize(50);
        labelX = initLeft;
        labelY = rect.centerY();
        this.action = initAction;
    }

    // Gets which box the user presses
    public boolean containsPoint(int x, int y)
    {
        return rect.contains(x, y);
    }

    // Draws the boxes for the controls
    public void onDraw(Canvas canvas)
    {
        canvas.drawRect(rect, rectPaint);
        canvas.drawText(label, labelX, labelY, labelPaint);
    }

    // Gets what box the user presses and does returns the action is
    public int getAction()
    {
        return this.action;
    }
}
