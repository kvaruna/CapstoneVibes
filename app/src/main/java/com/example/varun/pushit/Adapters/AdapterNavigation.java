package com.example.varun.pushit.Adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.example.varun.pushit.Fragments.FragmentCategories;
import com.example.varun.pushit.Fragments.FragmentPrograms;
import com.example.varun.pushit.R;
import com.github.ksoichiro.android.observablescrollview.CacheFragmentStatePagerAdapter;

public class AdapterNavigation extends CacheFragmentStatePagerAdapter {

    // Create variables to store page titles
    private  String[] sPagerTitles;

    private Context mContext;

    // Constructor to set context and page titles data
    public AdapterNavigation(Context c, FragmentManager fm) {
        super(fm);

        mContext = c;

        // Get page titles from strings.xml
        sPagerTitles = mContext.getResources().getStringArray(R.array.home_pager_titles);
    }

    @Override
    protected Fragment createItem(int position) {
        // Initialize fragments.
        // Please be sure to pass scroll position to each fragments using setArguments.
        Fragment f;
        switch (position) {
            case 0: {
                // Set first tab with workout category
                f = new FragmentCategories();
                break;
            }
            case 1:
            default: {
                // set second tab with day program
                f = new FragmentPrograms();
                break;
            }
        }
        return f;
    }

    @Override
    public int getCount() {
        return sPagerTitles.length;
    }

    @Override
    public Fragment getItemAt(int position) {
        return super.getItemAt(position);
    }

    @Override
    public Fragment getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return sPagerTitles[position];
    }
}