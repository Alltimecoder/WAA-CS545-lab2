package com.miu.lab2.repository;

import com.miu.lab2.domain.Log;
import org.springframework.data.repository.CrudRepository;

public interface LoggerRepository extends CrudRepository<Log, Long> {

}
