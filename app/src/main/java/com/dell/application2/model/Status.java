package com.dell.application2.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Status implements Serializable {

    private User user;
    private String textStatus;
    private List<Image> imageStatus;
    private int numLike;
    private int numComment;
    private boolean activeLike;
    private Date time;

    public Status(User user, String textStatus, List<Image> imageStatus, int numLike, int numComment, boolean activeLike, Date time) {
        this.user = user;
        this.textStatus = textStatus;
        this.imageStatus = imageStatus;
        this.numLike = numLike;
        this.numComment = numComment;
        this.activeLike = activeLike;
        this.time = time;
    }

    public Status() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTextStatus() {
        return textStatus;
    }

    public void setTextStatus(String textStatus) {
        this.textStatus = textStatus;
    }

    public List<Image> getImageStatus() {
        return imageStatus;
    }

    public int getNumLike() {
        return numLike;
    }

    public void setNumLike(int numLike) {
        this.numLike = numLike;
    }

    public int getNumComment() {
        return numComment;
    }

    public void setNumComment(int numComment) {
        this.numComment = numComment;
    }

    public boolean isActiveLike() {
        return activeLike;
    }

    public void setActiveLike(boolean activeLike) {
        this.activeLike = activeLike;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
