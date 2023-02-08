package com.emailzip.Repo;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import com.emailzip.Entity.ZipEntity;

public interface ZipRepo extends JpaRepository<ZipEntity, Integer> {
	
	
	@Query(nativeQuery = true,value="select * from zip_entity  where created_date>=DATE_SUB(now(),Interval 24 Hour)")
	public List<ZipEntity> findLatestCreatedFile();

}
