package ru.grigory.site.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.grigory.site.dao.mapper.ProductMapper;
import ru.grigory.site.domain.Product;
import ru.grigory.site.exception.DaoException;
import ru.grigory.site.exception.ProductNotFoundException;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: gr
 * Date: 12.08.14
 * Time: 22:46
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class ProductDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public Product findById(Long id) throws DaoException{
        try{
           return jdbcTemplate.queryForObject("select * from product where deleted = false and id=?", new ProductMapper(), id);
        }catch (EmptyResultDataAccessException ex){
            return null;
        }

    }

    public Product findByIdWithDeleted(Long id) throws DaoException{
        try{
            return jdbcTemplate.queryForObject("select * from product where id=?", new ProductMapper(), id);
        }catch (EmptyResultDataAccessException ex){
            return null;
        }
    }

    public List<Product> findByCategory(long categoryId) throws DaoException{
        return jdbcTemplate.query("select * from product where deleted = false and category_id = ?", new ProductMapper(), categoryId);
    }

    public int countByCategory(long categoryId) throws DaoException{
        return findByCategory(categoryId).size();
    }

    public List<Product> getPageByCategoryId(long categoryId, int from, int to) throws DaoException{
        List<Product> list = findByCategory(categoryId);
        to = Math.min(to, list.size());
        return  findByCategory(categoryId).subList(from, to);
    }

    public void add(Product product){
        jdbcTemplate.update("insert into product (name, description, category_id, date_add) values (?,?,?, now())",
                product.getName(), product.getDescription(), product.getCategoryId());
    }

    public void update(Product product) throws ProductNotFoundException{
        Product oldProduct = findByIdWithDeleted(product.getId());
        if(oldProduct == null){
            throw new ProductNotFoundException("id=" + product.getId());
        }
        String query = "update product set name=?, description=?, category_id = ?, deleted =?, date_update=now()  where id =?";
        List params = new ArrayList();
        params.add(product.getName());
        params.add(product.getDescription());
        params.add(product.getCategoryId());
        params.add(product.isDeleted());
        params.add(product.getId());
        jdbcTemplate.update(query, params.toArray());
    }

    public void delete(long id) {
        jdbcTemplate.update("update product set date_delete = now(), deleted = true where id = ?", id);
        jdbcTemplate.update("update product_version set date_delete = now(), deleted = true where product_id = ?", id);

    }

    public void restore(long id) {
        String query = "update product set date_delete = null, deleted = false where id = ?";
        jdbcTemplate.update(query, id);
    }

    public List<Product> findDeleted() {
        return jdbcTemplate.query("select * from product where deleted = true order by date_delete desc", new ProductMapper());
    }
}
