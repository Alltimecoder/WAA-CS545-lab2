package com.miu.lab2.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDTO {

  private long id;
  private String email;
  private String firstName;
  private String lastName;
  private String password;
}