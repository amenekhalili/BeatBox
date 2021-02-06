package com.example.beatbox.Controller.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.example.beatbox.Controller.Fragment.BeatBoxFragment;
import com.example.beatbox.R;

public class Beat_box_Activity extends Single_fragment_Activity {


    @Override
    public Fragment CreateFragment() {
        return BeatBoxFragment.newInstance();
    }
}