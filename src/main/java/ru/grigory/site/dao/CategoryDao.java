package ru.grigory.site.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.grigory.site.dao.mapper.CategoryMapper;
import ru.grigory.site.domain.Category;
import ru.grigory.site.exception.DaoException;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: gr
 * Date: 12.08.14
 * Time: 22:26
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class CategoryDao {

    private JdbcTemplate jdbcTemplate;


    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Category> findAll() throws DaoException {
        return jdbcTemplate.query("select * from dveri.category where deleted = false order by \"group\", \"order\"", new CategoryMapper());
    }

    public Category findById(Long categoryId) {
        try{
            return jdbcTemplate.queryForObject("select * from dveri.category where deleted = false and id = ?", new CategoryMapper(), categoryId);
        }catch (EmptyResultDataAccessException ex){
            return null;
        }
    }

    public Category findByIdWithDeleted(Long categoryId) {
        try{
            return jdbcTemplate.queryForObject("select * from dveri.category where id = ?", new CategoryMapper(), categoryId);
        }catch (EmptyResultDataAccessException ex){
            return null;
        }
    }

    public void add(Category category){
        String query = "insert into dveri.category(name, description, \"group\", \"order\") values(?, ?, ?, ?)";
        jdbcTemplate.update(query, category.getName(), category.getDescription(), category.getGroup(), category.getOrder());
    }

    public void update(Category category){
        String query = "update dveri.category set name=?, description=?, \"group\"=?, \"order\"=? where id =?";
        jdbcTemplate.update(query, category.getName(), category.getDescription(), category.getGroup(), category.getOrder(), category.getId());
    }

    public void delete(long id) {

        jdbcTemplate.update("update dveri.category set deleted = true where id = ?", id);
        jdbcTemplate.update("update dveri.product set date_delete = now(), deleted = true where category_id = ?", id);
        jdbcTemplate.update("update dveri.product_version set date_delete = now(), deleted = true where product_id in (select id from product where category_id = ?)", id);

    }

    public void restore(long id) {
        String query = "update dveri.category set deleted = false where id = ?";
        jdbcTemplate.update(query, id);
    }

    public List<Category> findDeleted() {
        return jdbcTemplate.query("select * from dveri.category where deleted = true order by id desc", new CategoryMapper());
    }
}
