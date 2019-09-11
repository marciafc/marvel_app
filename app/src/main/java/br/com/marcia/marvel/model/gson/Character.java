package br.com.marcia.marvel.model.gson;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;


public class Character implements Serializable {

    @SerializedName("id")
    private int mId;

    @SerializedName("name")
    private String mName;

    @SerializedName("description")
    private String mDescription;

    @SerializedName("modified")
    private Date mModified;

    @SerializedName("resourceURI")
    private String mResourceURI;


    public String getName() {
        return this.mName;
    }

    public String getDescription() {
        return this.mDescription;
    }
}
