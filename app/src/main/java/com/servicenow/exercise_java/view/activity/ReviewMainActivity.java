package com.servicenow.exercise_java.view.activity;

import android.content.Intent;
import android.os.Bundle;
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
import com.servicenow.exercise_java.view.ReviewMainViewModel;
import com.servicenow.exercise_java.view.adapter.ReviewAdapter;

import java.util.ArrayList;

public class ReviewMainActivity extends AppCompatActivity implements ReviewAdapter.OnReviewClickEventListener {

    private final String TAG = ReviewMainActivity.class.getSimpleName();

    private ArrayList<ReviewModel> reviews;
    private ReviewAdapter mReviewAdapter;
    private RecyclerView mReviewRecyclerView;
    private ReviewMainViewModel mReviewMainViewModel;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progressBar);
        mReviewMainViewModel = new ViewModelProvider(this).get(ReviewMainViewModel.class);
        mReviewMainViewModel.initViewModel();

        mReviewAdapter = new ReviewAdapter(reviews, this);

        mReviewRecyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mReviewRecyclerView.setLayoutManager(layoutManager);
        mReviewRecyclerView.setAdapter(mReviewAdapter);

        final Observer<ArrayList<ReviewModel>> reviewObserver = reviewModels -> {
            if (reviewModels != null) {
                progressBar.setVisibility(View.INVISIBLE);
                mReviewAdapter.setReviewData(reviewModels);
            }
        };

        mReviewMainViewModel.getReviewsLiveData().observe(this, reviewObserver);
    }

    public void startDetailReviewActivity(ReviewModel review) {
        Class destinationClass = DetailReviewActivity.class;
        Intent intent = new Intent(getApplicationContext(), destinationClass);
        intent.putExtra(ServiceNowConstants.REVIEW_EXTRA, review);
        startActivity(intent);
    }

    @Override
    public void onReviewClick(ReviewModel review) {
        startDetailReviewActivity(review);
    }
}
