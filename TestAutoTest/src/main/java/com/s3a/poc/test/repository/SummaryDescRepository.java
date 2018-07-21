package com.s3a.poc.test.repository;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
 
import com.s3a.poc.test.model.DbSummaryDesc;
 
public interface SummaryDescRepository extends CrudRepository<DbSummaryDesc, Long>{
    List<DbSummaryDesc> findByImpact(String impact);
	
    
    @Query(value="SELECT coalesce(max(version), 0) FROM summary_desc", nativeQuery = true) 
    public Integer getMaxId();
     



} 


