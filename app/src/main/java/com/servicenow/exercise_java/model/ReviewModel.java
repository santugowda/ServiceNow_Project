package com.servicenow.exercise_java.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Model i.e. POJO of the Review API
 */
public class ReviewModel {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("review")
    @Expose
    private String review;
    @SerializedName("rating")
    @Expose
    private Integer rating;
    @SerializedName("location")
    @Expose
    private String location;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ReviewModel withName(String name) {
        this.name = name;
        return this;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public ReviewModel withReview(String review) {
        this.review = review;
        return this;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public ReviewModel withRating(Integer rating) {
        this.rating = rating;
        return this;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public ReviewModel withLocation(String location) {
        this.location = location;
        return this;
    }
}
