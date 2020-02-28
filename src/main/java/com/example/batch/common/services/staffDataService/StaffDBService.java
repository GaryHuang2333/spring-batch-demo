package com.example.batch.common.services.staffDataService;

import com.example.batch.common.entities.Staff;
import com.example.batch.common.services.IStaffDataService;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Service("StaffDBService")
public class StaffDBService implements IStaffDataService {
    @Autowired
    private DataSource dataSource;

    @Override
    public ItemReader<Staff> getItemReader() {
        JdbcPagingItemReader<Staff> reader = new JdbcPagingItemReader<>();
        reader.setDataSource(dataSource);
        reader.setFetchSize(3);
        reader.setQueryProvider(getAllStaffProvider());
        reader.setRowMapper(getStaffRowMapper());

        return reader;
    }


    private MySqlPagingQueryProvider getAllStaffProvider() {
        Map<String, Order> orderMap = new HashMap<>();
        orderMap.put("id", Order.ASCENDING);

        MySqlPagingQueryProvider provider = new MySqlPagingQueryProvider();
        provider.setSelectClause("*");
        provider.setFromClause("staff");
        provider.setSortKeys(orderMap);
        return provider;
    }

    private RowMapper<Staff> getStaffRowMapper() {
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
