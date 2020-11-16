package ru.catcherry.lab1;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("facts")
    Observable<FactsList> facts();

    @GET("facts/{id}")
    Observable<FactDetail> fact(@Path("id") String id);
}
