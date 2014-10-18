package ru.grigory.site.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.grigory.site.dao.mapper.FeedbackMapper;
import ru.grigory.site.domain.Feedback;

import javax.sql.DataSource;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: gr
 * Date: 31.08.14
 * Time: 0:05
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class FeedbackDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public int countApproved() {
        return jdbcTemplate.queryForObject("select count (*) from feedback where approved=true and deleted=false", Integer.class);
    }

    public List<Feedback> getPageApproved(int from, int to) {
        return jdbcTemplate.query("select * from feedback where approved=true and deleted = false order by date_add desc offset ? limit ?", new FeedbackMapper(), from, to);
    }

    public int count() {
        return jdbcTemplate.queryForObject("select count (*) from feedback where deleted = false", Integer.class);
    }

    public List<Feedback> getPage(int from, int to) {
        return jdbcTemplate.query("select * from feedback where deleted=false order by date_add desc offset ? limit ?", new FeedbackMapper(), from, to);
    }


    public void addFeedback(Feedback fb) {
        jdbcTemplate.update("insert into feedback (author, date_add, text, ip, country, city) values(?, now(), ?, ?, ?,?)",
                fb.getAuthor(), fb.getText(), fb.getIp(), fb.getCountry(), fb.getCity());
    }

    public void approve(Long id){
        jdbcTemplate.update("update feedback set approved = true, date_approve=now() where id=?", id);
    }

    public void delete(Long id){
        jdbcTemplate.update("update feedback set deleted = true, date_delete=now() where id=?", id);
    }


    public Feedback findByIdWithDeleted(long id) {
        return jdbcTemplate.queryForObject("select * from feedback where id=?", new FeedbackMapper(), id);
    }
}
