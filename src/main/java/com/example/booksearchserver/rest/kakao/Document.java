package com.example.booksearchserver.rest.kakao;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Document {
  @SerializedName("title") private String title;
  @SerializedName("contents") private String contents;
  @SerializedName("url") private String url;
  @SerializedName("isbn") private String isbn;
  @SerializedName("datetime") private String datetime;
  @SerializedName("authors") private List<String> authors;
  @SerializedName("publisher") private String publisher;
  @SerializedName("translators") private List<String> translators;
  @SerializedName("price") private int price;
  @SerializedName("sale_price") private int salePrice;
  @SerializedName("thumbnail") private String thumbnail;
  @SerializedName("status") private String status;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContents() {
    return contents;
  }

  public void setContents(String contents) {
    this.contents = contents;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getIsbn() {
    return isbn;
  }

  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  public String getDatetime() {
    return datetime;
  }

  public void setDatetime(String datetime) {
    this.datetime = datetime;
  }

  public List<String> getAuthors() {
    return authors;
  }

  public void setAuthors(List<String> authors) {
    this.authors = authors;
  }

  public String getPublisher() {
    return publisher;
  }

  public void setPublisher(String publisher) {
    this.publisher = publisher;
  }

  public List<String> getTranslators() {
    return translators;
  }

  public void setTranslators(List<String> translators) {
    this.translators = translators;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public int getSalePrice() {
    return salePrice;
  }

  public void setSalePrice(int salePrice) {
    this.salePrice = salePrice;
  }

  public String getThumbnail() {
    return thumbnail;
  }

  public void setThumbnail(String thumbnail) {
    this.thumbnail = thumbnail;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}