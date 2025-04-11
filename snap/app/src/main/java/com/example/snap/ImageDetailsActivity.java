package com.example.snap;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.text.DateFormat;
import java.util.Date;

public class ImageDetailsActivity extends AppCompatActivity {

    ImageView imageView;
    TextView detailsText;
    Button deleteButton;
    String imagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_details);

        imageView = findViewById(R.id.imageView);
        detailsText = findViewById(R.id.detailsText);
        deleteButton = findViewById(R.id.deleteButton);

        imagePath = getIntent().getStringExtra("imagePath");
        File file = new File(imagePath);
        imageView.setImageURI(android.net.Uri.fromFile(file));

        String details = "Name: " + file.getName() + "\n"
                + "Path: " + file.getAbsolutePath() + "\n"
                + "Size: " + file.length() / 1024 + " KB\n"
                + "Date: " + DateFormat.getDateTimeInstance().format(new Date(file.lastModified()));

        detailsText.setText(details);

        deleteButton.setOnClickListener(v -> {
            new AlertDialog.Builder(this)
                    .setTitle("Delete Image")
                    .setMessage("Are you sure you want to delete this image?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        if (file.delete()) {
                            finish();
                        }
                    })
                    .setNegativeButton("No", null)
                    .show();
        });
    }
}
