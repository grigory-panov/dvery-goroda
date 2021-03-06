package ru.grigory.site.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.grigory.site.dao.mapper.ProductVersionMapper;
import ru.grigory.site.domain.Product;
import ru.grigory.site.domain.ProductVersion;
import ru.grigory.site.exception.DaoException;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: gr
 * Date: 12.08.14
 * Time: 23:17
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class ProductVersionDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<ProductVersion> findByProduct(long productId) {
        String query = "select * from dveri.product_version where product_id = ? and deleted = false order by \"order\"";
        return jdbcTemplate.query(query, new ProductVersionMapper(), productId);
    }

    public ProductVersion findById(long id) {
        String query = "select * from dveri.product_version where id = ? and deleted = false";
        try {
            return jdbcTemplate.queryForObject(query, new ProductVersionMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }

    }

    public ProductVersion findFirstVersion(long productId) {
        String query = "select * from dveri.product_version where product_id = ? and deleted = false order by \"order\" limit 1";
        try {
            return jdbcTemplate.queryForObject(query, new ProductVersionMapper(), productId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }

    }

    public void add(ProductVersion productVersion) {
        try {

            jdbcTemplate.update("update dveri.product_version v set \"order\" = \"order\"+1 where \"order\" >=? and product_id=?" +
                            " and exists (select * from product_version where \"order\" = ? and product_id=?)",
                    productVersion.getOrder(),
                    productVersion.getProductId(),
                    productVersion.getOrder(),
                    productVersion.getProductId());

            String query = "insert into dveri.product_version ( product_id, price, name, description, size, date_add, \"order\") values (?,?,?,?,?,?,?) returning id";

            Long id = jdbcTemplate.queryForObject(query, Long.class,
                    productVersion.getProductId(),
                    productVersion.getPrice(),
                    productVersion.getName(),
                    productVersion.getDescription(),
                    productVersion.getSize(),
                    new Date(),
                    productVersion.getOrder());
            productVersion.setId(id);
        } catch (DataAccessException ex) {
            throw new DaoException(ex.getMessage());
        }
    }

    public void update(ProductVersion productVersion) {
        try {
            jdbcTemplate.update("update dveri.product_version set \"order\" = \"order\"+1 where \"order\" >=? and product_id=? " +
                            "and exists (select * from product_version where \"order\" = ? and product_id = ? and id <> ?)",
                    productVersion.getOrder(),
                    productVersion.getProductId(),
                    productVersion.getOrder(),
                    productVersion.getProductId(),
                    productVersion.getId());

            String query = "UPDATE dveri.product_version\n" +
                    "   SET product_id=?, price=?, name=?, description=?, size=?, \n" +
                    "       date_update=now(), \"order\"=?\n" +
                    " WHERE id=?";
            jdbcTemplate.update(query,
                    productVersion.getProductId(),
                    productVersion.getPrice(),
                    productVersion.getName(),
                    productVersion.getDescription(),
                    productVersion.getSize(),
                    productVersion.getOrder(),
                    productVersion.getId());
        }catch (DataAccessException ex){
            throw new DaoException(ex.getMessage());
        }
    }

    public void delete(long id) {
        String query = "update dveri.product_version set date_delete = now(), deleted = true where id = ?";
        jdbcTemplate.update(query, id);
    }

    public void restore(long id) {
        String query = "update dveri.product_version set date_delete = null, deleted = false where id = ?";
        jdbcTemplate.update(query, id);
    }

    public ProductVersion findByIdWithDeleted(long id) {
        String query = "select * from dveri.product_version where id = ?";
        try {
            return jdbcTemplate.queryForObject(query, new ProductVersionMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }

    }

    public List<ProductVersion> findDeleted() {
        String query = "select * from dveri.product_version where  deleted = true order by date_delete desc";
        return jdbcTemplate.query(query, new ProductVersionMapper());
    }

    public int getMaxOrderInProduct(Long productId) {
        return jdbcTemplate.queryForObject("select coalesce(max(\"order\"), 0) from dveri.product_version where product_id=?", Integer.class, productId);
    }
}
