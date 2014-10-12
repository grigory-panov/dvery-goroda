package ru.grigory.site.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.grigory.site.dao.ProductVersionDao;
import ru.grigory.site.domain.ProductVersion;
import ru.grigory.site.exception.BusinessException;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: gr
 * Date: 16.08.14
 * Time: 17:36
 * To change this template use File | Settings | File Templates.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class ProductVersionService {

    @Autowired
    private ProductVersionDao productVersionDao;

    public List<ProductVersion> findByProduct(long id) {
        return productVersionDao.findByProduct(id);
    }

    public ProductVersion findById(Long id) {
        return productVersionDao.findById(id);
    }

    public ProductVersion findFirstVersion(Long productId) {
        return productVersionDao.findFirstVersion(productId);
    }

    @Transactional(readOnly = false)
    public void addProductVersion(ProductVersion productVersion) {
        if(productVersion.getId() != null){
            throw new BusinessException("non-empty Id is not allowed in insert");
        }
        productVersionDao.add(productVersion);
    }

    @Transactional(readOnly = false)
    public void updateProductVersion(ProductVersion productVersion) {
        if(productVersion.getId() == null){
            throw new BusinessException("empty Id is not allowed in update");
        }
        productVersionDao.update(productVersion);
    }

    @Transactional(readOnly = false)
    public void deleteProductVersion(long id){
        productVersionDao.delete(id);
    }


    public ProductVersion findByIdWithDeleted(long id) {
        return productVersionDao.findByIdWithDeleted(id);
    }

    @Transactional(readOnly = false)
    public void restoreProductVersion(Long id) {
        productVersionDao.restore(id);
    }

    public List<ProductVersion> findDeleted() {
        return productVersionDao.findDeleted();
    }
}
