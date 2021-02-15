package com.example.beatbox.Model;

import java.io.File;

public class Sound {
    private String mName;
    private String mPathFile;
    private Integer mSoundId;

    public Integer getSoundId() {
        return mSoundId;
    }

    public void setSoundId(Integer soundId) {
        mSoundId = soundId;
    }

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

    public Sound( String pathFile) {

        mPathFile = pathFile;
        String []sections = pathFile.split(File.separator);
        String fileNameWithExtension = sections[sections.length - 1];
        int lastDotIndex = fileNameWithExtension.lastIndexOf(".");
        mName = fileNameWithExtension.substring(0 , lastDotIndex);
    }

    public Sound() {
    }
}
