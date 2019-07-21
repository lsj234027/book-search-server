package com.example.booksearchserver.controller.search;

import com.example.booksearchserver.domain.book.BookSearchHistory;
import com.example.booksearchserver.domain.book.BookSearchHistoryRepository;
import com.example.booksearchserver.rest.kakao.KakaoBookSearchResult;
import com.example.booksearchserver.rest.kakao.KakaoRequestBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import retrofit2.Call;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/book")
public class BookSearchContoller {

  @Autowired
  BookSearchHistoryRepository historyRepository;

  @GetMapping("/search")
  public BookSearchResult searchBook(@RequestParam("query") String query, @RequestParam("sort") String sort,
                           @RequestParam("page") int page, @RequestParam("size") int size) {
    Call<KakaoBookSearchResult> searchResult = new KakaoRequestBuilder().build().searchBook(query, sort, page, size);

    KakaoBookSearchResult result = null;
    try {
      result = searchResult.execute().body();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return new BookSearchResult()
            .setBooks(result.getDocument())
            .setPage(new Page(page, size, (int)Math.ceil((double)(result.getMeta().getTotalCount()) / (double)size), result.getMeta().getTotalCount()));
  }
}
