package it.ttlab.piloopapp.deviceinfo;

import android.util.Pair;

import org.json.JSONException;

import java.util.List;

public interface Property {
    Object getProperty() throws JSONException;
    //public List<Pair<String,String>> getKeyValuePairPropertyList() throws JSONException;
    String getName();
}
