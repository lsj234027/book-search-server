package com.example.booksearchserver.rest.kakao;

import com.google.gson.annotations.SerializedName;

public class Meta {
  @SerializedName("total_count") private int totalCount;
  @SerializedName("pageable_count") private int pageableCount;
  @SerializedName("is_end") private boolean isEnd;

  public void setTotalCount(int totalCount) {
    this.totalCount = totalCount;
  }

  public int getPageableCount() {
    return pageableCount;
  }

  public void setPageableCount(int pageableCount) {
    this.pageableCount = pageableCount;
  }

  public boolean isEnd() {
    return isEnd;
  }

  public void setEnd(boolean end) {
    isEnd = end;
  }

  public int getTotalCount() {
    return totalCount;
  }
}