package api.batch.processor.configuration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;

import api.batch.processor.model.BankNifty;

@Configuration
@EnableBatchProcessing
@PropertySource("classpath:Messages.properties")
public class BatchConfigBankNifty {

	@Bean("bankNiftyJob")
	public Job bankNiftyJob(JobBuilderFactory builderFactory,StepBuilderFactory stepBuilderFactory,
			ItemReader<BankNifty> itemReader,
			ItemProcessor<BankNifty,BankNifty> itemProcessor,
			ItemWriter<BankNifty> itemwriter) {
		
		Step step=stepBuilderFactory.get("BankNifty-Historical--File-Load")
				.<BankNifty,BankNifty>chunk(100)
				.reader(itemReader)
				.processor(itemProcessor)
				.writer(itemwriter)
				.build();
		
		return builderFactory.get("BankNifty-ALL-Load")
		.incrementer(new RunIdIncrementer())
		.start(step)
		.build();
		
		
	}
	
	@Bean
	public FlatFileItemReader<BankNifty> itemReaderBankNIFTY(@Value("${filename_history_Bank_nifty}") Resource resource){
		
		FlatFileItemReader<BankNifty> flatFileItemReader=new FlatFileItemReader<BankNifty>();
		flatFileItemReader.setResource(resource);
		flatFileItemReader.setName("CSV-Reader");
		flatFileItemReader.setLinesToSkip(1);
		flatFileItemReader.setLineMapper(lineMapperImplBankNifty()); 
		
		return flatFileItemReader;
		
	}

	@Bean
	public LineMapper<BankNifty> lineMapperImplBankNifty() {
		
		DefaultLineMapper<BankNifty> defaultlineMapper=new DefaultLineMapper<>();
		
		BeanWrapperFieldSetMapper<BankNifty> beanWrapperFieldSetMapper=new BeanWrapperFieldSetMapper<>();
		beanWrapperFieldSetMapper.setTargetType(BankNifty.class);
		
		DelimitedLineTokenizer delimitedLineTokenizer=new DelimitedLineTokenizer();
		delimitedLineTokenizer.setDelimiter(",");
		delimitedLineTokenizer.setStrict(false);
		delimitedLineTokenizer.setNames(new String[] {"code","date","open","high","low","close"});
		
		defaultlineMapper.setLineTokenizer(delimitedLineTokenizer);
		defaultlineMapper.setFieldSetMapper(beanWrapperFieldSetMapper);
		return defaultlineMapper;
	}
	
}
