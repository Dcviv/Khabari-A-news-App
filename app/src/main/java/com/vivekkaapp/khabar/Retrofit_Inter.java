package com.vivekkaapp.khabar;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface Retrofit_Inter {
    @GET
    Call<NewsModalClass> getAllNews(@Url String url);
    @GET
    Call<NewsModalClass>getNewsByCategory(@Url String url);
}
