package com.miu.lab2.domain;


import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Post {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String title;
  private String content;
  private String author;
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "id_user")
  private User user;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
  private List<Comment> comment;
}
