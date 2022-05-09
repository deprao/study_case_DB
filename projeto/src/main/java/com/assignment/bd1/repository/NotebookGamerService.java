package com.assignment.bd1.repository;

import com.assignment.bd1.models.NotebookGamer;
import com.assignment.bd1.models.NotebooksColetados;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class NotebookGamerService {

    @Autowired
    JdbcTemplate template;

    public List<NotebookGamer> findAll() {
        String sql = "select * from notebook_gamer";
        RowMapper<NotebookGamer> rm = new RowMapper<NotebookGamer>() {
            @Override
            public NotebookGamer mapRow(ResultSet resultSet, int i) throws SQLException {
                NotebookGamer notebookgamer = new NotebookGamer(
                        resultSet.getString("nome"),
                        resultSet.getString("modelo"),
                        resultSet.getString("sist_op"),
                        resultSet.getString("memo_ram"),
                        resultSet.getString("placa_video"),
                        resultSet.getString("processador"),
                        resultSet.getString("armazena_hd"),
                        resultSet.getString("armazena_ssd"),
                        resultSet.getString("marca")
                );

                return notebookgamer;
            }
        };

        return template.query(sql, rm);
    }

    public List<NotebookGamer> findByName(String name) {
        String sql = "select * from notebook_gamer WHERE nome LIKE ?";
        RowMapper<NotebookGamer> rm = new RowMapper<NotebookGamer>() {
            @Override
            public NotebookGamer mapRow(ResultSet resultSet, int i) throws SQLException {
                NotebookGamer notebookgamer = new NotebookGamer(
                        resultSet.getString("nome"),
                        resultSet.getString("modelo"),
                        resultSet.getString("sist_op"),
                        resultSet.getString("memo_ram"),
                        resultSet.getString("placa_video"),
                        resultSet.getString("processador"),
                        resultSet.getString("armazena_hd"),
                        resultSet.getString("armazena_ssd"),
                        resultSet.getString("marca")
                );

                return notebookgamer;
            }
        };

        String formattedName = "%"+name+"%";

        return template.query(sql, rm, formattedName);
    }

    public Integer returnNotebookGamerQuantity(){
        Integer total = 0;

        total = template.queryForObject("SELECT COUNT(*) FROM notebook_gamer", Integer.class);

        return total;
    }


}
