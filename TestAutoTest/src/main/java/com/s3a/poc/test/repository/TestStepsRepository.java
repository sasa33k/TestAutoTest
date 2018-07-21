package com.s3a.poc.test.repository;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
 
import com.s3a.poc.test.model.DbTestSteps;
 
public interface TestStepsRepository extends CrudRepository<DbTestSteps, Long>{
//    Object findByVersionAndStepNo = null;

//	List<DbTestSteps> findByVersionAndStepNo(Integer version, String stepsNo);
//
//	Iterable<DbTestSteps> findByVersionAndStepNoIn(int i, List<Integer> stepsNoList);
	Iterable<DbTestSteps> findByVersionAndStepNoIn(int i, List<Integer> stepsNoList);

	Iterable<DbTestSteps> findByVersionAndModIdAndStepNoIn(int i, int j, List<Integer> stepsNoList);
	
	
} 