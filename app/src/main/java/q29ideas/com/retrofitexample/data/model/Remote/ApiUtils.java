package q29ideas.com.retrofitexample.data.model.Remote;

import q29ideas.com.retrofitexample.MainActivity;
import retrofit2.Retrofit;

//Created on 2/17/2018.


public class ApiUtils {

    public static final String BASE_URL = "http://192.168.1.111/clouddbfiles/";

    public static SOService getSOService() {
        return RetrofitClient.getClient(BASE_URL).create(SOService.class);
    }
}
