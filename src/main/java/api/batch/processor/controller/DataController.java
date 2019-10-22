package api.batch.processor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import api.batch.processor.model.IndN500;
import api.batch.processor.model.Nifty;
import api.batch.processor.model.NseListedALL;
import api.batch.processor.repository.NiftyALLRepository;
import api.batch.processor.repository.NiftyHistoryRepository;
import api.batch.processor.repository.NiftyRepository;

@RestController("/")
public class DataController {
	
	
	private static int i=0;
	@Autowired
	NiftyRepository niftyRepository;
	
	@Autowired
	NiftyALLRepository niftyALLRepository;
	
	@Autowired
	NiftyHistoryRepository niftyHistoryRepository;
	
		
	@GetMapping
	public String test() {
		
		System.out.println("OK.200 count" + i++);
		return "OK.200";
	}
	
	@GetMapping("/data")
	public List<IndN500> getIndN500() {
		return niftyRepository.findAll();
		
	}
	
	

	@GetMapping("/data/all")
	public List<NseListedALL> getIndN500ALL() {
		return niftyALLRepository.findAll();
		
	}
	
	
	@GetMapping("/data/nifty")
	public List<Nifty> getHistoricalNiftyALL() {
		return niftyHistoryRepository.findAll();
		
	}

	
	
	

}
