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

import api.batch.processor.model.IndN500;

@Configuration
@EnableBatchProcessing
@PropertySource("classpath:Messages.properties")
public class BatchConfig {

	@Bean
	public Job job(JobBuilderFactory builderFactory,StepBuilderFactory stepBuilderFactory,
			ItemReader<IndN500> itemReader,
			ItemProcessor<IndN500,IndN500> itemProcessor,
			ItemWriter<IndN500> itemwriter) {
		
		Step step=stepBuilderFactory.get("Index-N500--File-Load")
				.<IndN500,IndN500>chunk(100)
				.reader(itemReader)
				.processor(itemProcessor)
				.writer(itemwriter)
				.build();
		
		return builderFactory.get("Index-N500-Load")
		.incrementer(new RunIdIncrementer())
		.start(step)
		.build();
		
		
	}
	
	@Bean
	public FlatFileItemReader<IndN500> itemReader(@Value("${filename}") Resource resource){
		
		FlatFileItemReader<IndN500> flatFileItemReader=new FlatFileItemReader<IndN500>();
		flatFileItemReader.setResource(resource);
		flatFileItemReader.setName("CSV-Reader");
		flatFileItemReader.setLinesToSkip(1);
		flatFileItemReader.setLineMapper(lineMapperImpl()); 
		
		return flatFileItemReader;
		
	}

	@Bean
	public LineMapper<IndN500> lineMapperImpl() {
		
		DefaultLineMapper<IndN500> defaultlineMapper=new DefaultLineMapper<>();
		
		BeanWrapperFieldSetMapper<IndN500> beanWrapperFieldSetMapper=new BeanWrapperFieldSetMapper<>();
		beanWrapperFieldSetMapper.setTargetType(IndN500.class);
		
		DelimitedLineTokenizer delimitedLineTokenizer=new DelimitedLineTokenizer();
		delimitedLineTokenizer.setDelimiter(",");
		delimitedLineTokenizer.setStrict(false);
		delimitedLineTokenizer.setNames(new String[] {"code","company","industry","symbol","series","isinCode"});
		
		defaultlineMapper.setLineTokenizer(delimitedLineTokenizer);
		defaultlineMapper.setFieldSetMapper(beanWrapperFieldSetMapper);
		return defaultlineMapper;
	}
	
}
