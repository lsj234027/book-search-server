package com.example.booksearchserver.controller.search;

import com.example.booksearchserver.config.Constants;
import com.example.booksearchserver.controller.user.SearchHistoryView;
import com.example.booksearchserver.domain.book.BookSearchHistory;
import com.example.booksearchserver.domain.book.BookSearchHistoryRepository;
import com.example.booksearchserver.rest.kakao.KakaoBookSearchResult;
import com.example.booksearchserver.rest.kakao.KakaoRequestBuilder;
import com.example.booksearchserver.service.auth.AuthenticationService;
import com.example.booksearchserver.service.book.SearchHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import retrofit2.Call;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/book")
public class BookSearchContoller {

  @Autowired
  AuthenticationService authService;

  @Autowired
  SearchHistoryService historyService;

  @GetMapping("/search")
  public BookSearchResult searchBook(HttpServletRequest request, @RequestParam("query") String query, @RequestParam("sort") String sort,
                           @RequestParam("page") int page, @RequestParam("size") int size) {
    Call<KakaoBookSearchResult> searchResult = new KakaoRequestBuilder().build().searchBook(query, sort, page, size);

    KakaoBookSearchResult result = null;
    try {
      result = searchResult.execute().body();
    } catch (IOException e) {
      e.printStackTrace();
    }

    final String token = request.getHeader(Constants.TOKEN_HEADER);
    final String userid = authService.getUserIdByToken(token);
    historyService.addHistory(userid, query);

    return new BookSearchResult()
            .setBooks(result.getDocument())
            .setPage(new Page(page, size, (int)Math.ceil((double)(result.getMeta().getTotalCount()) / (double)size), result.getMeta().getTotalCount()));
  }


  @GetMapping("/search/topHistory")
  public List<SearchHistoryView> getTop10History() {
    return historyService.getTop10Histories();
  }
}
