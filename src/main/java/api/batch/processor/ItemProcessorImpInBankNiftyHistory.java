package api.batch.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import api.batch.processor.model.BankNifty;

@Component
public class ItemProcessorImpInBankNiftyHistory  implements ItemProcessor<BankNifty, BankNifty> {

	@Override
	public BankNifty process(BankNifty item) throws Exception {
		System.out.println("Processing...");
		BankNifty bnifty=item;
		//indN500.setCompany(item.getCompany().replaceAll("\\.", ""));
		return  bnifty;
	}
	
}
