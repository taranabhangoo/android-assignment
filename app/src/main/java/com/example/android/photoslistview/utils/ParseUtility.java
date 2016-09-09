package com.example.android.photoslistview.utils;

import android.util.Log;

import com.example.android.photoslistview.models.PhotoBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by Dell I7 on 9/8/2016.
 */
public class ParseUtility {

    public static ArrayList<PhotoBean> parsePhotos(String response){
        if (response == null) {
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<PhotoBean>>(){}.getType();
        ArrayList<PhotoBean> photosList = gson.fromJson(response, type);

        Log.e(ParseUtility.class.getSimpleName(), photosList.toString());
        return photosList;
    }
}
