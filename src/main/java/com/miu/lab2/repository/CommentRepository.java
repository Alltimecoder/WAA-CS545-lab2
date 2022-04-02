package com.miu.lab2.repository;

import com.miu.lab2.domain.Comment;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {

  @Query("select c from Comment c where c.id = :commentId and c.post.id = :postId and  c.post.user.id = :userId")
  Optional<Comment> getCommentBySpecificUserAndPostId(long userId, long postId, long commentId);
}
