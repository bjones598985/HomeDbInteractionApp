package q29ideas.com.retrofitexample;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import q29ideas.com.retrofitexample.data.model.DbQueryResponse;
import q29ideas.com.retrofitexample.data.model.Item;
import q29ideas.com.retrofitexample.data.model.Record;
import q29ideas.com.retrofitexample.data.model.Remote.ApiUtils;
import q29ideas.com.retrofitexample.data.model.Remote.SOService;
import q29ideas.com.retrofitexample.data.model.SOAnswersResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    //private AnswersAdapter mAdapter;
    private RecordsAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private SOService mService;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mService = ApiUtils.getSOService();
        mRecyclerView = findViewById(R.id.rv_answers);
        /*
        mAdapter = new AnswersAdapter(this, new ArrayList<Item>(0), new AnswersAdapter.PostItemListener() {

            @Override
            public void onPostClick(long id) {
                Toast.makeText(MainActivity.this, "Post id is " + id, Toast.LENGTH_SHORT).show();
            }
        });
        */
        mAdapter = new RecordsAdapter(new ArrayList<Record>(0));

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        loadAnswers();
    }

    public void loadAnswers() {
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Getting records");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(true);
        pDialog.show();
        /*
        mService.getAnswers().enqueue(new Callback<SOAnswersResponse>() {

            @Override
            public void onResponse(Call<SOAnswersResponse> call, Response<SOAnswersResponse> response) {
                if(response.isSuccessful()) {
                    mAdapter.updateAnswers(response.body().getItems());
                    Log.d("MainActivity", "posts loaded from API");
                }else {
                    int statusCode  = response.code();
                    // handle request errors depending on status code
                }
            }

            @Override
            public void onFailure(Call<SOAnswersResponse> call, Throwable t) {
                //showErrorMessage();
                Log.d("MainActivity", "error loading from API");

            }
        });

        mService.getAllRecords().enqueue(new Callback<DbQueryResponse>() {
            @Override
            public void onResponse(Call<DbQueryResponse> call, Response<DbQueryResponse> response) {
                if(response.isSuccessful()) {
                    mAdapter.updateRecords(response.body().getRecords());
                    Log.d("MainActivity", "posts loaded from API");
                }else {
                    int statusCode  = response.code();
                    // handle request errors depending on status code
                    Log.d("MainActivity", "posts NOT loaded from API, code: " + statusCode);
                }
            }

            @Override
            public void onFailure(Call<DbQueryResponse> call, Throwable t) {
                Log.d("MainActivity", "error loading from API");
                Log.d("MainActivity", "" + t.getMessage());
            }
        });
        */
        //RxJava version
        mService.getAllRecords()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DbQueryResponse>() {
                    @Override
                    public void onCompleted() {
                        Log.d("MainActivity", "onCompleted");
                        if (pDialog != null && pDialog.isShowing()) {
                            pDialog.hide();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("MainActivity", "" + e.getMessage());
                    }

                    @Override
                    public void onNext(DbQueryResponse response) {
                        Log.d("MainActivity", "onNext");
                        mAdapter.updateRecords(response.getRecords());
                    }
                });
    }
}
