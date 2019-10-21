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

import api.batch.processor.model.Nifty;

@Configuration
@EnableBatchProcessing
@PropertySource("classpath:Messages.properties")
public class BatchConfigNifty {

	@Bean("niftyJob")
	public Job niftyJob(JobBuilderFactory builderFactory,StepBuilderFactory stepBuilderFactory,
			ItemReader<Nifty> itemReader,
			ItemProcessor<Nifty,Nifty> itemProcessor,
			ItemWriter<Nifty> itemwriter) {
		
		Step step=stepBuilderFactory.get("Nifty-Historical--File-Load")
				.<Nifty,Nifty>chunk(100)
				.reader(itemReader)
				.processor(itemProcessor)
				.writer(itemwriter)
				.build();
		
		return builderFactory.get("Nifty-ALL-Load")
		.incrementer(new RunIdIncrementer())
		.start(step)
		.build();
		
		
	}
	
	@Bean
	public FlatFileItemReader<Nifty> itemReaderNIFTY(@Value("${filename_history}") Resource resource){
		
		FlatFileItemReader<Nifty> flatFileItemReader=new FlatFileItemReader<Nifty>();
		flatFileItemReader.setResource(resource);
		flatFileItemReader.setName("CSV-Reader");
		flatFileItemReader.setLinesToSkip(1);
		flatFileItemReader.setLineMapper(lineMapperImplNifty()); 
		
		return flatFileItemReader;
		
	}

	@Bean
	public LineMapper<Nifty> lineMapperImplNifty() {
		
		DefaultLineMapper<Nifty> defaultlineMapper=new DefaultLineMapper<>();
		
		BeanWrapperFieldSetMapper<Nifty> beanWrapperFieldSetMapper=new BeanWrapperFieldSetMapper<>();
		beanWrapperFieldSetMapper.setTargetType(Nifty.class);
		
		DelimitedLineTokenizer delimitedLineTokenizer=new DelimitedLineTokenizer();
		delimitedLineTokenizer.setDelimiter(",");
		delimitedLineTokenizer.setStrict(false);
		delimitedLineTokenizer.setNames(new String[] {"code","date","open","high","low","close"});
		
		defaultlineMapper.setLineTokenizer(delimitedLineTokenizer);
		defaultlineMapper.setFieldSetMapper(beanWrapperFieldSetMapper);
		return defaultlineMapper;
	}
	
}
