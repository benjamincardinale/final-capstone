package com.techelevator.model;

import javax.validation.constraints.NotBlank;

public class Image {
    @NotBlank(message = "Must send a base64 string containing image information")
    private String base64Info;

    public Image () {}

    public Image (String base64) {
        this.base64Info = base64;
    }

    public String getBase64Info() {
        return base64Info;
    }

    public void setBase64Info(String base64Info) {
        this.base64Info = base64Info;
    }

    @Override
    public String toString() {
        return "Image{" +
                "base64Info='" + base64Info + '\'' +
                '}';
    }
}
