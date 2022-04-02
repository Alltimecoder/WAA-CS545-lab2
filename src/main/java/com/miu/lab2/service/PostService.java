package com.miu.lab2.service;

import com.miu.lab2.domain.Post;
import com.miu.lab2.domain.User;
import com.miu.lab2.domain.dto.PostDTO;
import com.miu.lab2.domain.dto.UserDTO;
import java.util.List;

public interface PostService extends CRUDService<PostDTO> {

  void update(PostDTO p, long id);

  List<PostDTO> filterByAuthor(String author);

  List<PostDTO> filterByTitle(String title);

  List<UserDTO> findUserWithPostOfSpecificTitle(String title);

}
