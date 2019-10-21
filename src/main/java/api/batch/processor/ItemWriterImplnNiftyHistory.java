package api.batch.processor;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import api.batch.processor.model.Nifty;
import api.batch.processor.repository.NiftyHistoryRepository;

@Component
public class ItemWriterImplnNiftyHistory implements  ItemWriter<Nifty> {

	@Autowired
	NiftyHistoryRepository niftyHistoryRepository;
	
	@Override
	public void write(List<? extends Nifty> nifty) throws Exception {
		System.out.println("Inserting... records" + nifty);
		niftyHistoryRepository.saveAll(nifty);
	}
	
		
	
}
