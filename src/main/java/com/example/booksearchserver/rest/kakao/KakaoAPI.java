package com.example.booksearchserver.rest.kakao;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import springfox.documentation.annotations.Cacheable;

/**
 * Retrofit2 를 위한 KakaoAPI Interface
 */
public interface KakaoAPI {

  @GET("search/book")
  public Call<KakaoBookSearchResult> searchBook(@Query("query") String query);

  @Cacheable(value="searchBookCache")
  @GET("search/book")
  public Call<KakaoBookSearchResult> searchBook(@Query("query") String query,
                                                @Query("sort") String sort,
                                                @Query("page") int page,
                                                @Query("size") int size);
}
