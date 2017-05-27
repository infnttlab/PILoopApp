package it.ttlab.piloopapp.deviceinfo;

public class MountsProperty extends FileProperty {
    @Override
    public String getName() {
        return "mounts";
    }

    @Override
    protected String getFilename() {
        return "/proc/mounts";
    }
}
