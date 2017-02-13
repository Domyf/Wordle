package com.domenico.wordle.UI;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import com.domenico.wordle.R;

/**
 * Created by Domenico on 11/02/2017.
 */

public class HDTMButtonAdapter extends BaseAdapter {

    private Context mContext;
    private int numElements;
    private int lastItemClickable;

    public HDTMButtonAdapter(Context c) {
        mContext = c;
        numElements = 0;
        lastItemClickable = 1;
    }

    public int getCount() {
        return numElements;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(final int position, View convertView, final ViewGroup parent) {
        View view;
        HDTMButton button;
        /*if (convertView == null) {
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
        button.setBackgroundResource(R.drawable.button3);*/
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.grid_button, parent, false);
        } else {
            view = convertView;
        }

        if (position >= lastItemClickable) {
            button = (HDTMButton) view.findViewById(R.id.gridBtn);
            button.setVisibility(View.GONE);
            button = (HDTMButton) view.findViewById(R.id.gridBtnUnable);
            button.setVisibility(View.VISIBLE);
        } else {
            button = (HDTMButton) view.findViewById(R.id.gridBtnUnable);
            button.setVisibility(View.GONE);
            button = (HDTMButton) view.findViewById(R.id.gridBtn);
            button.setVisibility(View.VISIBLE);
        }
        button.setText(""+(position+1));
        if (Integer.parseInt(""+button.getText()) <= lastItemClickable) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((GridView) parent).performItemClick(v, position, 0);
                }
            });
        }

        return view;
    }

    public void setElements(int num) {
        numElements = num;
    }

    public void setLastItemClickable(int lastItemClickable) {
        this.lastItemClickable = lastItemClickable;
    }

    public int getLastItemClickable() {
        return lastItemClickable;
    }
}


