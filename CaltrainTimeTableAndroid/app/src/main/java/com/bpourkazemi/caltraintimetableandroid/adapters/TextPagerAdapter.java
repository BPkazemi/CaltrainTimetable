package com.bpourkazemi.caltraintimetableandroid.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by babakpourkazemi on 7/16/14.
 */
public class TextPagerAdapter extends PagerAdapter {
    private ArrayList<String> trainTimes;
    private Context context;

    public TextPagerAdapter(ArrayList<String> trainTimes, Context context) {
        this.trainTimes = trainTimes;
        this.context = context;
    }

    @Override public int getCount() {
        return trainTimes.size();
    }

    @Override public Object instantiateItem(ViewGroup collection, int position) {
        TextView nextArrival = new TextView(context);
        nextArrival.setText(trainTimes.get(position));
        nextArrival.setTextSize(60);
        nextArrival.setGravity(Gravity.CENTER);
        nextArrival.setAlpha( (float) 0.74 );

        // TODO: Change textSize based on screen size, the Android way (whatever that is)

        collection.addView(nextArrival);
        return nextArrival;
    }

    @Override public void destroyItem(ViewGroup collection, int position,
                                      Object view) {
        ((ViewPager) collection).removeView((TextView) view);
    }

    @Override public boolean isViewFromObject(View view, Object object) {
        return view == ((TextView) object);
    }

}
