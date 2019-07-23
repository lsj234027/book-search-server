package com.example.booksearchserver.controller.search;

import com.example.booksearchserver.infra.config.Constants;
import com.example.booksearchserver.controller.user.SearchHistoryView;
import com.example.booksearchserver.rest.kakao.KakaoBookSearchResult;
import com.example.booksearchserver.rest.kakao.KakaoRequestBuilder;
import com.example.booksearchserver.service.auth.AuthenticationService;
import com.example.booksearchserver.service.book.SearchHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import retrofit2.Call;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * 책 검색 Controller
 */
@RestController
@RequestMapping("/api/v1/book")
public class BookSearchContoller {

  @Autowired
  AuthenticationService authService;

  @Autowired
  SearchHistoryService historyService;

  /**
   * Kakao API 를 통해 책을 검색한다.
   *
   * @param request
   * @param query
   * @param sort
   * @param page
   * @param size
   * @return
   */
  @GetMapping("/search")
  public BookSearchResult searchBook(HttpServletRequest request, @RequestParam("query") String query, @RequestParam("sort") String sort,
                           @RequestParam("page") int page, @RequestParam("size") int size) throws Exception {
    Call<KakaoBookSearchResult> searchResult = new KakaoRequestBuilder().build().searchBook(query, sort, page, size);
    KakaoBookSearchResult result = null;
    result = searchResult.execute().body();

    // 검색이력 저장
    final String token = request.getHeader(Constants.TOKEN_HEADER);
    final String userid = authService.getUserIdByToken(token);
    historyService.addHistory(userid, query);

    return new BookSearchResult()
            .setBooks(result.getDocument())
            .setPage(new Page(page, size, (int)Math.ceil((double)(result.getMeta().getTotalCount()) / (double)size), result.getMeta().getTotalCount()));
  }

  /**
   * Top 10 검색이력을 반환한다.
   *
   * @return
   */
  @GetMapping("/search/topHistory")
  public List<SearchHistoryView> getTop10History() throws Exception {
    return historyService.getTop10Histories();
  }
}
