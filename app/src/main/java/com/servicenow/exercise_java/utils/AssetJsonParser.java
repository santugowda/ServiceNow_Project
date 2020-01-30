package com.servicenow.exercise_java.utils;

import android.content.Context;
import android.util.Log;

import com.servicenow.exercise_java.model.ReviewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class AssetJsonParser {

    private static final String TAG = AssetJsonParser.class.getSimpleName();

    private static String readFile(Context context) throws IOException {
        BufferedReader reader;
        reader = new BufferedReader(new InputStreamReader(context.getAssets().open("reviews_offline.json"), "UTF-8"));
        StringBuilder content = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            content.append(line);
        }
        Log.d(TAG, "read file");
        return content.toString();
    }

    public static ArrayList<ReviewModel> getAssetJsonData(Context context) throws IOException, JSONException {
        String jsonFileContent = readFile(context);

        JSONArray jsonArray = new JSONArray(jsonFileContent);
        ArrayList<ReviewModel> reviewModels = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObj = jsonArray.getJSONObject(i);
            String name = jsonObj.getString("name");
            String review = jsonObj.getString("review");
            int rating = jsonObj.getInt("rating");
            String location = jsonObj.getString("location");
            reviewModels.add(new ReviewModel(name, review, rating, location));
        }

        Log.d(TAG, "parse json file to get review models");
        return reviewModels;
    }
}
