package ru.grigory.site.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.grigory.site.dao.CategoryDao;
import ru.grigory.site.domain.Category;
import ru.grigory.site.exception.BusinessException;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: gr
 * Date: 12.08.14
 * Time: 23:18
 * To change this template use File | Settings | File Templates.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class CategoryService {

    @Autowired
    private CategoryDao categoryDao;


    public List<Category> findAll(){
        return categoryDao.findAll();
    }

    @Transactional(readOnly = false)
    public void addCategory(Category category){
        if(category.getId() != null){
            throw new BusinessException("category id is not null in add function");
        }
        categoryDao.add(category);
    }

    @Transactional(readOnly = false)
    public void updateCategory(Category category){
        if(category.getId() == null){
            throw new BusinessException("category is is null in update function!");
        }
        categoryDao.update(category);
    }


    public Category findById(long categoryId) {
        return categoryDao.findById(categoryId);
    }

    public Category findByIdWithDeleted(long id) {
        return categoryDao.findByIdWithDeleted(id);
    }

    @Transactional(readOnly = false)
    public void deleteCategory(long id) {
        categoryDao.delete(id);
    }

    @Transactional(readOnly = false)
    public void restoreCategory(long id) {
        categoryDao.restore(id);
    }

    public List<Category> findDeleted() {
        return categoryDao.findDeleted();

    }
}
