package com.example.beatbox.Controller.Fragment;

import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.beatbox.Model.Music;
import com.example.beatbox.R;

import java.util.List;


public class music_player_Fragment extends Fragment {

    private RecyclerView mRecyclerView;
    //private musicRepository mMusicRepository;
    private MediaPlayer MusicPlayer;



    public music_player_Fragment() {

    }

    public static music_player_Fragment newInstance() {
        music_player_Fragment fragment = new music_player_Fragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       // mMusicRepository = musicRepository.getInstance(getActivity());
        MusicPlayer = MediaPlayer.create(getActivity() ,R.raw.first);
        MusicPlayer = MediaPlayer.create(getActivity() , R.raw.second);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_music_player_, container, false);

        findViews(view);
        initViews();
        setAdapter();
        return view;
    }

    private void findViews(View view) {
        mRecyclerView = view.findViewById(R.id.music_player_recyclerView);

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

    private void setAdapter() {

        List<Music> music = (List<Music>) MusicPlayer;
       MusicAdapter adapter = new MusicAdapter(music);
       mRecyclerView.setAdapter(adapter);

      //  List<Music> music = mMusicRepository.getMusic();
       // MusicAdapter adapter = new  MusicAdapter(music);
        //mRecyclerView.setAdapter(adapter);
    }





    private class MusicHolder extends RecyclerView.ViewHolder{

        private Button mButton;
        private Music mMusic;

        public MusicHolder(@NonNull View itemView) {
            super(itemView);

            mButton = itemView.findViewById(R.id.btn_beat_box);
            mButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MusicPlayer.start();
               //     mMusicRepository.playMusic(mMusic);
                }
            });
        }

        public void bindMusic(Music music){
            mMusic = music;
            mButton.setText(mMusic.getNameMusic());

        }


    }


    private class MusicAdapter extends RecyclerView.Adapter<MusicHolder>{

      private List<Music> mMusic ;

        public MusicAdapter(List<Music> music) {
            mMusic = music;
        }

        public List<Music> getMusic() {
            return mMusic;
        }

        public void setMusic(List<Music> music) {
            mMusic = music;
        }

        @NonNull
        @Override
        public MusicHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(getContext()).
                    inflate(R.layout.item_list_sound,parent,false);
            return new MusicHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MusicHolder holder, int position) {

            Music music = mMusic.get(position);
            holder.bindMusic(music);

        }

        @Override
        public int getItemCount() {
            return mMusic.size();
        }
    }
}