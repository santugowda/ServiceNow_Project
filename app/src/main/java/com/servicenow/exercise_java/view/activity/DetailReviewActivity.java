package com.servicenow.exercise_java.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.servicenow.exercise.R;
import com.servicenow.exercise_java.model.ReviewModel;
import com.servicenow.exercise_java.utils.ServiceNowConstants;

public class DetailReviewActivity extends AppCompatActivity {

    private TextView reviewText;
    private TextView ratingText;
    private TextView locationText;
    private ReviewModel review;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_detail);
        reviewText = findViewById(R.id.textreview);
        ratingText = findViewById(R.id.textrating);
        locationText = findViewById(R.id.textlocation);

        if (savedInstanceState == null || !savedInstanceState
                .containsKey(ServiceNowConstants.REVIEW_DETAILS_BUNDLE_KEY)) {
            Intent intent = getIntent();
            if (intent.hasExtra(ServiceNowConstants.REVIEW_EXTRA)) {
                review = intent.getParcelableExtra(ServiceNowConstants.REVIEW_EXTRA);
                updateUI();
            }
        } else {
            review = savedInstanceState.getParcelable(ServiceNowConstants.REVIEW_DETAILS_BUNDLE_KEY);
            updateUI();
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(ServiceNowConstants.REVIEW_DETAILS_BUNDLE_KEY, review);
        super.onSaveInstanceState(outState);
    }

    private void updateUI() {
        this.reviewText.setText(review.getReview());
        this.ratingText.setText("" + review.getRating());
        this.locationText.setText(review.getLocation());
    }
}
