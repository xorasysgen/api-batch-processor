package api.batch.processor;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import api.batch.processor.model.NseListedALL;
import api.batch.processor.repository.NiftyALLRepository;

@Component
public class ItemWriterImplnNewJob implements  ItemWriter<NseListedALL> {

	@Autowired
	NiftyALLRepository niftyAllRepository;
	
	@Override
	public void write(List<? extends NseListedALL> nseListedALL) throws Exception {
		System.out.println("Inserting... records" + nseListedALL);
		niftyAllRepository.saveAll(nseListedALL);
	}
	
		
	
}
