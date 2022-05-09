package com.assignment.bd1.repository;
import com.assignment.bd1.models.Crawls;
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
                        resultSet.getString("modelo_coletado"),
                        resultSet.getString("loja_retirada"),
                        resultSet.getDouble("preco"),
                        resultSet.getString("data_coleta")
                );

                return notebookcoletado;
            }
        };

        return template.query(sql, rm);
    }

    public List<NotebooksColetados> findByPrice(Double preco) {
        String sql = "select * from ng_coletado WHERE (CAST(preco AS DOUBLE PRECISION)) <= ?";
        RowMapper<NotebooksColetados> rm = new RowMapper<NotebooksColetados>() {
            @Override
            public NotebooksColetados mapRow(ResultSet resultSet, int i) throws SQLException {
                NotebooksColetados notebookcoletado = new NotebooksColetados(
                        resultSet.getLong("id"),
                        resultSet.getString("modelo_coletado"),
                        resultSet.getString("loja_retirada"),
                        resultSet.getDouble("preco"),
                        resultSet.getString("data_coleta")
                );

                return notebookcoletado;
            }
        };

        return template.query(sql, rm, preco);
    }

    public List<NotebooksColetados> findByModel(String modelo) {
        String sql = "select * from ng_coletado WHERE modelo_coletado LIKE ?";
        RowMapper<NotebooksColetados> rm = new RowMapper<NotebooksColetados>() {
            @Override
            public NotebooksColetados mapRow(ResultSet resultSet, int i) throws SQLException {
                NotebooksColetados notebookcoletado = new NotebooksColetados(
                        resultSet.getLong("id"),
                        resultSet.getString("modelo_coletado"),
                        resultSet.getString("loja_retirada"),
                        resultSet.getDouble("preco"),
                        resultSet.getString("data_coleta")
                );

                return notebookcoletado;
            }
        };

        String formattedName = "%"+modelo+"%";

        return template.query(sql, rm, formattedName);
    }

    public List<NotebooksColetados> orderByPriceAsc() {
        String sql = "select * from ng_coletado ORDER BY preco ASC";
        RowMapper<NotebooksColetados> rm = new RowMapper<NotebooksColetados>() {
            @Override
            public NotebooksColetados mapRow(ResultSet resultSet, int i) throws SQLException {
                NotebooksColetados notebookcoletado = new NotebooksColetados(
                        resultSet.getLong("id"),
                        resultSet.getString("modelo_coletado"),
                        resultSet.getString("loja_retirada"),
                        resultSet.getDouble("preco"),
                        resultSet.getString("data_coleta")
                );

                return notebookcoletado;
            }
        };

        return template.query(sql, rm);
    }

    public List<NotebooksColetados> orderByPriceDesc() {
        String sql = "select * from ng_coletado ORDER BY preco DESC";
        RowMapper<NotebooksColetados> rm = new RowMapper<NotebooksColetados>() {
            @Override
            public NotebooksColetados mapRow(ResultSet resultSet, int i) throws SQLException {
                NotebooksColetados notebookcoletado = new NotebooksColetados(
                        resultSet.getLong("id"),
                        resultSet.getString("modelo_coletado"),
                        resultSet.getString("loja_retirada"),
                        resultSet.getDouble("preco"),
                        resultSet.getString("data_coleta")
                );

                return notebookcoletado;
            }
        };

        return template.query(sql, rm);
    }

    public Integer returnNgColetadoPriceLess(Double price){
        Integer total = 0;

        total = template.queryForObject("SELECT COUNT(*) FROM ng_coletado WHERE preco < ?", Integer.class, price);

        return total;
    }

    public Integer returnNgColetadoPriceLessMore(Double price1, Double price2){
        Integer total = 0;

        total = template.queryForObject("SELECT COUNT(*) FROM ng_coletado WHERE preco > ? AND preco < ? ", Integer.class, price1, price2);

        return total;
    }

    public Integer returnNgColetadoPriceMore(Double price){
        Integer total = 0;

        total = template.queryForObject("SELECT COUNT(*) FROM ng_coletado WHERE preco > ?", Integer.class, price);

        return total;
    }


}
