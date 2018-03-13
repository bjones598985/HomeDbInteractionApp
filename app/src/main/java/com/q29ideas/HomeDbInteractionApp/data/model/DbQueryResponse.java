package com.q29ideas.HomeDbInteractionApp.data.model;

/**
 * Created by Bobby Jones on 2/17/2018.
 */import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DbQueryResponse {

    @SerializedName("records")
    @Expose
    private List<Record> records = null;
    @SerializedName("success")
    @Expose
    private int success;

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

}