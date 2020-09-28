package com.android.retrofittest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Retrofit;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private  List<PhotoBean.DataBean> list ;
    private RecyclerView recyclerView;
    private MyAdapter myAdapter;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        button = (Button) findViewById(R.id.button);
        recyclerView = (RecyclerView) findViewById(R.id.recycleview);
        context = getApplicationContext();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.button:
                        RequestPhotoData();
                        break;
                }
            }
        });
    }


    public void refashUI(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        myAdapter = new MyAdapter(context,list);
        recyclerView.setAdapter(myAdapter);
    }

    private void RequestPhotoData() {
        final Retrofit retrofit = RetrofitHelper.getRetrofitInstance();
        RetrofitData retrofitData = retrofit.create(RetrofitData.class);
        Observable<PhotoBean> observable = retrofitData.getData(1,45);
        observable.subscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PhotoBean>() {
                    @Override
                    public void onCompleted() {
                        Log.i("wj","--------onCompleted-----------");
                    }

                    @Override
                    public void onError(Throwable e) {
                       e.printStackTrace();
                    }

                    @Override
                    public void onNext(PhotoBean photoBean) {
                        Log.i("wj","--------onNext-----------"+photoBean);
                        list = photoBean.getData();
                        refashUI();
                  //      myAdapter.notifyDataSetChanged();
                  //      updateUI();
//                        for (PhotoBean.DataBean bean:list){
//                            Log.i("wj","--------id------"+bean.get_id());
//                            Log.i("wj","---------author------"+bean.getAuthor());
//                            Log.i("wj","--------category-------"+bean.getCategory());
//                            Log.i("wj","---------createdAt------"+bean.getCreatedAt());
//                            Log.i("wj","--------desc------"+bean.getDesc());
//                            Log.i("wj","---------publishedAt------"+bean.getPublishedAt());
//                            Log.i("wj","--------likeCounts-------"+bean.getLikeCounts());
//                            Log.i("wj","---------stars------"+bean.getStars());
//                            Log.i("wj","--------title------"+bean.getTitle());
//                            Log.i("wj","---------type------"+bean.getType());
//                            Log.i("wj","--------url-------"+bean.getUrl());
//                            Log.i("wj","---------views------"+bean.getViews());
//                            Log.i("wj","--------images------"+bean.getImages());
//
//                        }
//                        Log.i("wj","--------1-------"+photoBean.getPage());
//                        Log.i("wj","--------2-------"+photoBean.getPage_count());
//                        Log.i("wj","--------3-------"+photoBean.getStatus());
//                        Log.i("wj","--------4-------"+photoBean.getTotal_counts());
                    }
                });
    }


    @Override
    protected void onResume() {
        super.onResume();

    }
}
