package ru.grigory.site.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.grigory.site.domain.Info;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: gr
 * Date: 30.08.14
 * Time: 20:11
 * To change this template use File | Settings | File Templates.
 */
public class InfoMapper implements RowMapper<Info> {
    @Override
    public Info mapRow(ResultSet resultSet, int i) throws SQLException {
        Info info = new Info();
        info.setId(resultSet.getLong("id"));
        info.setHeader(resultSet.getString("header"));
        info.setBody(resultSet.getString("body"));
        info.setDateAdd(resultSet.getDate("date_add"));
        info.setDeleted(resultSet.getBoolean("deleted"));
        return info;
    }
}
