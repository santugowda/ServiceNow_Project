package com.servicenow.exercise_java.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.servicenow.exercise.R;
import com.servicenow.exercise_java.model.ReviewModel;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewViewHolder> {

    private List<ReviewModel> mReviewsList;
    private OnReviewClickEventListener mOnReviewClickEventListener;

    //Initialize adapter
    public ReviewAdapter(List<ReviewModel> reviews, OnReviewClickEventListener onReviewClickEventListener) {
        mReviewsList = reviews;
        mOnReviewClickEventListener = onReviewClickEventListener;
    }

    public interface OnReviewClickEventListener {
        void onReviewClick(ReviewModel review);
    }

    public void setOnReviewClickEventListener(OnReviewClickEventListener listener) {
        mOnReviewClickEventListener = listener;
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.review_item, parent, false);
        return new ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder viewHolder, int position) {
        ReviewModel review = mReviewsList.get(position);

        viewHolder.name.setText(review.getName());
        viewHolder.review.setText(review.getReview());
        viewHolder.reviewImage.setImageResource(ReviewModel.getIconResourceFromPlaceName(review.getName()));

        viewHolder.itemView.setOnClickListener(view -> {
            if (mOnReviewClickEventListener != null) {
                mOnReviewClickEventListener.onReviewClick(review);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mReviewsList != null) {
            return mReviewsList.size();
        }
        return 0;
    }

    public void setReviewData(List<ReviewModel> reviews) {
        if (reviews != null) {
            mReviewsList = reviews;
            notifyDataSetChanged();
        }
    }
}
