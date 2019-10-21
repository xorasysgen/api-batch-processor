package api.batch.processor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import api.batch.processor.model.Nifty;

@Repository
public interface NiftyHistoryRepository  extends JpaRepository<Nifty, Integer>{

	
}
