package br.com.marcia.marvel.model.gson;

import com.google.gson.annotations.SerializedName;

public class DataWrapper<T> {

    @SerializedName("code")
    private int mCode;

    @SerializedName("status")
    private String mStatus;

    @SerializedName("copyright")
    private String mCopyright;

    @SerializedName("attributionText")
    private String mAttributionText;

    @SerializedName("attributionHTML")
    private String mAttributionHTML;

    @SerializedName("etag")
    private String mEtag;

    @SerializedName("data")
    private DataContainer<T> mData;

    public int getCode() {
        return this.mCode;
    }

    public void setCode(int code) {
        this.mCode = code;
    }

    public DataContainer<T> getData() {
        return this.mData;
    }

}