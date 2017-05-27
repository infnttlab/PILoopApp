package it.ttlab.piloopapp.deviceinfo;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import org.json.JSONException;
import org.json.JSONObject;

import it.ttlab.piloopapp.PiLoopApp;


public class DisplayProperty implements Property {
    @Override
    public Object getProperty() {
        JSONObject jsonObject = new JSONObject();

        ActivityManager activityManager = (ActivityManager) PiLoopApp.getContext().getSystemService(Context.ACTIVITY_SERVICE);
        ConfigurationInfo configurationInfo = activityManager.getDeviceConfigurationInfo();
        try {
            jsonObject.put("GlEsVersion", configurationInfo.getGlEsVersion());
        } catch (JSONException e) {
            try {
                jsonObject.put("GlEsVersion", JSONObject.NULL);
            } catch (JSONException e1) {}
        }

        if(Build.VERSION.SDK_INT < 17) {
            DisplayMetrics displayMetrics = PiLoopApp.getContext().getResources().getDisplayMetrics();
            try {
                jsonObject.put("x", Integer.valueOf(displayMetrics.widthPixels));
                jsonObject.put("y", Integer.valueOf(displayMetrics.heightPixels));
                jsonObject.put("name", "");
            } catch (JSONException e) {
            }
        } else {
            Point screenSize = new Point();
            WindowManager windowManager = (WindowManager) PiLoopApp.getContext().getSystemService(Context.WINDOW_SERVICE);
            Display display = windowManager.getDefaultDisplay();
            display.getRealSize(screenSize);
            try {
                jsonObject.put("x", screenSize.x);
                jsonObject.put("y", screenSize.y);
                jsonObject.put("name", display.getName());
            } catch (JSONException e) {
            }
        }
        return jsonObject;
    }

    @Override
    public String getName() {
        return "display";
    }
}
