package api.batch.processor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import api.batch.processor.model.NseListedALL;

@Repository
public interface NiftyALLRepository  extends JpaRepository<NseListedALL, Integer>{

	
}
