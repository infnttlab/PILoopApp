package it.ttlab.piloopapp.deviceinfo;


import org.json.JSONException;
import org.json.JSONObject;

public class DeviceInformation {
    private static DeviceInformation singleton = null;

    public static DeviceInformation getInstance() {
        if(singleton==null)
            singleton = new DeviceInformation();
        return singleton;
    }

    private DeviceInformation() {
    }

    public JSONObject getDeviceInformation() {
        JSONObject jsonObject = new JSONObject();
        addProperty(jsonObject, new MeminfoProperty());
        addProperty(jsonObject, new MountsProperty());
        addProperty(jsonObject, new CpuinfoProperty());
        addProperty(jsonObject, new EnvironmentProperty());
        addProperty(jsonObject, new JavaSystemProperty());
        addProperty(jsonObject, new FeaturesProperty());
        addProperty(jsonObject, new DisplayProperty());
        addProperty(jsonObject, new UsbProperty());
        addProperty(jsonObject, new GetPropProperty());
        addProperty(jsonObject, new PackageSigProperty());
        addProperty(jsonObject, new OtacertsProperty());
        addProperty(jsonObject, new DirProperty());
        addProperty(jsonObject, new VersionProperty());
        addProperty(jsonObject, new SharedLibraryNamesProperty());
        addProperty(jsonObject, new NameProperty());

        return jsonObject;
    }

    private void addProperty(JSONObject jsonObject, Property property) {
        if(jsonObject.has(property.getName())) {
            throw new RuntimeException("property already exists");
        }
        try {
            jsonObject.put(property.getName(), property.getProperty());
        } catch (JSONException e) {
            try {
                jsonObject.put(property.getName(), JSONObject.NULL);
            } catch (JSONException e1) {
                e1.printStackTrace();
            }
        }

    }
}
