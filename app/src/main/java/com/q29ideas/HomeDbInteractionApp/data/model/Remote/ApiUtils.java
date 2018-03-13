package com.q29ideas.HomeDbInteractionApp.data.model.Remote;

//Created on 2/17/2018.


public class ApiUtils {

    public static final String BASE_URL = "http://192.168.1.111/clouddbfiles/";

    public static SOService getSOService() {
        return RetrofitClient.getClient(BASE_URL).create(SOService.class);
    }
}
