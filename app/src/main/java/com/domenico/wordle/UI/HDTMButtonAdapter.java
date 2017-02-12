package com.domenico.wordle.UI;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.domenico.wordle.R;

/**
 * Created by Domenico on 11/02/2017.
 */

public class HDTMButtonAdapter extends BaseAdapter {

    private Context mContext;

    public HDTMButtonAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return 148;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(final int position, View convertView, ViewGroup parent) {
        HDTMButton button;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            button = new HDTMButton(mContext);
            button.setLayoutParams(new GridView.LayoutParams(130, 160));
            button.setTextColor(Color.WHITE);
            button.setShadowLayer(1, 0, 10, Color.rgb(10, 97, 194));
            //imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        } else {
            button = (HDTMButton) convertView;
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Adapter", "Bottone "+(position+1));
            }
        });
        button.setText(""+(position+1));
        button.setBackgroundResource(R.drawable.button3);
        return button;
    }
}


