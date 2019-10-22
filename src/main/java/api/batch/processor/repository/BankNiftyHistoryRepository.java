package api.batch.processor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import api.batch.processor.model.BankNifty;

@Repository
public interface BankNiftyHistoryRepository  extends JpaRepository<BankNifty, Integer>{

	
}
