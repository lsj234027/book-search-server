package com.example.booksearchserver.rest.kakao;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface KakaoAPI {

  @GET("search/book")
  public Call<KakaoBookSearchResult> searchBook(@Query("query") String query);

  @GET("search/book")
  public Call<KakaoBookSearchResult> searchBook(@Query("query") String query,
                              @Query("sort") String sort,
                              @Query("page") int page,
                              @Query("size") int size);
}
