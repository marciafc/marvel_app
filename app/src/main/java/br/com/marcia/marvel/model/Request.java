package br.com.marcia.marvel.model;

import android.content.Context;

public class Request {

    private int limit;

    private int offset;

    private boolean loading;

    private Authentication auth;

    public Request(Context context) {
        this.auth = new Authentication(context);
    }

    public int getLimit() {
        return this.limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return this.offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    /**
     * Incrementa o offset de acordo com o limite estipulado.
     */
    public void increaseOffset(){
        this.offset += this.limit;
    }

    public boolean isLoading() {
        return this.loading;
    }

    public void setLoading(boolean loading) {
        this.loading = loading;
    }

    public String getHash() {
        return this.auth.getmHash();
    }

    public String getTs() {
        return String.valueOf(this.auth.getmTs());
    }

    public String getApiKey() {
        return this.auth.getmApiKey();
    }

}
