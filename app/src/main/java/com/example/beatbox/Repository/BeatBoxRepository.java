package com.example.beatbox.Repository;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

import com.example.beatbox.Model.Sound;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class BeatBoxRepository {
    private static String Asset_folder = "sound";
    private static BeatBoxRepository sInstance;
    private Context mContext ;
    private SoundPool mSoundPool;
    private List<Sound> mSounds = new ArrayList<>();

    public static BeatBoxRepository getInstance(Context context) {

        if(sInstance == null)
            sInstance = new BeatBoxRepository(context);
        return sInstance;
    }

    private BeatBoxRepository(Context context) {
          mContext = context.getApplicationContext();
          mSoundPool = new SoundPool(5 , AudioManager.STREAM_MUSIC , 0);
          loudSounds();
    }


    public List<Sound> getSounds() {
        return mSounds;
    }

    private void loudSounds() {

        AssetManager assetManager = mContext.getAssets();
        ;
        try {
            String [] FileName = assetManager.list(Asset_folder);
            for(String filename : FileName){
                Log.i("BeatBox" , filename);

                String assetPAth = Asset_folder + File.separator + filename;
                Sound sound = new Sound(assetPAth);

                AssetFileDescriptor afd = assetManager.openFd(sound.getPathFile());
                int soundId = mSoundPool.load(afd , 1);
                sound.setSoundId(soundId);
                mSounds.add(sound);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void playSound(Sound sound){
        if(sound == null || sound.getSoundId() == null)
            return;

        int playState = mSoundPool.play(sound.getSoundId(),
                1.0f ,
                1.0f,
                1,
                0,
                1.0f
                );
    }

    public void releaseSoundPool(){
        mSoundPool.release();
    }

}
