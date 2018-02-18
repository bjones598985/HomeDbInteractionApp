package q29ideas.com.retrofitexample.data.model.Remote;

import q29ideas.com.retrofitexample.data.model.DbQueryResponse;
import q29ideas.com.retrofitexample.data.model.Record;
import q29ideas.com.retrofitexample.data.model.SOAnswersResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Bobby Jones on 2/17/2018.
 */

public interface SOService {

    @GET("/answers?order=desc&sort=activity&site=stackoverflow")
    Observable<SOAnswersResponse> getAnswers();

    @GET("/answers?order=desc&sort=activity&site=stackoverflow")
    Observable<SOAnswersResponse> getAnswers(@Query("tagged") String tags);

    @GET("get_all_records.php")
    Observable<DbQueryResponse> getAllRecords();
}
