package com.oms.webda2.model;

public class ProductReview {
    private int reviewId;
    private int productId;
    private String reviewInfo;
    private int rating;

    public ProductReview() { }

    public ProductReview(int productId, String reviewInfo, int rating) {
        this.productId = productId;
        this.reviewInfo = reviewInfo;
        this.rating = rating;
    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getReviewInfo() {
        return reviewInfo;
    }

    public void setReviewInfo(String reviewInfo) {
        this.reviewInfo = reviewInfo;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
