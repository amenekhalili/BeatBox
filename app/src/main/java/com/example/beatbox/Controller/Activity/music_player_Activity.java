package com.example.beatbox.Controller.Activity;

import androidx.fragment.app.Fragment;

import com.example.beatbox.Controller.Fragment.music_player_Fragment;

public class music_player_Activity extends Single_fragment_Activity {


    @Override
    public Fragment CreateFragment() {
        return music_player_Fragment.newInstance();
    }
}