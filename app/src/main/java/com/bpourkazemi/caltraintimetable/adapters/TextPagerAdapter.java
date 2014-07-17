package com.bpourkazemi.caltraintimetable.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
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
        // TODO: Add style to textview
        TextView nextArrival = new TextView(context);
        nextArrival.setText(trainTimes.get(position));
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
