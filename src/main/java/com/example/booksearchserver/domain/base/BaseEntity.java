package com.example.booksearchserver.domain.base;

import com.google.gson.annotations.SerializedName;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.util.*;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
abstract public class BaseEntity {

  @Id
  @GeneratedValue
  @SerializedName("id")
  private Long id;

  @CreationTimestamp
  private Date createDate;

  @UpdateTimestamp
  private Date updateDate;
}