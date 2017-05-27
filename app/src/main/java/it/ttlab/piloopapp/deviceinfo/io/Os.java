package it.ttlab.piloopapp.deviceinfo.io;

public interface Os {
    public StructStat lstat(String path) throws ErrnoException;
}
