package ru.grigory.site.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.grigory.site.dao.ProductDao;
import ru.grigory.site.domain.Product;
import ru.grigory.site.exception.ProductNotFoundException;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: gr
 * Date: 12.08.14
 * Time: 23:27
 * To change this template use File | Settings | File Templates.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class ProductService {

    @Autowired
    private ProductDao productDao;


    public Product findById(long id){
        return productDao.findById(id);
    }

    public List<Product> findByCategory(long categoryId){
        return productDao.findByCategory(categoryId);
    }

    public int countByCategory(long categoryId){
        return productDao.countByCategory(categoryId);
    }

    public List<Product> getPageByCategoryID(long categoryId, int from, int to){
        return  productDao.getPageByCategoryId(categoryId, from, to);
    }

    @Transactional(readOnly =  false)
    public void addProduct(Product product){
        productDao.add(product);
    }

    @Transactional(readOnly =  false, rollbackFor = ProductNotFoundException.class)
    public void updateProduct(Product product){
         productDao.update(product);
    }

    @Transactional(readOnly = false)
    public void deleteProduct(long id){
        productDao.delete(id);
    }

    @Transactional(readOnly = false)
    public void restoreProduct(long id){
        productDao.restore(id);
    }


    public Product findByIdWithDeleted(long id) {
        return productDao.findByIdWithDeleted(id);
    }

    public List<Product> findDeleted() {
        return productDao.findDeleted();
    }

    public int getMaxOrderByCategory(long categoryId){
        return productDao.getMaxOrderInCategory(categoryId);
    }

    public List<Product> getRelatedProducts(long id) {
        return productDao.getRelatedProducts(id);
    }

    @Transactional(readOnly = false)
    public void addRelation(long productId, long relationId) {
        productDao.addRelation(productId, relationId);
    }

    @Transactional(readOnly = false)
    public void deleteRelation(long productId, long relationId) {
        productDao.deleteRelation(productId, relationId);
    }
}
