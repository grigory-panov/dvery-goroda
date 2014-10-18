package ru.grigory.site.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.grigory.site.domain.Feedback;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: gr
 * Date: 15.09.14
 * Time: 21:47
 * To change this template use File | Settings | File Templates.
 */
public class FeedbackMapper implements RowMapper<Feedback> {
    @Override
    public Feedback mapRow(ResultSet resultSet, int i) throws SQLException {
        Feedback feedback = new Feedback();
        feedback.setId(resultSet.getLong("id"));
        feedback.setDateAdd(resultSet.getTimestamp("date_add"));
        feedback.setDateApprove(resultSet.getTimestamp("date_approve"));
        feedback.setDateDelete(resultSet.getTimestamp("date_delete"));
        feedback.setAuthor(resultSet.getString("author"));
        feedback.setApproved(resultSet.getBoolean("approved"));
        feedback.setIp(resultSet.getString("ip"));
        feedback.setCity(resultSet.getString("city"));
        feedback.setCountry(resultSet.getString("country"));
        feedback.setDeleted(resultSet.getBoolean("deleted"));
        feedback.setText(resultSet.getString("text"));
        return feedback;

    }
}
