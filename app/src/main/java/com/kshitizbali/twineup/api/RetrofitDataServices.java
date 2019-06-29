package com.kshitizbali.twineup.api;

import com.kshitizbali.twineup.model.RetroGetSavings;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetrofitDataServices {

    /*@GET("06042019/CA/")*/
    @GET("{date}/CA/")
    Call<RetroGetSavings> getAllSavings(@Path("date") String date);
}
