package com.assignment.bd1.repository;
import com.assignment.bd1.models.Crawls;
import com.assignment.bd1.models.NotebooksColetados;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class NotebooksColetadosService {

    @Autowired
    JdbcTemplate template;

    public List<NotebooksColetados> findAll() {
        String sql = "select * from ng_coletado";
        RowMapper<NotebooksColetados> rm = new RowMapper<NotebooksColetados>() {
            @Override
            public NotebooksColetados mapRow(ResultSet resultSet, int i) throws SQLException {
                NotebooksColetados notebookcoletado = new NotebooksColetados(
                        resultSet.getLong("id"),
                        resultSet.getString("modelo"),
                        resultSet.getString("sist_op"),
                        resultSet.getString("memo_ram"),
                        resultSet.getString("placa_video"),
                        resultSet.getString("processador"),
                        resultSet.getString("armazena_hd"),
                        resultSet.getString("armazena_ssd"),
                        resultSet.getString("loja_retirada"),
                        resultSet.getString("preco"),
                        resultSet.getString("data_coleta")
                );

                return notebookcoletado;
            }
        };

        return template.query(sql, rm);
    }


}
