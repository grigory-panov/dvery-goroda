package ru.grigory.site.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.grigory.site.domain.Partner;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by grigory on 02.11.14.
 */
public class PartnerMapper implements RowMapper<Partner> {
    @Override
    public Partner mapRow(ResultSet resultSet, int i) throws SQLException {
        Partner partner = new Partner();
        partner.setId(resultSet.getLong("id"));
        partner.setName(resultSet.getString("name"));
        partner.setUrl(resultSet.getString("url"));
        partner.setBanner(resultSet.getBytes("banner"));
        partner.setDeleted(resultSet.getBoolean("deleted"));
        return partner;
    }
}
