package com.example.beatbox.Model;

import java.io.File;

public class Music {

    private String mNameMusic;
    private String mPathMusic;
    private Integer musicId;

    public String getNameMusic() {
        return mNameMusic;
    }

    public void setNameMusic(String nameMusic) {
        mNameMusic = nameMusic;
    }

    public String getPathMusic() {
        return mPathMusic;
    }

    public void setPathMusic(String pathMusic) {
        mPathMusic = pathMusic;
    }

    public Integer getMusicId() {
        return musicId;
    }

    public void setMusicId(Integer musicId) {
        this.musicId = musicId;
    }

    public Music(String pathMusic) {
        mPathMusic = pathMusic;

        String []sections = mPathMusic.split(File.separator);
        String fileNameWithExtension = sections[sections.length - 1];
        int lastDotIndex = fileNameWithExtension.lastIndexOf(".");
        mNameMusic = fileNameWithExtension.substring(0 , lastDotIndex);
    }

    public Music() {
    }


}
