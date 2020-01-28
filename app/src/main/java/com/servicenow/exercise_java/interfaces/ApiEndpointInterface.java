package com.servicenow.exercise_java.interfaces;

import com.servicenow.exercise_java.utils.ServiceNowConstants;
import com.servicenow.exercise_java.model.ReviewModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiEndpointInterface {

    @GET(ServiceNowConstants.PATH)
    Call<ArrayList<ReviewModel>> fetchReviews();

}
