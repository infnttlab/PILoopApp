package it.ttlab.piloopapp.deviceinfo;

import org.json.JSONException;

public interface Property {
    public Object getProperty() throws JSONException;
    public String getName();
}
