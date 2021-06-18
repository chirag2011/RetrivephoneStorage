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

import com.bumptech.glide.Glide;
import com.example.retrivephonestorage.Activity.VideoPlayerActivity;
import com.example.retrivephonestorage.R;

import java.io.File;
import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {

    private Context context;
    public static List<File> allMediaListVideo;

    public VideoAdapter(Context context, List<File> allMediaListVideo) {
        this.context = context;
        this.allMediaListVideo = allMediaListVideo;
    }

    @NonNull
    @Override
    public VideoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoAdapter.ViewHolder holder, int position) {

        ((ViewHolder)holder).xTextviewTitle.setText(allMediaListVideo.get(position).getName());
        Uri uri = Uri.fromFile(allMediaListVideo.get(position));

        Glide.with(context).load(uri).thumbnail(0.1f).into(((ViewHolder)holder).xImageviewImage);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, VideoPlayerActivity.class);
                intent.putExtra("position",position);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return allMediaListVideo.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView xImageviewImage;
        TextView xTextviewTitle;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            xImageviewImage = itemView.findViewById(R.id.xVideoImage);
            xTextviewTitle = itemView.findViewById(R.id.xVideoTitle);
            cardView = itemView.findViewById(R.id.xVideoCardciew);
        }
    }
}
