package it.ttlab.piloopapp.deviceinfo;


import org.json.JSONObject;

import static it.ttlab.piloopapp.deviceinfo.Utils.readFile;

public abstract class FileProperty implements Property {

    @Override
    public Object getProperty() {
        try {
           return readFile(getFilename());
        } catch (Exception e) {
            return JSONObject.NULL;
        }
    }

    protected abstract String getFilename();
}
