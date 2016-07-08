package com.google.sample.cloudvision;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by root on 7/7/16.
 */
public class GridViewAdapter extends ArrayAdapter {
    private Context context;
    private SharedPreferences mSettings;
    private int layoutResourceId;
    private ArrayList data = new ArrayList();
    private int i = 0;

    public GridViewAdapter(Context context, int layoutResourceId, ArrayList data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new ViewHolder();
//            holder.imageTitle = (TextView) row.findViewById(R.id.text);
            holder.image = (ImageView) row.findViewById(R.id.image);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

//        ImageItem item = (ImageItem)data.get(position);
//        holder.imageTitle.setText(item.getTitle());
//        holder.image.setImageBitmap(item.getImage());
        mSettings = PreferenceManager.getDefaultSharedPreferences(context);
        String size = mSettings.getString("size", "0");
        Log.d("adaptersize", size);
        if (i < Integer.parseInt(size)) {
//            loadImageFromStorage(i, holder.image);
            try {
                String path = mSettings.getString("path", "empty");
                Log.d("path",path);
                File f = new File(path, String.valueOf(i)+".jpg");
                Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));

                holder.image.setImageBitmap(b);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            i++;
        }

        Log.d("ival", String.valueOf(i));

        return row;
    }

    static class ViewHolder {
        //        TextView imageTitle;
        ImageView image;
    }

//    private void loadImageFromStorage(int val, ImageView img) {
//
//        Log.d("val", String.valueOf(val));
//
//        try {
//            String path = mSettings.getString("path", "empty");
//            Log.d("path",path);
//            File f = new File(path, "2"+".jpg");
//            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
//
//            img.setImageBitmap(b);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
}