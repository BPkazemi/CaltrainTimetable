package com.bpourkazemi.caltraintimetable.utils;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by babakpourkazemi on 7/20/14.
 */
public class FileUtils {

    public static String[] readEntireFile( Context context, String filename, boolean eatHeaders ) {
        String[] rows = null;
        AssetManager assetManager = context.getAssets();

        InputStream is = null;
        try {
            is = assetManager.open(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder stringBuilder = new StringBuilder();

        try {
            int value;
            char c;

            if (eatHeaders) { reader.readLine(); }

            while ((value = reader.read()) != -1) {
                c = (char) value;
                stringBuilder.append(c);
            }

            String result = stringBuilder.toString();
            rows = result.split("\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                is.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

        return rows;
    }

}
