package com.miu.lab2.repository;

import com.miu.lab2.domain.User;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

  @Query("select u from User u where u.posts.size > :postMoreThan")
  List<User> userWithMoreThanOnePost(int postMoreThan);
}
