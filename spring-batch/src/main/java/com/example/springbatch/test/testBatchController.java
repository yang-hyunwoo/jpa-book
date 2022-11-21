package com.example.springbatch.test;

import com.example.springbatch.part5.TestStatistics;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Configuration
@Slf4j
public class testBatchController {

    private final String JOB_NAME = "testJob";
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    private final TestExcelStepListener excelListener;
    private final EntityManagerFactory entityManagerFactory;

    private final DataSource dataSource;

    private int rowIdx = 0;

    public testBatchController(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory, TestExcelStepListener excelListener, EntityManagerFactory entityManagerFactory, DataSource dataSource) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.excelListener = excelListener;
        this.entityManagerFactory = entityManagerFactory;
        this.dataSource = dataSource;
    }

    @Bean(JOB_NAME)
    public Job testJob() throws Exception {
        System.out.println("1111111111111111");
        return this.jobBuilderFactory.get(JOB_NAME)
                .incrementer(new RunIdIncrementer())
                .start(this.testStep(null))
                .build();
    }

    @Bean
    @JobScope
    public Step testStep(@Value("#{jobParameters[date]}") String date) throws Exception {
        return this.stepBuilderFactory.get("testJob")
                .<TestStatistics, TestStatistics>chunk(100)
                .reader(orderStatisticsItemReader(date))
                .writer(excelWriter1())
                .listener(excelListener)
                .build();
    }

    private ItemReader<? extends TestStatistics> orderStatisticsItemReader(String date) throws Exception {
        System.out.println("1111111111111");
        YearMonth yearMonth = YearMonth.parse(date);
        Map<String, Object> parameters = new HashMap<>();


        String localDateStartDate = yearMonth.atDay(1)+" 00:00:00.000000";
        String localDateStartDate2 =  yearMonth.atEndOfMonth()+" 23:59:59.999999";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");
        LocalDateTime dateTime = LocalDateTime.parse(localDateStartDate, formatter);
        LocalDateTime dateTime2 = LocalDateTime.parse(localDateStartDate2, formatter);
        System.out.println("dateTime:::"+dateTime);
        System.out.println("dateTime2:::"+dateTime2);

        parameters.put("startDate", dateTime);
        parameters.put("endDate", dateTime2);

        System.out.println("parameters:::::"+parameters);
        Map<String, Order> sortKey = new HashMap<>();
        sortKey.put("created_at", Order.ASCENDING);

        JdbcPagingItemReader<TestStatistics> itemReader =  new JdbcPagingItemReaderBuilder<TestStatistics>()
                .dataSource(this.dataSource)
                .rowMapper((resultSet, rowNum) ->
                        TestStatistics.builder()
                        .personCount(resultSet.getString(1))
                        .date(resultSet.getString(2).substring(0,10))
                        .email(resultSet.getString(3))
                        .build())
                .pageSize(100)
                .name(JOB_NAME+"_orderStatisticsItemReader")
                .selectClause("person_count,tr.created_at,email")
                .fromClause("travel_agency_reservation tr inner join trip_user tu on tr.trip_user_id=tu.id")
                .whereClause("tr.created_at between :startDate and :endDate")
//                .groupClause("created_date")
                .parameterValues(parameters)
                .sortKeys(sortKey)
                .build();
//        System.out.println("itemReader:::"+itemReader);
        itemReader.afterPropertiesSet();
        return itemReader;
}

    private ItemWriter<TestStatistics> excelWriter1() {

        return items -> {
            Row row = null;
            Cell cell = null;
            Iterator e = items.iterator();

            while(e.hasNext()){
                row = excelListener.sheet.createRow(rowIdx++);
                Object[] t = (Object[]) e.next();

                for(int i = 0; i< Arrays.stream(t).count(); i++){
                    cell = row.createCell(i);
                    cell.setCellValue(t[i].toString());
                }
            }
        };
    }

    // csv 파일로 변환
    private ItemWriter<? super TestStatistics> orderStatisticsItemWriter(String date) throws Exception {
        YearMonth yearMonth = YearMonth.parse(date);
        String fileName = yearMonth.getYear() + " 년_" + yearMonth.getMonthValue() + "월_일별_주문_금액.csv";

        BeanWrapperFieldExtractor<TestStatistics> fieldExtractor =new BeanWrapperFieldExtractor<TestStatistics>();
        fieldExtractor.setNames(new String[] {"personCount" , "date","email"});
        DelimitedLineAggregator<TestStatistics> lineAggregator = new DelimitedLineAggregator<>();
        lineAggregator.setDelimiter(",");
        lineAggregator.setFieldExtractor(fieldExtractor);
        FlatFileItemWriter<TestStatistics> itemWriter =new FlatFileItemWriterBuilder<TestStatistics>()
                .resource(new FileSystemResource("output/" + fileName))
                .lineAggregator(lineAggregator)
                .name(JOB_NAME+"_orderStatisticsItemWriter")
                .encoding("UTF-8")
                .headerCallback(writer -> writer.write("total_amount,date,email"))
                .build();
        itemWriter.afterPropertiesSet();

        return itemWriter;
    }
}
