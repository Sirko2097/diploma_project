package com.diploma.binpacking.dao.impl;

import com.diploma.binpacking.dao.CarDAO;
import com.diploma.binpacking.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
@PropertySource("classpath:sql/cardao.properties")
public class CarDAOImpl implements CarDAO {

    @Value("${GET_ALL}")
    private String GET_ALL;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private void setDataSource(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<Car> getAll() {
        return namedParameterJdbcTemplate.query(GET_ALL, new BeanPropertyRowMapper<>(Car.class));
    }
}
