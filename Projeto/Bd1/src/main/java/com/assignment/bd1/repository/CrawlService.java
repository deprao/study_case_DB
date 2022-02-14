package com.assignment.bd1.repository;

import com.assignment.bd1.models.Crawls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class CrawlService {

    @Autowired
    JdbcTemplate template;

    public List<Crawls> findAll() {
        String sql = "select * from crawlers";
        RowMapper<Crawls> rm = new RowMapper<Crawls>() {
            @Override
            public Crawls mapRow(ResultSet resultSet, int i) throws SQLException {
                Crawls crawl = new Crawls(
                        resultSet.getInt("id"),
                        resultSet.getString("versao"),
                        resultSet.getString("loja_coleta"),
                        resultSet.getString("data_inclusao"),
                        resultSet.getString("data_alteracao")
                );

                return crawl;
            }
        };

        return template.query(sql, rm);
    }


}
