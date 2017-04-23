package com.example.recyclerview_master.model;

/**
 * Created by hrx on 2017/4/16.
 */

public class AnimalBean {
    private String imageUrl;
    private String imageName;

    public AnimalBean(String imageUrl, String imageName) {
        this.imageUrl = imageUrl;
        this.imageName = imageName;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageUrl() {

        return imageUrl;
    }

    public String getImageName() {
        return imageName;
    }
}
