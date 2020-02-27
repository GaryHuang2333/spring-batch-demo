package com.example.batch.common.services;

import com.example.batch.common.entities.Staff;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Service
public class StaffDataService {
    @Autowired
    private DataSource dataSource;

    public DataSource getDataSource() {
        return dataSource;
    }

    public MySqlPagingQueryProvider getAllStaffProvider() {
        Map<String, Order> orderMap = new HashMap<>();
        orderMap.put("id", Order.ASCENDING);

        MySqlPagingQueryProvider provider = new MySqlPagingQueryProvider();
        provider.setSelectClause("*");
        provider.setFromClause("staff");
        provider.setSortKeys(orderMap);
        return provider;
    }

    public RowMapper<Staff> getStaffRowMapper() {
        // beautiful code with lambda
        return (resultSet, i) -> new Staff().builder()
                .id(resultSet.getInt("id"))
                .age(resultSet.getInt("age"))
                .department(resultSet.getString("department"))
                .gender(resultSet.getString("gender"))
                .name(resultSet.getString("name"))
                .comment(resultSet.getString("comment"))
                .staffNo(resultSet.getString("staff_no"))
                .build();
    }

    public FieldSetMapper<Staff> getFieldSetMapper() {
        return fieldSet -> Staff.builder()
                .id(fieldSet.readInt("id"))
                .age(fieldSet.readInt("age"))
                .department(fieldSet.readString("department"))
                .gender(fieldSet.readString("gender"))
                .name(fieldSet.readString("name"))
                .comment(fieldSet.readString("comment"))
                .staffNo(fieldSet.readString("staff_no")).build();
    }

    public DelimitedLineTokenizer getDelimitedLineTokenizer() {
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setNames(new String[]{"id", "age", "department", "gender", "name", "comment", "staff_no"});
        return tokenizer;
    }

    public LineMapper<Staff> getStaffLineMapper() {
        DefaultLineMapper mapper = new DefaultLineMapper();
        mapper.setLineTokenizer(getDelimitedLineTokenizer());
        mapper.setFieldSetMapper(getFieldSetMapper());
        mapper.afterPropertiesSet();
        return mapper;
    }

}
