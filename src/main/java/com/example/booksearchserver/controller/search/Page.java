package com.example.booksearchserver.controller.search;

public class Page {
  private int currentPage;
  private int pageSize;
  private int pageTotalSize;
  private int totalCount;

  public Page(int currentPage, int pageSize, int pageTotalSize, int totalCount) {
    this.currentPage = currentPage;
    this.pageSize = pageSize;
    this.pageTotalSize = pageTotalSize;
    this.totalCount = totalCount;
  }

  public int getCurrentPage() {
    return currentPage;
  }

  public void setCurrentPage(int currentPage) {
    this.currentPage = currentPage;
  }

  public int getPageSize() {
    return pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }

  public int getPageTotalSize() {
    return pageTotalSize;
  }

  public void setPageTotalSize(int pageTotalSize) {
    this.pageTotalSize = pageTotalSize;
  }

  public int getTotalCount() {
    return totalCount;
  }

  public void setTotalCount(int totalCount) {
    this.totalCount = totalCount;
  }
}
