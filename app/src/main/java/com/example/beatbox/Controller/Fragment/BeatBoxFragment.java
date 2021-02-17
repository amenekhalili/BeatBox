package com.example.beatbox.Controller.Fragment;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.beatbox.Model.Sound;
import com.example.beatbox.R;
import com.example.beatbox.Repository.BeatBoxRepository;

import java.util.List;


public class BeatBoxFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private BeatBoxRepository mBeatBoxRepository;

    public BeatBoxFragment() {
        // Required empty public constructor
    }



    public static BeatBoxFragment newInstance( ) {
        BeatBoxFragment fragment = new BeatBoxFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        mBeatBoxRepository = BeatBoxRepository.getInstance(getActivity());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mBeatBoxRepository.releaseSoundPool();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_beat_box, container, false);
        findViews(view);
        initViews();
        setAdapter();
        return view;
    }

    private void findViews(View view) {
        mRecyclerView = view.findViewById(R.id.recycler_view_beat_box);
    }

    private void initViews() {
        int spanCount = 0 ;

        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
         spanCount = 4 ;
        } else {
            spanCount = 3;
        }
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext() ,spanCount));
    }


    private void setAdapter(){
        List<Sound> sounds = mBeatBoxRepository.getSounds();
        SoundAdapter adapter = new SoundAdapter(sounds);
        mRecyclerView.setAdapter(adapter);

    }
    private class SoundHolder extends RecyclerView.ViewHolder{

        private Button mButton;
        private Sound mSound;

        public SoundHolder(@NonNull View itemView) {
            super(itemView);

            mButton = itemView.findViewById(R.id.btn_beat_box);
            mButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mBeatBoxRepository.playSound(mSound);
                }
            });

        }

        public void bindSound(Sound sound){
            mSound = sound;
            mButton.setText(mSound.getName());
        }
    }


    private class SoundAdapter extends RecyclerView.Adapter<SoundHolder>{

        private List<Sound> mSounds;

        public List<Sound> getSounds() {
            return mSounds;
        }

        public void setSounds(List<Sound> sounds) {
            mSounds = sounds;
        }

        public SoundAdapter(List<Sound> sounds) {
            mSounds = sounds;
        }

        @NonNull
        @Override
        public SoundHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getContext()).
                    inflate(R.layout.item_list_sound , parent , false);

            return new SoundHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull SoundHolder holder, int position) {

            Sound sound = mSounds.get(position);
            holder.bindSound(sound);
        }

        @Override
        public int getItemCount() {
            return mSounds.size();
        }
    }
}