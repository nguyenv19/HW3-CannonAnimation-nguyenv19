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
    public static final int cannongravityUp = 2;
    public static final int cannongravityDown = 3;
    public static final int cannonvelocityDown = 4;
    public static final int cannonvelocityUp = 5;
    private Paint rectPaint = new Paint();
    private Paint labelPaint = new Paint();
    private Rect rect;
    private String label;
    private int labelX;
    private int labelY;
    private int action;

    // ctor for the buttons
    public CannonControls(int initLeft, int initTop, int initRight, int initBottom, String controlLabel, int initColor, int initAction, int newlabelX, int newlabelY)
    {
        this.rect = new Rect(initLeft, initTop, initRight, initBottom);
        this.rectPaint.setColor(initColor);
        this.label = controlLabel;
        this.labelPaint.setColor(Color.BLACK);
        this.labelPaint.setTextSize(50);
        this.labelX = initLeft + newlabelX;
        this.labelY = rect.centerY()+newlabelY;
        this.action = initAction;
    }

    // ctor the "text view"
    public CannonControls(int initLeft, int initTop, int initRight, int initBottom, String newValue, int newlabelX, int newlabelY)
    {
        this.rectPaint.setColor(Color.WHITE);
        this.rectPaint.setStyle(Paint.Style.STROKE);
        this.rect = new Rect(initLeft, initTop, initRight, initBottom);
        this.label = newValue;
        this.labelPaint.setColor(Color.BLACK);
        this.labelPaint.setTextSize(50);
        this.labelX = initLeft + newlabelX;
        this.labelY = rect.centerY()+ newlabelY;
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
