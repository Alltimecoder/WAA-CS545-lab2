package com.miu.lab2.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Comment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String name;
  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @Fetch(value = FetchMode.SELECT)
  @JoinColumn(name = "id_post")
  private Post post;
}
