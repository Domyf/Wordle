package com.domenico.wordle.UI;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;

/**
 * Created by Domenico on 10/02/2017.
 */

public class HDTMButton extends Button {

    private int paddingBottom;

    public HDTMButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(), "Jellee-Roman.ttf"));
        paddingBottom = getPaddingBottom();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!isPressed())
            setPadding(0, 0, 0, paddingBottom);
        if (event.getAction() == MotionEvent.ACTION_DOWN)
            setPadding(0, 0, 0, 0);
        if (event.getAction() == MotionEvent.ACTION_UP)
            setPadding(0, 0, 0, paddingBottom);
        return super.onTouchEvent(event);
    }

}
