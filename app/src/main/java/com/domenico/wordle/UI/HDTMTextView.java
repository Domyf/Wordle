package com.domenico.wordle.UI;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Domenico on 10/02/2017.
 */

public class HDTMTextView extends TextView {

    public HDTMTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(), "Jellee-Roman.ttf"));
    }

}
