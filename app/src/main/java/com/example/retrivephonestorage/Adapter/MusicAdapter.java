package com.example.retrivephonestorage.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrivephonestorage.Activity.AudioPlayerActivity;
import com.example.retrivephonestorage.R;

import java.io.File;
import java.util.List;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.ViewHolder> {

    private Context context;
    public static List<File> allMediaListAudio;

    public MusicAdapter(Context context, List<File> allMediaListAudio) {
        this.context = context;
        this.allMediaListAudio = allMediaListAudio;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.music_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ((MusicAdapter.ViewHolder)holder).musicname.setText(allMediaListAudio.get(position).getName());
        Uri uri = Uri.fromFile(allMediaListAudio.get(position));

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(MediaStore.INTENT_ACTION_MUSIC_PLAYER);
                Intent intent = new Intent(context, AudioPlayerActivity.class);
                intent.putExtra("music",position);
                context.startActivity(intent);
            }
        });

       // Glide.with(context).load(uri).thumbnail(0.1f).into(((ViewHolder)holder).musicimage);

           /* holder.musicname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer player = MediaPlayer.create(v.getContext(),Uri.parse(musicModels.get(position).getPath()));
                player.start();
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return allMediaListAudio.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView musicname;
        private ImageView musicimage;
        private CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            musicname = itemView.findViewById(R.id.xTvMusicName);
            musicimage = itemView.findViewById(R.id.xMusicImage);
            cardView = itemView.findViewById(R.id.xMusicCardciew);
        }
    }

}
