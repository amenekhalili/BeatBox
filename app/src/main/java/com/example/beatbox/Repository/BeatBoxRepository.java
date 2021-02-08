package com.example.beatbox.Repository;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import com.example.beatbox.Model.Sound;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BeatBoxRepository {
    private static String Asset_folder = "sound";
    private static BeatBoxRepository sInstance;
    private Context mContext ;

    public static BeatBoxRepository getInstance(Context context) {

        if(sInstance == null)
            sInstance = new BeatBoxRepository(context);
        return sInstance;
    }

    private BeatBoxRepository(Context context) {
          mContext = context.getApplicationContext();
    }

    public List<Sound> getSound(){
        List<Sound> sounds = new ArrayList<>();

        AssetManager assetManager = mContext.getAssets();
        try {
            String [] FileName = assetManager.list(Asset_folder);
            for(String filename : FileName){
                Log.i("BeatBox" , filename);

                String assetPAth = Asset_folder + File.separator + filename;
                Sound sound = new Sound(assetPAth);
                sounds.add(sound);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sounds;
    }
}
