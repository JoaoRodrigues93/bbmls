package mz.co.barclays.barclaysloansimulator.models;

import android.app.Activity;
import android.content.Intent;

import mz.co.barclays.barclaysloansimulator.AboutActivity;
import mz.co.barclays.barclaysloansimulator.ContactActivity;
import mz.co.barclays.barclaysloansimulator.VersionActivity;

/**
 * Created by João on 10/05/2016.
 */
public class MainMenuActions {

    public static void openAbout (Activity activity){
        Intent intent = new Intent(activity, AboutActivity.class);
        activity.startActivity(intent);
    }

    public static void openVersion (Activity activity){
        Intent intent = new Intent(activity, VersionActivity.class);
        activity.startActivity(intent);
    }

    public static void openContact (Activity activity){

        Intent intent = new Intent(activity,ContactActivity.class);
        activity.startActivity(intent);

    }
}
