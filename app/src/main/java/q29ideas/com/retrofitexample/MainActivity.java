package q29ideas.com.retrofitexample;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import q29ideas.com.retrofitexample.data.model.DbInsertResponse;
import q29ideas.com.retrofitexample.data.model.DbQueryResponse;
import q29ideas.com.retrofitexample.data.model.Record;
import q29ideas.com.retrofitexample.data.model.Remote.ApiUtils;
import q29ideas.com.retrofitexample.data.model.Remote.SOService;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private static final String SUCCESS = "Successful";
    private static final String FAIL = "Failed";

    private RecordsAdapter mAdapter;
    private SOService mService;
    private ProgressDialog pDialog;
    private ViewGroup form;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mService = ApiUtils.getSOService();
        form = findViewById(R.id.include_layout);
        final RecyclerView mRecyclerView = findViewById(R.id.rv_answers);
        mAdapter = new RecordsAdapter(new ArrayList<Record>(0));

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        setupButtons();
    }

    private void setupButtons() {
        //Get All Records
        findViewById(R.id.get_all_records).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadAnswers();
            }
        });

        //Insert Records
        findViewById(R.id.insert_record).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFormVisibility();
            }
        });

        //Form cancel button
        findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFormVisibility();
            }
        });

        //Form submit button
        findViewById(R.id.submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertRecords();
                changeFormVisibility();
            }
        });
    }

    public void loadAnswers() {
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Getting records");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(true);
        pDialog.show();
        /*
        non-RxJava version
        mService.getAllRecords().enqueue(new Callback<DbQueryResponse>() {
            @Override
            public void onResponse(Call<DbQueryResponse> call, Response<DbQueryResponse> response) {
                if(response.isSuccessful()) {
                    mAdapter.updateRecords(response.body().getRecords());
                    Log.d(TAG, "posts loaded from API");
                }else {
                    int statusCode  = response.code();
                    // handle request errors depending on status code
                    Log.d(TAG, "posts NOT loaded from API, code: " + statusCode);
                }
            }

            @Override
            public void onFailure(Call<DbQueryResponse> call, Throwable t) {
                Log.d(TAG, "error loading from API");
                Log.d(TAG, "" + t.getMessage());
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
                        Log.d(TAG, "onCompleted");
                        if (pDialog != null && pDialog.isShowing()) {
                            pDialog.hide();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "" + e.getMessage());
                    }

                    @Override
                    public void onNext(DbQueryResponse response) {
                        Log.d(TAG, "onNext");
                        mAdapter.updateRecords(response.getRecords());
                    }
                });
    }

    public void insertRecords() {
        String date = ((EditText) findViewById(R.id.et_date)).getText().toString();
        int seq = Integer.valueOf(((EditText) findViewById(R.id.et_seq)).getText().toString());
        long time = Long.valueOf(((EditText) findViewById(R.id.et_time)).getText().toString());
        int distance = Integer.valueOf(((EditText) findViewById(R.id.et_dist)).getText().toString());
        int cals = Integer.valueOf(((EditText) findViewById(R.id.et_cals)).getText().toString());
        String ctype = ((EditText) findViewById(R.id.et_ctype)).getText().toString();
        String notes = ((EditText) findViewById(R.id.et_notes)).getText().toString();
        mService.savePost(date, seq, time, distance, cals, ctype, notes)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DbInsertResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(MainActivity.this, "There was a problem, the records weren't added", Toast.LENGTH_SHORT).show();
                        Log.e(TAG, "Error: " + e.getMessage());
                    }

                    @Override
                    public void onNext(DbInsertResponse dbInsertResponse) {
                        String result = dbInsertResponse.getResult();
                        if (result.equals(SUCCESS)) {
                            Toast.makeText(MainActivity.this, "New records successfully added!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void changeFormVisibility() {
        if (form.getVisibility() == View.VISIBLE) {
            form.setVisibility(View.INVISIBLE);
        } else {
            form.setVisibility(View.VISIBLE);
        }
    }
}