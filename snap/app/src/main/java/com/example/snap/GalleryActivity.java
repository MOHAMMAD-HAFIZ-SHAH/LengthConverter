package com.example.snap;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.util.ArrayList;

public class GalleryActivity extends AppCompatActivity {

    GridView gridView;
    ArrayList<File> imageFiles;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        gridView = findViewById(R.id.gridView);
        File folder = getExternalFilesDir(android.os.Environment.DIRECTORY_PICTURES);
        imageFiles = new ArrayList<>();
        if (folder != null && folder.listFiles() != null) {
            for (File file : folder.listFiles()) {
                if (file.getName().endsWith(".jpg")) {
                    imageFiles.add(file);
                }
            }
        }

        ImageAdapter adapter = new ImageAdapter(this, imageFiles);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener((parent, view, position, id) -> {
            Intent i = new Intent(GalleryActivity.this, ImageDetailsActivity.class);
            i.putExtra("imagePath", imageFiles.get(position).getAbsolutePath());
            startActivity(i);
        });
    }
}
