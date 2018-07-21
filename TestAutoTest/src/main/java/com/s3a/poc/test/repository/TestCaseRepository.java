package com.s3a.poc.test.repository;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.s3a.poc.test.model.DbSummaryDesc;
import com.s3a.poc.test.model.DbTestCase;
 
public interface TestCaseRepository extends CrudRepository<DbTestCase, Long>{
//    List<DbTestCase> findByLastName(String lastName);


    List<DbTestCase> findByVersionAndSubId(int ver, String SubId);
} 