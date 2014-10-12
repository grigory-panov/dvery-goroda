package ru.grigory.site.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.grigory.site.domain.ProductVersion;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: gr
 * Date: 30.08.14
 * Time: 20:12
 * To change this template use File | Settings | File Templates.
 */
public class ProductVersionMapper implements RowMapper<ProductVersion> {
    @Override
    public ProductVersion mapRow(ResultSet resultSet, int i) throws SQLException {
        ProductVersion productVersion = new ProductVersion();
        productVersion.setId(resultSet.getLong("id"));
        productVersion.setProductId(resultSet.getLong("product_id"));
        productVersion.setSize(resultSet.getString("size"));
        productVersion.setOrder(resultSet.getInt("order"));
        productVersion.setDeleted(resultSet.getBoolean("deleted"));
        productVersion.setDescription(resultSet.getString("description"));
        productVersion.setName(resultSet.getString("name"));
        productVersion.setPrice(resultSet.getBigDecimal("price"));
        productVersion.setDateAdd(resultSet.getDate("date_add"));
        productVersion.setDateDelete(resultSet.getDate("date_delete"));
        productVersion.setDateUpdate(resultSet.getDate("date_update"));

        return productVersion;
    }
}
