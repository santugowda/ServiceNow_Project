package com.servicenow.exercise_java.view.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.servicenow.exercise.R;

public class ReviewViewHolder extends RecyclerView.ViewHolder {

    TextView name;
    TextView review;
    ImageView reviewImage;

    ReviewViewHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.name);
        review = itemView.findViewById(R.id.review);
        reviewImage = itemView.findViewById(R.id.image);
    }
}
