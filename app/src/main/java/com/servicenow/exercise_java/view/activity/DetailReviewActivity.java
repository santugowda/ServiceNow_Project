package com.servicenow.exercise_java.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.servicenow.exercise.R;
import com.servicenow.exercise_java.model.ReviewModel;
import com.servicenow.exercise_java.utils.ServiceNowConstants;

/**
 * Activity to display details of the coffee shop and its review
 */

public class DetailReviewActivity extends AppCompatActivity {

    private TextView shopReviewText;
    private TextView shopRatingText;
    private TextView shopLocationText;
    private TextView shopNameText;
    private ImageView shopImageView;

    private ReviewModel review;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.review_detail_activity);
        shopNameText = findViewById(R.id.coffeeShopName);
        shopImageView = findViewById(R.id.coffeeShopImageView);
        shopReviewText = findViewById(R.id.textreview);
        shopRatingText = findViewById(R.id.textrating);
        shopLocationText = findViewById(R.id.textlocation);

        if (savedInstanceState == null || !savedInstanceState
                .containsKey(ServiceNowConstants.REVIEW_DETAILS_BUNDLE_KEY)) {
            Intent intent = getIntent();
            if (intent.hasExtra(ServiceNowConstants.COFFEE_REVIEW_EXTRA)) {
                review = intent.getParcelableExtra(ServiceNowConstants.COFFEE_REVIEW_EXTRA);
                updateUI();
            }
        } else {
            review = savedInstanceState.getParcelable(ServiceNowConstants.REVIEW_DETAILS_BUNDLE_KEY);
            updateUI();
        }
    }

    /*
     * used to retrieve per-instance state from an activity before being killed
     * so that the state can be restored
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(ServiceNowConstants.REVIEW_DETAILS_BUNDLE_KEY, review);
        super.onSaveInstanceState(outState);
    }

    private void updateUI() {
        this.shopNameText.setText(review.getName());
        this.shopImageView.setImageResource(ReviewModel.getIconResourceFromPlaceName(review.getName()));
        this.shopReviewText.setText(review.getReview());
        this.shopRatingText.setText("" + review.getRating());
        this.shopLocationText.setText(review.getLocation());
    }
}
