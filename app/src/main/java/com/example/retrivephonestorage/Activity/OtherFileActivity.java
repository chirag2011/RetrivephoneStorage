package com.example.retrivephonestorage.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;

import com.example.retrivephonestorage.Adapter.DocumentAdapter;
import com.example.retrivephonestorage.Interface.onPadfSelectListener;
import com.example.retrivephonestorage.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class OtherFileActivity extends AppCompatActivity implements onPadfSelectListener {

    private DocumentAdapter documentAdapter;
    private List<File> pdfList;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_file);

        recyclerView = findViewById(R.id.xRecyclerviewOther);
        displayPdf();
    }

    public ArrayList<File> findPdf(File file){
        ArrayList<File> arrayList = new ArrayList<>();
        File[] files = file.listFiles();

        for (File singleFile: files){
            if (singleFile.isDirectory() && !singleFile.isHidden()){
                arrayList.addAll(findPdf(singleFile));
            }
            else {
                if (singleFile.getName().endsWith(".txt") || singleFile.getName().endsWith(".py")){
                    arrayList.add(singleFile);
                }
            }
        }
        return arrayList;
    }

    private void displayPdf() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        pdfList = new ArrayList<>();
        pdfList.addAll(findPdf(Environment.getExternalStorageDirectory()));
        documentAdapter = new DocumentAdapter(this,pdfList,this);
        recyclerView.setAdapter(documentAdapter);
    }

    @Override
    public void onPdfSelected(File file) {
        startActivity(new Intent(OtherFileActivity.this, DocumentShowActivity.class)
                .putExtra("path",file.getAbsolutePath()));
    }
}