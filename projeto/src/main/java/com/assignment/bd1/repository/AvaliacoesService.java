package com.assignment.bd1.repository;

import com.assignment.bd1.models.Avaliacoes;
import com.assignment.bd1.models.NotebookGamer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class AvaliacoesService {

    @Autowired
    JdbcTemplate template;

    public List<Avaliacoes> findAll() {
        String sql = "select * from avaliacoes";
        RowMapper<Avaliacoes> rm = new RowMapper<Avaliacoes>() {
            @Override
            public Avaliacoes mapRow(ResultSet resultSet, int i) throws SQLException {
                Avaliacoes avaliacoes = new Avaliacoes(
                        resultSet.getString("modelo_avaliacao"),
                        resultSet.getDouble("nota"),
                        resultSet.getString("descricao"),
                        resultSet.getString("nome_avaliador"),
                        resultSet.getInt("vezes_avaliado")
                );
                return avaliacoes;
            }
        };

        return template.query(sql, rm);
    }

    public List<Avaliacoes> findByModel(String model) {
        String sql = "select * from avaliacoes WHERE modelo_avaliacao = ?";
        RowMapper<Avaliacoes> rm = new RowMapper<Avaliacoes>() {
            @Override
            public Avaliacoes mapRow(ResultSet resultSet, int i) throws SQLException {
                Avaliacoes avaliacoes = new Avaliacoes(
                        resultSet.getString("modelo_avaliacao"),
                        resultSet.getDouble("nota"),
                        resultSet.getString("descricao"),
                        resultSet.getString("nome_avaliador"),
                        resultSet.getInt("vezes_avaliado")
                );

                return avaliacoes;
            }
        };

        return template.query(sql, rm, model);
    }

    public Integer returnAvaliacoesQuantity(){
        Integer total = 0;

        total = template.queryForObject("SELECT COUNT(*) FROM avaliacoes", Integer.class);

        return total;
    }


}
