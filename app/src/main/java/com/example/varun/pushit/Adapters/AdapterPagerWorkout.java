package com.example.varun.pushit.Adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.example.varun.pushit.Activities.ActivityStopWatch;

public class AdapterPagerWorkout extends PagerAdapter {

    private View[] mViews;

    public AdapterPagerWorkout(Context context, View... views) {
        this.mViews = views;
    }

    @Override
    public int getCount() {
        return mViews.length;
    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position) {
        View currentView = mViews[position];
        ((ViewPager) collection).addView(currentView);
        return currentView;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        ((ViewPager) collection).removeView((View) view);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

}
