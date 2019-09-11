package br.com.marcia.marvel.model.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataContainer<T> {

    @SerializedName("offset")
    private int mOffset;

    @SerializedName("limit")
    private int mLimit;

    @SerializedName("total")
    private int mTotal;

    @SerializedName("count")
    private int mCount;

    @SerializedName("results")
    private List<T> mResults;

    public List<T> getResults() {
        return this.mResults;
    }
}
