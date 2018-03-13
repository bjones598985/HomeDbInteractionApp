package com.q29ideas.HomeDbInteractionApp.data.model;

/**
 * Created by Bobby Jones on 2/18/2018.
 */

import com.google.gson.annotations.SerializedName;
import com.google.gson.annotations.Expose;

public class DbInsertResponse {

    @SerializedName("result")
    @Expose
    private String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

}