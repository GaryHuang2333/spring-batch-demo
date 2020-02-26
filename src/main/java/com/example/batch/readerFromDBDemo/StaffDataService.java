package com.example.batch.readerFromDBDemo;

import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
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
}
