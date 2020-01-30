package com.servicenow.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.servicenow.exercise_java.model.ReviewModel;
import com.servicenow.exercise_java.repository.ReviewRepository;

import java.util.ArrayList;


/**
 * ViewModel class helps in storing data to survive configuration change such as screen
 * rotations and manage UI related data in accordance with activity/fragment lifecycle.
 */
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

    /**
     * Expose the LiveData Projects query so the UI can observe it.
     */
    public MutableLiveData<ArrayList<ReviewModel>> getReviewsLiveData() {
        Log.d(TAG, "get review data");
        return reviewsLiveData;
    }
}