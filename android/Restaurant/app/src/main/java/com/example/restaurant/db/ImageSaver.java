package com.example.restaurant.db;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import androidx.annotation.NonNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * To Save:
 * new ImageSaver(context)
 * .setFileName("myImage.png")
 * .setDirectoryName("images")
 * .save(bitmap);
 * <p>
 * To Load:
 * Bitmap bitmap = new ImageSaver(context)
 * .setFileName("myImage.png")
 * .setDirectoryName("images")
 * .load();
 */
public class ImageSaver {

    private final Context context;
    private String directoryName = "images";
    private String fileName = "image.png";

    public ImageSaver(Context context) {
        this.context = context;
    }

    public ImageSaver setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public ImageSaver setDirectoryName(String directoryName) {
        this.directoryName = directoryName;
        return this;
    }

    public boolean exists() {
        return createFile().exists();
    }

    public void save(Bitmap bitmapImage) {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(createFile());
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @NonNull
    private File createFile() {
        File directory = context.getDir(directoryName, Context.MODE_PRIVATE);

        if (!directory.exists() && !directory.mkdirs()) {
            Log.e("ImageSaver", "Error creating directory " + directory);
        }

        return new File(directory, fileName);
    }

    public Bitmap load() {
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(createFile());
            return BitmapFactory.decodeStream(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}