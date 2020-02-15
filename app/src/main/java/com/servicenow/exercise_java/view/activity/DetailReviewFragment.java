package com.servicenow.exercise_java.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.servicenow.exercise.R;
import com.servicenow.exercise_java.model.ReviewModel;
import com.servicenow.exercise_java.utils.ServiceNowConstants;

/**
 * Activity to display details of the coffee shop and its review
 */

public class DetailReviewFragment extends Fragment {

    public static final String TAG = DetailReviewFragment.class.getSimpleName();
    private TextView shopReviewText;
    private TextView shopRatingText;
    private TextView shopLocationText;
    private TextView shopNameText;
    private ImageView shopImageView;

    private ReviewModel review;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.review_detail_activity, container, false);
        shopNameText = view.findViewById(R.id.coffeeShopName);
        shopImageView = view.findViewById(R.id.coffeeShopImageView);
        shopReviewText = view.findViewById(R.id.textreview);
        shopRatingText = view.findViewById(R.id.textrating);
        shopLocationText = view.findViewById(R.id.textlocation);


        if (savedInstanceState == null || !savedInstanceState
                .containsKey(ServiceNowConstants.REVIEW_DETAILS_BUNDLE_KEY)) {
            Bundle args = getArguments();
            if (args != null) {
                review = args.getParcelable(ServiceNowConstants.COFFEE_REVIEW_EXTRA);
            }
            updateUI();
        } else {
            review = savedInstanceState.getParcelable(ServiceNowConstants.REVIEW_DETAILS_BUNDLE_KEY);
            updateUI();
        }

        return view;
    }

//    @Override
//    public void o(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.review_detail_activity);
//        shopNameText = findViewById(R.id.coffeeShopName);
//        shopImageView = findViewById(R.id.coffeeShopImageView);
//        shopReviewText = findViewById(R.id.textreview);
//        shopRatingText = findViewById(R.id.textrating);
//        shopLocationText = findViewById(R.id.textlocation);
//
//
//    }

    /*
     * used to retrieve per-instance state from an activity before being killed
     * so that the state can be restored
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(ServiceNowConstants.REVIEW_DETAILS_BUNDLE_KEY, review);
        super.onSaveInstanceState(outState);
    }

    private void updateUI() {
        this.shopNameText.setText(review.getName());
        this.shopImageView.setImageResource(ReviewModel.getIconResourceFromPlaceName(review.getName()));
        this.shopReviewText.setText(review.getReview());
        this.shopRatingText.setText("" + review.getRating());
        this.shopLocationText.setText(review.getLocation());

        shopLocationText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "location clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
