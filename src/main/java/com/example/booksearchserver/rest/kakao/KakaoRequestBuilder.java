package com.example.booksearchserver.rest.kakao;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

/**
 * Kakao Retrofit builder
 */
public class KakaoRequestBuilder {
  private static final String BASE_URL = "https://dapi.kakao.com/v3/";
  private static final String HEADER_API_KEY = "Authorization";
  private static final String API_KEY = "KakaoAK 161c4ac5820b61b68d81f7ffe769f0d3";

  public KakaoAPI build() {
    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
    OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(chain -> {
              Request request = chain.request().newBuilder().addHeader(HEADER_API_KEY, API_KEY).build();
              return chain.proceed(request);})
            .addInterceptor(loggingInterceptor)
            .build();

    Retrofit retrofit =
            new Retrofit.Builder().baseUrl(BASE_URL).client(client).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();

    return retrofit.create(KakaoAPI.class);
  }

}
