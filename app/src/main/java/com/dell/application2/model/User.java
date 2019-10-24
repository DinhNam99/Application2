package com.dell.application2.model;

import java.io.Serializable;

public class User implements Serializable {
    private String profile_image;
    private String name;

    public User(String profile_image, String name) {
        this.profile_image = profile_image;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }
}
