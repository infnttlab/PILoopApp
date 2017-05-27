package it.ttlab.piloopapp.deviceinfo;

public class CpuinfoProperty extends FileProperty {
    @Override
    public String getName() {
        return "cpuinfo";
    }

    @Override
    protected String getFilename() {
        return "/proc/cpuinfo";
    }
}
