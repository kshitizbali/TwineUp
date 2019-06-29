package com.kshitizbali.twineup.api;

import android.content.Context;
import android.widget.Toast;

import com.kshitizbali.twineup.database.DbNetworkOperations;
import com.kshitizbali.twineup.model.RetroGetSavings;
import com.kshitizbali.twineup.utilities.ConstantUtils;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {

    private static Retrofit retrofit;
    private static final String BASE_URL = "https://www.plushcare.com/appointments/internal/next/";


    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
        return retrofit;
    }



    public static void getSavingsTypes(final Context context, String date) {
        RetrofitDataServices services = getRetrofitInstance().create(RetrofitDataServices.class);
        Call<RetroGetSavings> call = services.getAllSavings(date);
        call.enqueue(new Callback<RetroGetSavings>() {
            @Override
            public void onResponse(Call<RetroGetSavings> call, Response<RetroGetSavings> response) {
                if (response.isSuccessful()) {
                    // Save in room DB
                    if (response.body() != null) {
                        DbNetworkOperations.insertAllAppointments(context, response);
                    }
                } else if (response.code() == 401) {
                    // Handle unauthorized
                    Toast.makeText(context, ConstantUtils.UNAUTHORIZED_ACCESS, Toast.LENGTH_SHORT).show();
                } else {
                    // Handle other responses
                    Toast.makeText(context, ConstantUtils.PLEASE_TRY_LATER, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RetroGetSavings> call, Throwable t) {
                Toast.makeText(context, ConstantUtils.PLEASE_TRY_LATER, Toast.LENGTH_SHORT).show();
            }
        });

    }
}
