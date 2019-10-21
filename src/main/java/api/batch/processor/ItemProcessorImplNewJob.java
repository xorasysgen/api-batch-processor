package api.batch.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import api.batch.processor.model.NseListedALL;

@Component
public class ItemProcessorImplNewJob  implements ItemProcessor<NseListedALL, NseListedALL> {

	@Override
	public NseListedALL process(NseListedALL item) throws Exception {
		System.out.println("Processing...");
		NseListedALL nseListedALL=item;
		//indN500.setCompany(item.getCompany().replaceAll("\\.", ""));
		return  nseListedALL;
	}
	
}
