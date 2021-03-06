package com.example.varun.pushit.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Locale;

public class Utils {

    // Key values for passing data between activities
    public static final String ARG_PARENT_PAGE          = "parent_page";
    public static final String ARG_WORKOUT_ID           = "workout_id";
    public static final String ARG_WORKOUT_NAME         = "workout_name";
    public static final String ARG_WORKOUT_IDS          = "workout_ids";
    public static final String ARG_WORKOUT_NAMES        = "workout_names";
    public static final String ARG_WORKOUT_IMAGES       = "workout_images";
    public static final String ARG_WORKOUT_TIMES        = "workout_times";
    public static final String ARG_WORKOUTS 	        = "workouts";
    public static final String ARG_PROGRAMS 	        = "programs";

    // Key values for ad interstitial trigger
    public static final String ARG_TRIGGER      = "trigger";

    // Set TextToSpeech language, you can check other locale language code in
    // http://developer.android.com/reference/java/util/Locale.html
    public static final Locale ARG_LOCALE = Locale.US;

    // Configurable parameters. you can configure these parameter.
    // Set database path. It must be similar with package name.
    public static final String ARG_DATABASE_PATH = "/data/data/com.example.varun.pushit/databases/";

    // Set default break time
    public static final String ARG_DEFAULT_BREAK = "00:10";
    public static final String ARG_DEFAULT_START = "00:10";

    // Set default sound volume
    public static final int ARG_SOUND_VOLUME = 7;

    // For every recipe detail you want to display interstitial ad.
    // 3 means interstitial ad will display after user open detail page three times.
    public static final int ARG_TRIGGER_VALUE = 3;





    // Method to load data that stored in SharedPreferences
    public static int loadPreferences(String param, Context c){
        SharedPreferences sharedPreferences = c.getSharedPreferences("user_data", 0);
        return sharedPreferences.getInt(param, 0);
    }

    // Method to save data to SharedPreferences
    public static void savePreferences(String param, int value, Context c){
        SharedPreferences sharedPreferences = c.getSharedPreferences("user_data", 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(param, value);
        editor.apply();
    }
}

