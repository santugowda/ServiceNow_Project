package com.servicenow.exercise_java.repository;

import com.servicenow.exercise_java.utils.ServiceNowConstants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {

    private static Retrofit retrofitInstance = new Retrofit.Builder()
            .baseUrl(ServiceNowConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static <S> S getClient(Class<S> serviceClass) {
        return retrofitInstance.create(serviceClass);
    }
}
