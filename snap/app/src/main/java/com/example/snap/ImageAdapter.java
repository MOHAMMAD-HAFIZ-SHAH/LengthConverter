package com.example.snap;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.io.File;
import java.util.ArrayList;

public class ImageAdapter extends BaseAdapter {

    Context context;
    ArrayList<File> imageFiles;

    public ImageAdapter(Context c, ArrayList<File> files) {
        context = c;
        imageFiles = files;
    }

    public int getCount() {
        return imageFiles.size();
    }

    public Object getItem(int position) {
        return imageFiles.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = new ImageView(context);
        imageView.setImageURI(android.net.Uri.fromFile(imageFiles.get(position)));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(300, 300));
        return imageView;
    }
}
