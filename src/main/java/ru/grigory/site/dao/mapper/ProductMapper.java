package ru.grigory.site.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.grigory.site.domain.Product;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: gr
 * Date: 30.08.14
 * Time: 20:11
 * To change this template use File | Settings | File Templates.
 */
public class ProductMapper implements RowMapper<Product> {

    @Override
    public Product mapRow(ResultSet resultSet, int i) throws SQLException {
        Product product = new Product();
        product.setId(resultSet.getLong("id"));
        product.setName(resultSet.getString("name"));
        product.setDescription(resultSet.getString("description"));
        product.setCategoryId(resultSet.getLong("category_id"));
        product.setDateAdd(resultSet.getTimestamp("date_add"));
        product.setDateDelete(resultSet.getTimestamp("date_delete"));
        product.setDateUpdate(resultSet.getTimestamp("date_update"));
        product.setDeleted(resultSet.getBoolean("deleted"));
        product.setOrder(resultSet.getInt("order"));
        return product;
    }
}
