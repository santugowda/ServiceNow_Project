package com.servicenow.exercise_java.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.servicenow.exercise.R;

/**
 * Model i.e. POJO of the Review API
 */
public class ReviewModel implements Parcelable {

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

    public static final Creator<ReviewModel> CREATOR = new Creator<ReviewModel>() {
        @Override
        public ReviewModel createFromParcel(Parcel in) {
            return new ReviewModel(in);
        }

        @Override
        public ReviewModel[] newArray(int size) {
            return new ReviewModel[size];
        }
    };

    private ReviewModel(Parcel in) {
        name = in.readString();
        review = in.readString();
        location = in.readString();
        rating = in.readInt();
    }

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

    public static int getIconResourceFromPlaceName(String name) {
        switch (name) {
            case "Lofty":
                return R.drawable.bean_bag;
            case "Zumbar":
                return R.drawable.coffee_cup;
            case "Blue Bottle":
                return R.drawable.coffee_grinder;
            case "Bird Rock":
                return R.drawable.coffee_maker;
            case "Better Buzz Coffee":
                return R.drawable.coffee_shop;
        }
        return -1;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(review);
        parcel.writeString(location);
        parcel.writeInt(rating);
    }
}
