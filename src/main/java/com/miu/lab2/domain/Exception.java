package com.miu.lab2.domain;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Exception extends Log {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long transactionId;
  private LocalDate date;
  private double timeTaken;
  @ManyToOne
  @JoinColumn(name = "id_user")
  private User principal;
  private String operation;
}
