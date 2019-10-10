package api.batch.processor.controller;

import java.util.HashMap;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class RunBatchController {

	@Autowired
	JobLauncher jobLauncher;
	
	@Autowired
	Job job;
	
	
	@GetMapping("/run")
	public BatchStatus runBatch() {
		HashMap<String,JobParameter> map=new HashMap<>();
		map.put("Time", new JobParameter(System.currentTimeMillis()));
		JobParameters jobParameters=new JobParameters(map);
		try {
			JobExecution je=jobLauncher.run(job, jobParameters);
			System.out.println("Status:" + je.getStatus());
			while(je.isRunning()) {
				System.out.println("Job Running");
			}
			return je.getStatus();
		} catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
				| JobParametersInvalidException e) {
			e.printStackTrace();
		}
		return null;
		
		
	}
	
	
	
}
