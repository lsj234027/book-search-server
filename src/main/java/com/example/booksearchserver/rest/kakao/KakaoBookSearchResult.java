package com.example.booksearchserver.rest.kakao;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Kakao 책 검색 API 결과를 담을 Result
 */
public class KakaoBookSearchResult implements Serializable {

  @SerializedName("meta") private Meta meta;
  @SerializedName("documents") private List<Document> document;

  public Meta getMeta() {
    return meta;
  }

  public void setMeta(Meta meta) {
    this.meta = meta;
  }

  public List<Document> getDocument() {
    return document;
  }

  public void setDocument(List<Document> document) {
    this.document = document;
  }
}
