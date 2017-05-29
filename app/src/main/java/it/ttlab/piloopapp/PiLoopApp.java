package it.ttlab.piloopapp;

import android.app.Application;
import android.content.Context;

/**
 * Created by Andrea Ferraro on 27/05/2017.
 */

public class PiLoopApp extends Application {

    private static Context myContext;

    public static Context getContext() {
        return myContext;
    }

    public static void setContext(Context mContext) {
        myContext = mContext;
    }

}
