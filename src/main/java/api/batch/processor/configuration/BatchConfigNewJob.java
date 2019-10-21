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

import api.batch.processor.model.NseListedALL;

@Configuration
@EnableBatchProcessing
@PropertySource("classpath:Messages.properties")
public class BatchConfigNewJob {

	@Bean("jobNewJob")
	public Job jobNewJob(JobBuilderFactory builderFactory,StepBuilderFactory stepBuilderFactory,
			ItemReader<NseListedALL> itemReader,
			ItemProcessor<NseListedALL,NseListedALL> itemProcessor,
			ItemWriter<NseListedALL> itemwriter) {
		
		Step step=stepBuilderFactory.get("Nse-ALL--File-Load")
				.<NseListedALL,NseListedALL>chunk(100)
				.reader(itemReader)
				.processor(itemProcessor)
				.writer(itemwriter)
				.build();
		
		return builderFactory.get("NSE-ALL-Load")
		.incrementer(new RunIdIncrementer())
		.start(step)
		.build();
		
		
	}
	
	@Bean
	public FlatFileItemReader<NseListedALL> itemReaderNewJob(@Value("${filename_all}") Resource resource){
		
		FlatFileItemReader<NseListedALL> flatFileItemReader=new FlatFileItemReader<NseListedALL>();
		flatFileItemReader.setResource(resource);
		flatFileItemReader.setName("CSV-Reader");
		flatFileItemReader.setLinesToSkip(1);
		flatFileItemReader.setLineMapper(lineMapperImplNewJob()); 
		
		return flatFileItemReader;
		
	}

	@Bean
	public LineMapper<NseListedALL> lineMapperImplNewJob() {
		
		DefaultLineMapper<NseListedALL> defaultlineMapper=new DefaultLineMapper<>();
		
		BeanWrapperFieldSetMapper<NseListedALL> beanWrapperFieldSetMapper=new BeanWrapperFieldSetMapper<>();
		beanWrapperFieldSetMapper.setTargetType(NseListedALL.class);
		
		DelimitedLineTokenizer delimitedLineTokenizer=new DelimitedLineTokenizer();
		delimitedLineTokenizer.setDelimiter(",");
		delimitedLineTokenizer.setStrict(false);
		delimitedLineTokenizer.setNames(new String[] {"code","symbol","isin","company","ldate","faceval"});
		
		defaultlineMapper.setLineTokenizer(delimitedLineTokenizer);
		defaultlineMapper.setFieldSetMapper(beanWrapperFieldSetMapper);
		return defaultlineMapper;
	}
	
}
