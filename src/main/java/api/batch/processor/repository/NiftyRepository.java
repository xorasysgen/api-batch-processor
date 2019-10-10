package api.batch.processor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import api.batch.processor.model.IndN500;

@Repository
public interface NiftyRepository  extends JpaRepository<IndN500, Integer>{

	
}
