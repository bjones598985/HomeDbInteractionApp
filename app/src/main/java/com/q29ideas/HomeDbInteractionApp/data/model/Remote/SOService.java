package com.q29ideas.HomeDbInteractionApp.data.model.Remote;

import com.q29ideas.HomeDbInteractionApp.data.model.DbInsertResponse;
import com.q29ideas.HomeDbInteractionApp.data.model.DbQueryResponse;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

//Created by Bobby Jones on 2/17/2018.

public interface SOService {

    @GET("get_all_records.php")
    Observable<DbQueryResponse> getAllRecords();

    @POST("insert_record.php")
    @FormUrlEncoded
    Observable<DbInsertResponse> savePost(@Field("date") String date,
                                          @Field("sequence") int seq,
                                          @Field("time") long time,
                                          @Field("distance") int distance,
                                          @Field("calories") int calories,
                                          @Field("cardio_type") String cardioType,
                                          @Field("notes") String notes);
}