package com.android.retrofittest;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface RetrofitData {
    @GET("api/v2/data/category/Girl/type/Girl/page/{page}/count/{number}")
    Observable<PhotoBean> getData(@Path("page") int page,@Path("number") int number);
}
