package com.furkanturkmen.recipe;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface RecipeService {

    String BASE_URL = "https://www.food2fork.com/";


    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    @GET("/api/search?key=ad2c5ae4b71fec6af813b9c97eca3331&sort=r&count=6")
    Call<RecipeResult> getRecipe();
}
