package com.servicenow.exercise_java.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.servicenow.exercise.R;
import com.servicenow.exercise_java.model.ReviewModel;
import com.servicenow.exercise_java.utils.ServiceNowConstants;
import com.servicenow.exercise_java.view.adapter.ReviewAdapter;
import com.servicenow.viewmodel.ReviewMainViewModel;

import java.util.ArrayList;

public class ReviewListFragment extends Fragment implements ReviewAdapter.OnReviewClickEventListener {

    public static final String TAG = ReviewListFragment.class.getSimpleName();

    private ArrayList<ReviewModel> reviews;
    private ReviewAdapter mReviewAdapter;
    private ProgressBar progressBar;
    private ReviewMainViewModel mReviewMainViewModel;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach called");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.review_list_fragment, container, false);
        progressBar = view.findViewById(R.id.progressBar);

        mReviewMainViewModel = new ViewModelProvider(this).get(ReviewMainViewModel.class);
        mReviewMainViewModel.initViewModel(getActivity());

        mReviewAdapter = new ReviewAdapter(reviews, this);

        RecyclerView mReviewRecyclerView = view.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mReviewRecyclerView.setLayoutManager(layoutManager);
        mReviewRecyclerView.setAdapter(mReviewAdapter);
        Log.d(TAG, "RecyclerView and adapter all set");

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Observer which updates the list of reviews from json via api call
        final Observer<ArrayList<ReviewModel>> reviewObserver = reviewModels -> {
            if (reviewModels != null) {
                progressBar.setVisibility(View.INVISIBLE);
                mReviewAdapter.setReviewData(reviewModels);
            }
        };

        mReviewMainViewModel.getReviewsLiveData().observe(getViewLifecycleOwner(), reviewObserver);
        Log.d(TAG, "RecyclerView is populated with data");
    }

    private void startDetailReviewActivity(ReviewModel review) {
        Class destinationClass = DetailReviewActivity.class;
        Intent intent = new Intent(getActivity(), destinationClass);
        intent.putExtra(ServiceNowConstants.COFFEE_REVIEW_EXTRA, review);
        startActivity(intent);
        Log.d(TAG, "launching detailed review activity");
    }

    @Override
    public void onReviewClick(ReviewModel review) {
        startDetailReviewActivity(review);
    }


}
