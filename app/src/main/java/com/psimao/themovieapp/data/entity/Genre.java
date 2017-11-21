package com.psimao.themovieapp.data.entity;

import com.google.gson.annotations.SerializedName;

public class Genre {

    @SerializedName("id")
    Long id;
    @SerializedName("name")
    String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
