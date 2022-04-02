package com.miu.lab2.repository;

import com.miu.lab2.domain.Post;
import com.miu.lab2.domain.User;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {

  List<Post> findAllByAuthor(String author);

  List<Post> findAllByTitle(String title);

  @Query("select p.user from Post p where p.title = :title")
  List<User> findUserWithPostOfSpecificTitle(String title);
}
