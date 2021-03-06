package ru.grigory.site.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.grigory.site.dao.mapper.PartnerMapper;
import ru.grigory.site.domain.Partner;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by grigory on 02.11.14.
 */
@Repository
public class PartnerDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public List<Partner> findAll() {
        return jdbcTemplate.query("select * from dveri.partner where deleted = false order by id", new PartnerMapper());
    }

    public Partner findById(long partnerId) {
        try {
            return jdbcTemplate.queryForObject("select * from dveri.partner where id=? and deleted = false", new PartnerMapper(), partnerId);
        }catch (EmptyResultDataAccessException ex){
            return null;
        }
    }

    public void add(Partner partner){
        String query = "insert into dveri.partner (name, url, banner) values (?,?,?)";
        jdbcTemplate.update(query, partner.getName(), partner.getUrl(), partner.getBanner());
    }

    public void update(Partner partner){
        String query = "update dveri.partner set name=?, url=?, banner=? where id=?";
        jdbcTemplate.update(query, partner.getName(), partner.getUrl(), partner.getBanner(), partner.getId());
    }

    public void delete(Long partnerId){
        jdbcTemplate.update("update dveri.partner set deleted = true where id=?", partnerId);
    }

    public void restore(Long partnerId){
        jdbcTemplate.update("update dveri.partner set deleted = false where id=?", partnerId);
    }

    public Partner findByIdWithDeleted(long id) {
        try {
            return jdbcTemplate.queryForObject("select * from dveri.partner where id=?", new PartnerMapper(), id);
        }catch (EmptyResultDataAccessException ex){
            return null;
        }
    }

    public List<Partner> findDeleted() {
        return jdbcTemplate.query("select * from dveri.partner where deleted = true order by id", new PartnerMapper());
    }
}
