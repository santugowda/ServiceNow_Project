package com.servicenow.exercise_java.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.servicenow.exercise.R;
import com.servicenow.exercise_java.model.ReviewModel;
import com.servicenow.exercise_java.utils.ServiceNowConstants;
import com.servicenow.viewmodel.ReviewMainViewModel;
import com.servicenow.exercise_java.view.adapter.ReviewAdapter;

import java.util.ArrayList;

public class ReviewMainActivity extends AppCompatActivity implements ReviewAdapter.OnReviewClickEventListener {

    private final String TAG = ReviewMainActivity.class.getSimpleName();

    private ArrayList<ReviewModel> reviews;
    private ReviewAdapter mReviewAdapter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progressBar);
        ReviewMainViewModel mReviewMainViewModel = new ViewModelProvider(this).get(ReviewMainViewModel.class);
        mReviewMainViewModel.initViewModel();

        mReviewAdapter = new ReviewAdapter(reviews, this);

        RecyclerView mReviewRecyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mReviewRecyclerView.setLayoutManager(layoutManager);
        mReviewRecyclerView.setAdapter(mReviewAdapter);
        Log.d(TAG, "RecyclerView and adapter all set");

        //Observer which updates the list of reviews from json via api call
        final Observer<ArrayList<ReviewModel>> reviewObserver = reviewModels -> {
            if (reviewModels != null) {
                progressBar.setVisibility(View.INVISIBLE);
                mReviewAdapter.setReviewData(reviewModels);
            }
        };

        mReviewMainViewModel.getReviewsLiveData().observe(this, reviewObserver);
        Log.d(TAG, "RecyclerView is populated with data");
    }

    public void startDetailReviewActivity(ReviewModel review) {
        Class destinationClass = DetailReviewActivity.class;
        Intent intent = new Intent(getApplicationContext(), destinationClass);
        intent.putExtra(ServiceNowConstants.COFFEE_REVIEW_EXTRA, review);
        startActivity(intent);
        Log.d(TAG, "launching detailed review activity");
    }

    @Override
    public void onReviewClick(ReviewModel review) {
        startDetailReviewActivity(review);
    }
}
