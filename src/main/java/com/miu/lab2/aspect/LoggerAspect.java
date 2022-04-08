package com.miu.lab2.aspect;

import com.miu.lab2.domain.Exception;
import com.miu.lab2.domain.Log;
import com.miu.lab2.domain.User;
import com.miu.lab2.service.ExceptionService;
import com.miu.lab2.service.LoggerService;
import java.time.LocalDate;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAspect {
  
  private static User Mocked_User = new User(112, "john@miu.edu", "john", "doe", "$2a$12$IKEQb00u5QpZMx4v5zMweu.3wrq0pS7XLCHO4yHZ.BW/yvWu1feo2", null, null);
  private final LoggerService loggerService;
  private final ExceptionService exceptionService;

  @Autowired
  public LoggerAspect(LoggerService loggerService, ExceptionService exceptionService) {
    this.loggerService = loggerService;
    this.exceptionService = exceptionService;
  }

  @Pointcut("@annotation(com.miu.lab2.aspect.annotation.ExecutionTime)")
  public void logMeAnnotation() {}

  @Pointcut("execution(* com.miu.lab2.controller.UserController.*(..))")
  public void executionTime() {}

  @Pointcut("execution(* com.miu.lab2.controller.UserController.exceptionTest(..))")
  public void exceptionTime() {}
  
  @Before("logMeAnnotation()")
  public void saveLogToDB(JoinPoint joinPoint) {
    Log log = new Log();
    log.setDate(LocalDate.now());
    log.setOperation(joinPoint.getSignature().getName());
    log.setPrincipal(Mocked_User);
    log.setTimeTaken(System.currentTimeMillis());
    loggerService.save(log);
    System.out.println("Log with id " + log.getTransactionId() + " Recorded Successfully");
  }

  @Before("exceptionTime()")
  public void saveExceptionToDB(JoinPoint joinPoint) {
    Exception log = new Exception();
    log.setDate(LocalDate.now());
    log.setOperation(joinPoint.getSignature().getName());
    log.setPrincipal(Mocked_User);
    log.setTimeTaken(System.currentTimeMillis());
    exceptionService.save(log);
    System.out.println("Exception with id " + log.getTransactionId() + " Recorded Successfully");
  }
}
