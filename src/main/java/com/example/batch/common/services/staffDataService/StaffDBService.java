package com.example.batch.common.services.staffDataService;

import com.example.batch.common.entities.Staff;
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

@Service("staffDBService")
public class StaffDBService implements IStaffDataService {
    @Autowired
    private DataSource dataSource;

    @Override
    public ItemReader setupItemReader(ItemReader itemReader) {
        if (itemReader instanceof JdbcPagingItemReader) {
            JdbcPagingItemReader jdbcPagingItemReader = (JdbcPagingItemReader) itemReader;
            jdbcPagingItemReader.setDataSource(dataSource);
            jdbcPagingItemReader.setFetchSize(3);
            jdbcPagingItemReader.setQueryProvider(getAllStaffProvider());
            jdbcPagingItemReader.setRowMapper(getStaffRowMapper());
            itemReader = jdbcPagingItemReader;
        }

        return itemReader;
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
