package api.batch.processor;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import api.batch.processor.model.IndN500;
import api.batch.processor.repository.NiftyRepository;

@Component
public class ItemWriterImpl implements  ItemWriter<IndN500> {

	@Autowired
	NiftyRepository niftyRepository;
	
	@Override
	public void write(List<? extends IndN500> indN500) throws Exception {
		System.out.println("Inserting... records" + indN500);
		niftyRepository.saveAll(indN500);
	}
	
		
	
}
