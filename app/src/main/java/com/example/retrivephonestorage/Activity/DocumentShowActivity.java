package com.example.retrivephonestorage.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;

import com.example.retrivephonestorage.R;
import com.github.barteksc.pdfviewer.PDFView;

import java.io.File;

public class DocumentShowActivity extends AppCompatActivity {

    String filePath = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document_show);

        PDFView pdfView = findViewById(R.id.xPdfView);
        filePath = getIntent().getStringExtra("path");

        File file = new File(filePath);
        Uri path = Uri.fromFile(file);
        pdfView.fromUri(path).load();

    }
}