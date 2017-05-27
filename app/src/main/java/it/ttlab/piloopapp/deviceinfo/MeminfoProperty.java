package it.ttlab.piloopapp.deviceinfo;

public class MeminfoProperty extends FileProperty {
    @Override
    public String getName() {
        return "meminfo";
    }

    @Override
    protected String getFilename() {
        return "/proc/meminfo";
    }
}
