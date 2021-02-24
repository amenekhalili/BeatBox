package com.example.beatbox.Repository;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

import com.example.beatbox.Model.Music;
import com.example.beatbox.Model.Sound;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class musicRepository {

    private static String Asset_folder = "music";
    private static musicRepository sInstance;
    private Context mContext;
    private List<Music> mMusic = new ArrayList<>();
    private SoundPool mSoundPool;

    public static musicRepository getInstance(Context context) {

        if (sInstance == null)
            sInstance = new musicRepository(context);
        return sInstance;
    }

    public musicRepository(Context context) {
     mContext = context.getApplicationContext();

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            mSoundPool = new SoundPool.Builder().build();
        } else {
            mSoundPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        }

        loudMusic();

    }

    public List<Music> getMusic() {
        return mMusic;
    }

    private void  loudMusic(){
        AssetManager assetManager = mContext.getAssets();
        try {
            String[] FileName = assetManager.list(Asset_folder);
            for (String filename : FileName) {
                Log.i("BeatBox", filename);

                String assetPAth = Asset_folder + File.separator + filename;
                Music music = new Music(assetPAth);

                AssetFileDescriptor afd = assetManager.openFd(music.getPathMusic());

                int soundId = mSoundPool.load(afd, 1);
                music.setMusicId(soundId);
               mMusic.add(music);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void playMusic(Music music) {
        if (music == null || music.getMusicId() == null)
            return;

        int playState = mSoundPool.play(music.getMusicId(),
                1.0f,
                1.0f,
                1,
                0,
                1.0f
        );
    }

    public void releaseSoundPool() {
        mSoundPool.release();
    }

}
