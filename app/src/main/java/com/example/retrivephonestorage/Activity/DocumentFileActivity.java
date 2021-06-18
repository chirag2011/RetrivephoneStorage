package com.example.retrivephonestorage.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;

import com.example.retrivephonestorage.Adapter.DocumentAdapter;
import com.example.retrivephonestorage.Interface.onPadfSelectListener;
import com.example.retrivephonestorage.R;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DocumentFileActivity extends AppCompatActivity implements onPadfSelectListener {

   // private FragmentAdapter adapter;
    private DocumentAdapter documentAdapter;
    private List<File> pdfList;
    private RecyclerView recyclerView;

    private Button xPdfFile;
    private Button xWordFile;
    private Button xExcelFile;
    private Button xPptFile;
    private Button xOtherFile;

    // ViewPager viewPager2;
   // TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document_file);

        recyclerView = findViewById(R.id.xRecyclerview);

        xPdfFile = findViewById(R.id.xBtnPdf);
        xWordFile = findViewById(R.id.xbtnWord);
        xExcelFile = findViewById(R.id.xBtnExcel);
        xPptFile = findViewById(R.id.xBtnPpt);
        xOtherFile = findViewById(R.id.xBtnOther);

        xPdfFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DocumentFileActivity.this,PdfFileActivity.class);
                startActivity(intent);
            }
        });

        xWordFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DocumentFileActivity.this,WordFileActivity.class);
                startActivity(intent);
            }
        });

        xExcelFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DocumentFileActivity.this,ExcelFileActivity.class);
                startActivity(intent);
            }
        });

        xPptFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DocumentFileActivity.this,PptFileActivity.class);
                startActivity(intent);
            }
        });

        xOtherFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(DocumentFileActivity.this,OtherFileActivity.class);
               startActivity(intent);
            }
        });


        // viewPager2 = findViewById(R.id.xViewPager);
        //tabLayout = findViewById(R.id.xTabLayout);

       /* FragmentManager fm = getSupportFragmentManager();
        adapter = new FragmentAdapter(fm, getLifecycle());
        viewPager2.setAdapter(adapter);*/

        //adapter = new FragmentAdapter(getSupportFragmentManager());
        //viewPager2.setAdapter(adapter);

       // tabLayout.setupWithViewPager(viewPager2);

        /*tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });*/
        runtimePermission();
    }

    private void runtimePermission() {

        Dexter.withContext(DocumentFileActivity.this).withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        displayPdf();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {

                    }
                }).check();
    }

    public ArrayList<File> findPdf(File file){
        ArrayList<File> arrayList = new ArrayList<>();
        File[] files = file.listFiles();

        for (File singleFile: files){
            if (singleFile.isDirectory() && !singleFile.isHidden()){
                arrayList.addAll(findPdf(singleFile));
            }
            else {
                if (singleFile.getName().endsWith(".pdf") || singleFile.getName().endsWith(".txt") || singleFile.getName().endsWith(".pptx") || singleFile.getName().endsWith(".doc") || singleFile.getName().endsWith(".docx") || singleFile.getName().endsWith(".xls") || singleFile.getName().endsWith(".xlsx") || singleFile.getName().endsWith(".py") || singleFile.getName().endsWith(".pptx")){
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
        startActivity(new Intent(DocumentFileActivity.this, DocumentShowActivity.class)
                .putExtra("path",file.getAbsolutePath()));
    }
}