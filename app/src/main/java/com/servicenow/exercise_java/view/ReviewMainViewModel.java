package com.servicenow.exercise_java.view;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.servicenow.exercise_java.model.ReviewModel;
import com.servicenow.exercise_java.network.ReviewRepository;

import java.util.ArrayList;

public class ReviewMainViewModel extends ViewModel {

    private static final String TAG = ReviewMainViewModel.class.getSimpleName();
    private MutableLiveData<ArrayList<ReviewModel>> reviewsLiveData;
    private ReviewRepository reviewRepository;

    public void initViewModel() {
        if (reviewsLiveData != null) {
            return;
        }
        reviewRepository = ReviewRepository.getInstance();
        reviewsLiveData = reviewRepository.getReviews();
    }

    public MutableLiveData<ArrayList<ReviewModel>> getReviewsLiveData() {
        Log.d(TAG, "get review data");
        return reviewsLiveData;
    }
}