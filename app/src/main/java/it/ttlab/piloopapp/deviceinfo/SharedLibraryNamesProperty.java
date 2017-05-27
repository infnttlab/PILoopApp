package it.ttlab.piloopapp.deviceinfo;

import android.content.pm.PackageManager;

import org.json.JSONArray;

import it.ttlab.piloopapp.PiLoopApp;


public class SharedLibraryNamesProperty implements Property {
    @Override
    public Object getProperty() {
        JSONArray jsonArray = new JSONArray();

        PackageManager pm = PiLoopApp.getContext().getPackageManager();
        String[] libraries = pm != null ? pm.getSystemSharedLibraryNames() : new String[0];
        for(String library: libraries) {
            jsonArray.put(library);
        }
        return jsonArray;
    }

    @Override
    public String getName() {
        return "sharedlibraries";
    }
}
