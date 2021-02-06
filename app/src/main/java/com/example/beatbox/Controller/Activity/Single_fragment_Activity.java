package com.example.beatbox.Controller.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.example.beatbox.R;

public abstract class Single_fragment_Activity extends AppCompatActivity {

    public abstract Fragment CreateFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        FragmentManager fragmentManager = getSupportFragmentManager();

        Fragment fragment = fragmentManager.findFragmentById(R.id.container_fragment);

        if(fragment == null){

            fragmentManager
                    .beginTransaction()
                    .add(R.id.container_fragment ,CreateFragment())
                    .commit();
        }



    }
}