package net.sixpointsix.carpo.mi.spring;

import net.sixpointsix.carpo.common.extractor.DataSetExtractor;
import net.sixpointsix.carpo.common.extractor.DirectPropertyExtractor;
import net.sixpointsix.carpo.common.extractor.PropertyExtractor;
import net.sixpointsix.carpo.common.extractor.StreamingDataSetExtractor;
import net.sixpointsix.carpo.common.writer.CsvDataWriter;
import net.sixpointsix.carpo.mi.CsvMIManager;
import net.sixpointsix.carpo.mi.MIManager;
import net.sixpointsix.carpo.mi.spring.configuration.MIConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CarpoMIConfiguration {

    @Bean
    @ConditionalOnMissingBean(MIManager.class)
    public MIManager miManager(DataSetExtractor dataSetExtractor, CsvDataWriter csvDataWriter) {
        return new CsvMIManager(dataSetExtractor, csvDataWriter);
    }

    @Bean
    @ConditionalOnMissingBean(DataSetExtractor.class)
    public DataSetExtractor dataSetExtractor(PropertyExtractor propertyExtractor) {
        return new StreamingDataSetExtractor(propertyExtractor);
    }

    @Bean
    @ConditionalOnMissingBean(PropertyExtractor.class)
    public PropertyExtractor propertyExtractor() {
        return new DirectPropertyExtractor();
    }

    @Bean
    @ConditionalOnMissingBean(CsvDataWriter.class)
    public CsvDataWriter csvDataWriter() {
        return new CsvDataWriter();
    }

    @Bean
    public MIConfiguration miConfiguration() {
        return new MIConfiguration();
    }

    @Bean
    public SpringMIExtractionConfiguration springMIExtractionConfiguration(MIConfiguration configuration) {
        return new SpringMIExtractionConfiguration(configuration);
    }
}
