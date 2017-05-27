package it.ttlab.piloopapp.deviceinfo;

import android.content.pm.FeatureInfo;
import android.content.pm.PackageManager;

import org.json.JSONArray;

import it.ttlab.piloopapp.PiLoopApp;


public class FeaturesProperty implements Property {
    @Override
    public Object getProperty() {
        JSONArray jsonArray = new JSONArray();

        PackageManager pm = PiLoopApp.getContext().getPackageManager();
        FeatureInfo[] features = pm != null ? pm.getSystemAvailableFeatures() : new FeatureInfo[0];
        for(FeatureInfo feature: features) {
            jsonArray.put(feature.name);
        }
        return jsonArray;
    }

    @Override
    public String getName() {
        return "feature";
    }
}
