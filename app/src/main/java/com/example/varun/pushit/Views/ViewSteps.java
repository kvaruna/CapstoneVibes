package com.example.varun.pushit.Views;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.varun.pushit.Activities.ActivityDetail;
import com.example.varun.pushit.R;

public class ViewSteps extends LinearLayout {

    // Create view object
    private FrameLayout mLytStepsLayout;

    // Create context and image loader class objects
    private Context mContext;

    // Create variables to store dimension values
    private int mImageWidth, mImageHeight;

    // Create arraylist variable to store image data
    private String mSteps;

    public ViewSteps(Context context, String steps) {
        super(context);

        mContext        = context;
        mSteps         = steps;

        initView();
    }

    // Method to initialize views
    private void initView() {
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // Connect view objects with view ids in xml
        final View v            =  inflater.inflate(R.layout.pager_adapter_steps, this);
        mLytStepsLayout         = (FrameLayout) v.findViewById(R.id.lytStepsLayout);


        ScrollView scrollview = new ScrollView(mContext);
        ScrollView.LayoutParams lp = new ScrollView.LayoutParams(
                getResources().getDimensionPixelSize(R.dimen.steps_scrollview_width_and_height),
                getResources().getDimensionPixelSize(R.dimen.steps_scrollview_width_and_height));

        lp.gravity = Gravity.CENTER;
        scrollview.setLayoutParams(lp);

        TextView txtSteps = new TextView(mContext);
        txtSteps.setGravity(Gravity.CENTER);
        txtSteps.setTextColor(ContextCompat.getColor(mContext, R.color.text_and_icon_color));
        txtSteps.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                getResources().getDimension(R.dimen.body));
        txtSteps.setLineSpacing(
                TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX,
                        getResources().getDimension(R.dimen.line_spacing),
                        getResources().getDisplayMetrics()),
                1.0f);
        txtSteps.setText(mSteps);

        scrollview.addView(txtSteps, new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

        mLytStepsLayout.addView(scrollview);

    }

}