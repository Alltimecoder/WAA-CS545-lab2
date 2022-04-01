package com.miu.lab2.repository;

import com.miu.lab2.domain.Post;
import com.miu.lab2.domain.dto.PostDTO;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {

  List<PostDTO> findAllByAuthor(String author);
}
