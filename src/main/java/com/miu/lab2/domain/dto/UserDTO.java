package com.miu.lab2.domain.dto;

import com.miu.lab2.domain.Post;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDTO {
  private String name;
  private List<Post> posts;
}