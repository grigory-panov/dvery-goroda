package ru.grigory.site.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
           return jdbcTemplate.queryForObject("select * from dveri.product where deleted = false and id=?", new ProductMapper(), id);
        }catch (EmptyResultDataAccessException ex){
            return null;
        }

    }

    public Product findByIdWithDeleted(Long id) throws DaoException{
        try{
            return jdbcTemplate.queryForObject("select * from dveri.product where id=?", new ProductMapper(), id);
        }catch (EmptyResultDataAccessException ex){
            return null;
        }
    }

    public List<Product> findByCategory(long categoryId) throws DaoException{
        return jdbcTemplate.query("select * from dveri.product where deleted = false and category_id = ? order by \"order\"", new ProductMapper(), categoryId);
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
        try {
            jdbcTemplate.update("update dveri.product set \"order\" = \"order\"+1 where \"order\" >=? and category_id=?" +
                            " and exists (select * from dveri.product where \"order\" = ? and category_id=?)",
                    product.getOrder(),
                    product.getCategoryId(),
                    product.getOrder(),
                    product.getCategoryId());

            jdbcTemplate.update("insert into dveri.product (name, description, category_id, date_add, \"order\") values (?,?,?, now(), ?)",
                    product.getName(), product.getDescription(), product.getCategoryId(), product.getOrder());
        }catch (DataAccessException ex){
            throw new DaoException(ex.getMessage());
        }
    }

    public void update(Product product) throws ProductNotFoundException{
        Product oldProduct = findByIdWithDeleted(product.getId());
        if(oldProduct == null){
            throw new ProductNotFoundException("id=" + product.getId());
        }
        try {
            jdbcTemplate.update("update dveri.product set \"order\" = \"order\"+1 where \"order\" >=? and category_id=?" +
                            " and exists (select * from dveri.product where \"order\" = ? and category_id=? and id <> ?)",
                    product.getOrder(),
                    product.getCategoryId(),
                    product.getOrder(),
                    product.getCategoryId(),
                    product.getId());

            String query = "update dveri.product set name=?, description=?, category_id = ?, deleted =?, date_update=now(), \"order\"=? where id = ?";
            jdbcTemplate.update(query,
                    product.getName(),
                    product.getDescription(),
                    product.getCategoryId(),
                    product.isDeleted(),
                    product.getOrder(),
                    product.getId());
        }catch (DataAccessException ex){
            throw new DaoException(ex.getMessage());
        }
    }

    public void delete(long id) {
        jdbcTemplate.update("update dveri.product set date_delete = now(), deleted = true where id = ?", id);
        jdbcTemplate.update("update dveri.product_version set date_delete = now(), deleted = true where product_id = ?", id);

    }

    public void restore(long id) {
        String query = "update dveri.product set date_delete = null, deleted = false where id = ?";
        jdbcTemplate.update(query, id);
    }

    public List<Product> findDeleted() {
        return jdbcTemplate.query("select * from dveri.product where deleted = true order by date_delete desc", new ProductMapper());
    }

    public int getMaxOrderInCategory(long categoryId) {
        return jdbcTemplate.queryForObject("select coalesce(max(\"order\"),0) from dveri.product where category_id=?", Integer.class, categoryId);
    }

    public List<Product> getRelatedProducts(long productId) {
        return jdbcTemplate.query("select * from dveri.product where id in (select related_id from product_relation where product_id=?)", new ProductMapper(), productId);
    }

    public void addRelation(long productId, long relationId) {
        jdbcTemplate.update("insert into dveri.product_relation (product_id, related_id)  select ?, ?" +
                " where not exists (select * from dveri.product_relation where product_id = ? and related_id = ?)",
                productId, relationId, productId, relationId);

    }

    public void deleteRelation(long productId, long relationId) {
        jdbcTemplate.update("delete from dveri.product_relation where product_id = ? and related_id = ?",
                productId, relationId);

    }

}
