package com.miu.lab2.service;

import com.miu.lab2.domain.User;
import com.miu.lab2.domain.dto.UserDTO;
import java.util.List;

public interface UserService extends CRUDService<UserDTO> {
  List<UserDTO> userWithMoreThanOnePost(int postMoreThan);
}
