package com.example.varun.pushit.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.varun.pushit.Adapters.AdapterNavigation;
import com.example.varun.pushit.Fragments.FragmentCategories;
import com.example.varun.pushit.Fragments.FragmentPrograms;
import com.example.varun.pushit.R;
import com.example.varun.pushit.Utils.Utils;
import com.gigamole.navigationtabbar.ntb.NavigationTabBar;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements
        FragmentCategories.OnSelectedCategoryListener,
        FragmentPrograms.OnSelectedDayListener, View.OnClickListener{

    // Create view objects
    private View mHeaderView;
    private Toolbar mToolbar;
    private ViewPager mPager;

    // Create adapter object
    public AdapterNavigation mAdapterNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        // Connect view objects with view ids in xml
        mHeaderView         = findViewById(R.id.header);
        mToolbar            = (Toolbar) findViewById(R.id.toolbar);
        mPager              = (ViewPager) findViewById(R.id.pager);
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        Log.d("THis is fcm",""+task.getResult().getToken());
                    }
                });
        // Set adapter object
        mAdapterNavigation  = new AdapterNavigation(this, getSupportFragmentManager());

        // Set elevation to header view
        ViewCompat.setElevation(mHeaderView, getResources().getDimension(R.dimen.toolbar_elevation));
        // Set tab bar adapter to the pager
        mPager.setAdapter(mAdapterNavigation);
        // Set toolbar as actionbar
        setSupportActionBar(mToolbar);

        final NavigationTabBar navigationTabBar = (NavigationTabBar) findViewById(R.id.ntb);
        final ArrayList<NavigationTabBar.Model> models = new ArrayList<>();


        models.add(new NavigationTabBar.Model.Builder(getResources().getDrawable(R.drawable.workout),
                Color.parseColor("#6da5ff")).title("WORKOUTS").badgeTitle("WORKOUTS").build());

        models.add(new NavigationTabBar.Model.Builder(getResources().getDrawable(R.drawable.event),
                Color.parseColor("#6da5ff")).title("SCHEDULE").badgeTitle("SCHEDULE").build());



        navigationTabBar.setModels(models);
        navigationTabBar.setViewPager(mPager,0);
        navigationTabBar.setTitleMode(NavigationTabBar.TitleMode.ACTIVE);
        navigationTabBar.setBadgeGravity(NavigationTabBar.BadgeGravity.BOTTOM);
        navigationTabBar.setBadgePosition(NavigationTabBar.BadgePosition.CENTER);
        navigationTabBar.setTypeface("fonts/custom_font.ttf");
        navigationTabBar.setIsBadged(true);
        navigationTabBar.setIsTitled(true);
        navigationTabBar.setIsTinted(true);
        navigationTabBar.setIsBadgeUseTypeface(true);
        navigationTabBar.setBadgeBgColor(Color.RED);
        navigationTabBar.setBadgeTitleColor(Color.WHITE);
        navigationTabBar.setIsSwiped(true);
        navigationTabBar.setBgColor(Color.WHITE);
        navigationTabBar.setBadgeSize(10);
        navigationTabBar.setTitleSize(21);
        navigationTabBar.setIconSizeFraction((float) 0.5);

        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.menuAbout:
                        // Open ActivityAbout
                        Intent aboutIntent = new Intent(getApplicationContext(), ActivityAbout.class);
                        startActivity(aboutIntent);
                        return true;
                    default:
                        return true;
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_menu_home, menu);
        return true;
    }

    @Override
    public void onSelectedCategory(String selectedID, String selectedName) {
        // On workout category selected open ActivityWorkouts and pass
        // selected values to that activity

        Intent detailIntent = new Intent(this, ActivityWorkouts.class);
        detailIntent.putExtra(Utils.ARG_WORKOUT_ID, selectedID);
        detailIntent.putExtra(Utils.ARG_WORKOUT_NAME, selectedName);
        detailIntent.putExtra(Utils.ARG_PARENT_PAGE, Utils.ARG_WORKOUTS);
        startActivity(detailIntent);

    }

    @Override
    public void onSelectedDay(String selectedID, String selectedName) {
        // On day selected open ActivityWorkouts and pass
        // selected values to that activity

        Intent detailIntent = new Intent(this, ActivityWorkouts.class);
        detailIntent.putExtra(Utils.ARG_WORKOUT_ID, selectedID);
        detailIntent.putExtra(Utils.ARG_WORKOUT_NAME, selectedName);
        detailIntent.putExtra(Utils.ARG_PARENT_PAGE, Utils.ARG_PROGRAMS);
        startActivity(detailIntent);

    }

    @Override
    public void onClick(View v) {


        Intent i= new Intent(HomeActivity.this,ActivityNotes.class);
        startActivity(i);




    }
}

