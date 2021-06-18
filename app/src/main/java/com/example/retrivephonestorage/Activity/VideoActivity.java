package com.example.retrivephonestorage.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.example.retrivephonestorage.Adapter.VideoAdapter;
import com.example.retrivephonestorage.Class.StorageUtil;
import com.example.retrivephonestorage.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class VideoActivity extends AppCompatActivity {

    private File storage;
    private String[] storagePaths;
    private List<File> allMediaListVideo;

    public static String[] videoExtensions = {".mp4",".ts",".mkv",".mov",
            ".3gp",".mv2",".m4v",".webm",".mpeg1",".mpeg2",".mts",".ogm",
            ".bup", ".dv",".flv",".m1v",".m2ts",".mpeg4",".vlc",".3g2",
            ".avi",".mpeg",".mpg",".wmv",".asf"};

    private RecyclerView recyclerView;
    private VideoAdapter videoAdapter;
    private Uri fileUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        recyclerView = findViewById(R.id.xRecyclerviewVideo);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        recyclerView.setNestedScrollingEnabled(false);
        allMediaListVideo = new ArrayList<>();
        videoAdapter = new VideoAdapter(this,allMediaListVideo);
        recyclerView.setAdapter(videoAdapter);

        storagePaths = StorageUtil.getStorageDirectories(this);

        for (String path : storagePaths) {
            storage = new File(path);
            load_video_file(storage);
        }

        checkStorageAccessPermission();
    }

    private void load_video_file(File storage) {
        File[] fileList = storage.listFiles();
        if(fileList != null && fileList.length > 0){
            for (int i=0; i<fileList.length; i++){
                if(fileList[i].isDirectory()){
                    load_video_file(fileList[i]);
                }
                else {
                    String name = fileList[i].getName().toLowerCase();
                    for (String extension: videoExtensions){
                        if(name.endsWith(extension)){
                            allMediaListVideo.add(fileList[i]);
                            break;
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                storagePaths = StorageUtil.getStorageDirectories(this);

                for (String path : storagePaths) {
                    storage = new File(path);
                    load_video_file(storage);
                }

                videoAdapter.notifyDataSetChanged();
            }
        }
    }

    private void checkStorageAccessPermission() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                new androidx.appcompat.app.AlertDialog.Builder(this)
                        .setTitle("Permission Needed")
                        .setMessage("This permission is needed to access media file in your phone")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityCompat.requestPermissions(VideoActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                            }
                        }).setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).show();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            }
        }
        else {

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case 1:
                if (resultCode == Activity.RESULT_OK){
                    fileUri  = data.getData();
                    getContentResolver().takePersistableUriPermission(fileUri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
                }
        }
    }
}