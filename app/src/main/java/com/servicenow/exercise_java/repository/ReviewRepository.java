package com.servicenow.exercise_java.repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.servicenow.exercise_java.interfaces.ApiEndpointInterface;
import com.servicenow.exercise_java.model.ReviewModel;
import com.servicenow.exercise_java.utils.AssetJsonParser;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReviewRepository {

    private static final String TAG = ReviewRepository.class.getSimpleName();
    private static ReviewRepository reviewRepository;
    private ApiEndpointInterface apiEndpointInterface;
    private Context mContext;

    public ReviewRepository() {
        apiEndpointInterface = APIClient.getClient(ApiEndpointInterface.class);
    }

    public static ReviewRepository getInstance() {
        if (reviewRepository == null) {
            reviewRepository = new ReviewRepository();
            Log.d(TAG, "get review repository instance");
        }
        return reviewRepository;
    }

    public MutableLiveData<ArrayList<ReviewModel>> getReviews(Context context) {
        MutableLiveData<ArrayList<ReviewModel>> reviewsLiveData = new MutableLiveData<>();
        apiEndpointInterface.fetchReviews().enqueue(new Callback<ArrayList<ReviewModel>>() {
            @Override
            public void onResponse(@NotNull Call<ArrayList<ReviewModel>> call, @NotNull Response<ArrayList<ReviewModel>> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "Retrofit call success with response: " + response.body().toString());
                    reviewsLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ReviewModel>> call, Throwable t) {
                //read data for offline view via asset file
                ArrayList<ReviewModel> offlineJson = null;
                try {
                    offlineJson = AssetJsonParser.getAssetJsonData(context);
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
                reviewsLiveData.setValue(offlineJson);
                Log.d(TAG, "Retrofit call failed with error, offline data: " + offlineJson);
            }
        });

        return reviewsLiveData;
    }
}
