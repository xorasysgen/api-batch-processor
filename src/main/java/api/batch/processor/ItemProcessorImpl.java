package api.batch.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import api.batch.processor.model.IndN500;

@Component
public class ItemProcessorImpl  implements ItemProcessor<IndN500, IndN500> {

	@Override
	public IndN500 process(IndN500 item) throws Exception {
		System.out.println("Processing...");
		IndN500 indN500=item;
		indN500.setCompany(item.getCompany().replaceAll("\\.", ""));
		return  indN500;
	}
	
}
