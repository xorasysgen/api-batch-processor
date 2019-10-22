package api.batch.processor;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import api.batch.processor.model.BankNifty;
import api.batch.processor.repository.BankNiftyHistoryRepository;

@Component
public class ItemWriterImplnBankNiftyHistory implements  ItemWriter<BankNifty> {

	@Autowired
	BankNiftyHistoryRepository bankniftyHistoryRepository;
	
	@Override
	public void write(List<? extends BankNifty> bNifty) throws Exception {
		System.out.println("Inserting... records" + bNifty);
		bankniftyHistoryRepository.saveAll(bNifty);
	}
	
		
	
}
