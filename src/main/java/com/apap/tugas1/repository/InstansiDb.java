package com.apap.tugas1.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.apap.tugas1.model.InstansiModel;

@Repository
public interface InstansiDb extends JpaRepository<InstansiModel, Long>{
	
	

}
