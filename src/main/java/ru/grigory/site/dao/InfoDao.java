package ru.grigory.site.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.grigory.site.dao.mapper.InfoMapper;
import ru.grigory.site.domain.Info;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: gr
 * Date: 30.08.14
 * Time: 20:02
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class InfoDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public List<Info> findAll() {
        return jdbcTemplate.query("select * from dveri.info where deleted = false order by date_add desc", new InfoMapper());
    }

    public Info findById(long id) {
        String query = "select * from dveri.info where deleted = false and id = ?";
        try {
            return jdbcTemplate.queryForObject(query, new InfoMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    public Info findByIdWithDeleted(long id) {
        String query = "select * from dveri.info where id = ?";
        try {
            return jdbcTemplate.queryForObject(query, new InfoMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }


    public void add(Info info) {
        jdbcTemplate.update("insert into dveri.info (header, body, date_add) values (?, ?, now())",
                info.getHeader(), info.getBody());
    }

    public void update(Info info) {
        jdbcTemplate.update("update dveri.info set header = ?, body=? where id =?",
                info.getHeader(), info.getBody(), info.getId());
    }

    public void delete(long id){
        jdbcTemplate.update("update dveri.info set deleted = true where id=?", id);
    }

    public void restore(long id){
        jdbcTemplate.update("update dveri.info set deleted = false where id=?", id);
    }


    public List<Info> findDeleted(){
         return jdbcTemplate.query("select * from dveri.info where deleted = true", new InfoMapper());
    }

}
