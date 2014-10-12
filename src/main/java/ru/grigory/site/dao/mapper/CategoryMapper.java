package ru.grigory.site.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.grigory.site.domain.Category;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: gr
 * Date: 30.08.14
 * Time: 20:11
 * To change this template use File | Settings | File Templates.
 */
public class CategoryMapper implements RowMapper<Category> {
    @Override
    public Category mapRow(ResultSet resultSet, int i) throws SQLException {
        Category category = new Category();
        category.setId(resultSet.getLong("id"));
        category.setName(resultSet.getString("name"));
        category.setDescription(resultSet.getString("description"));
        category.setGroup(resultSet.getInt("group"));
        category.setOrder(resultSet.getInt("order"));
        category.setDeleted(resultSet.getBoolean("deleted"));

        return category;
    }
}
