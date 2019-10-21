package api.batch.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import api.batch.processor.model.Nifty;

@Component
public class ItemProcessorImpInNiftyHistory  implements ItemProcessor<Nifty, Nifty> {

	@Override
	public Nifty process(Nifty item) throws Exception {
		System.out.println("Processing...");
		Nifty nifty=item;
		//indN500.setCompany(item.getCompany().replaceAll("\\.", ""));
		return  nifty;
	}
	
}
