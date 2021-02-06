package com.example.beatbox.Model;

public class Sound {
    private String mName;
    private String mPathFile;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getPathFile() {
        return mPathFile;
    }

    public void setPathFile(String pathFile) {
        mPathFile = pathFile;
    }

    public Sound(String name, String pathFile) {
        mName = name;
        mPathFile = pathFile;
    }

    public Sound() {
    }
}
