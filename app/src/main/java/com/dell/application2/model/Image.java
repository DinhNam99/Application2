package com.dell.application2.model;

import java.io.Serializable;

public class Image implements Serializable {
    private String imageLink;
    private int numLikeImage;
    private int numCommentImage;
    private boolean activeLike;

    public Image(String imageLink, int numLikeImage, int numCommentImage) {
        this.imageLink = imageLink;
        this.numLikeImage = numLikeImage;
        this.numCommentImage = numCommentImage;
    }

    public Image(String imageLink, int numLikeImage, int numCommentImage, boolean activeLike) {
        this.imageLink = imageLink;
        this.numLikeImage = numLikeImage;
        this.numCommentImage = numCommentImage;
        this.activeLike = activeLike;
    }

    public boolean isActiveLike() {
        return activeLike;
    }

    public void setActiveLike(boolean activeLike) {
        this.activeLike = activeLike;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public int getNumLikeImage() {
        return numLikeImage;
    }

    public void setNumLikeImage(int numLikeImage) {
        this.numLikeImage = numLikeImage;
    }

    public int getNumCommentImage() {
        return numCommentImage;
    }

    public void setNumCommentImage(int numCommentImage) {
        this.numCommentImage = numCommentImage;
    }
}
