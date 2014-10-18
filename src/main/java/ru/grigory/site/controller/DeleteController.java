package ru.grigory.site.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.grigory.site.domain.*;
import ru.grigory.site.dto.DeleteResult;
import ru.grigory.site.dto.ProductDto;
import ru.grigory.site.service.*;

/**
 * Created with IntelliJ IDEA.
 * User: gr
 * Date: 11.10.14
 * Time: 14:20
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class DeleteController {

    @Autowired
    private InfoService infoService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductVersionService productVersionService;

    @Autowired
    private FeedbackService feedbackService;

    @RequestMapping(value="/delete/category/{id}",method = RequestMethod.DELETE)
    @Secured(value = "ROLE_ADMIN")
    public @ResponseBody
    DeleteResult deleteCategoryJSON(@PathVariable(value = "id") long id) {

        Category category = categoryService.findByIdWithDeleted(id);
        DeleteResult result = new DeleteResult();
        result.setError("OK");

        if(category == null){
            result.setError("Не найдена категрия для удаления");
            return result;
        }
        if(category.isDeleted()){
            result.setError("категория уже была удалена");
            return result;
        }
        try{
            categoryService.deleteCategory(category.getId());
        }catch (Exception ex){
            result.setError("Произошла ошибка при удалении");
            return result;
        }
        return result;
    }


    @RequestMapping(value="/restore/category/{id}",method = RequestMethod.DELETE)
    @Secured(value = "ROLE_ADMIN")
    public @ResponseBody
    DeleteResult restoreCategoryJSON(@PathVariable(value = "id") long id) {

        Category category = categoryService.findByIdWithDeleted(id);
        DeleteResult result = new DeleteResult();
        result.setError("OK");

        if(category == null){
            result.setError("Не найдена категрия для восстановления");
            return result;
        }
        if(!category.isDeleted()){
            return result;
        }
        try{
            categoryService.restoreCategory(category.getId());
        }catch (Exception ex){
            result.setError("Произошла ошибка при восстановлении");
            return result;
        }
        return result;
    }

    @RequestMapping(value="/delete/product/{id}",method = RequestMethod.DELETE)
    @Secured(value = "ROLE_ADMIN")
    public @ResponseBody
    DeleteResult deleteProductJSON(@PathVariable(value = "id") long id) {

        Product product = productService.findByIdWithDeleted(id);
        DeleteResult result = new DeleteResult();
        result.setError("OK");

        if(product == null){
            result.setError("Не найден продукт для удаления");
            return result;
        }
        if(product.isDeleted()){
            result.setError("Продукт уже был удален");
            return result;
        }

        try{
            productService.deleteProduct(product.getId());
        }catch (Exception ex){
            result.setError("Произошла ошибка при удалении");
            return result;
        }
        return result;
    }

    @RequestMapping(value="/restore/product/{id}",method = RequestMethod.DELETE)
    @Secured(value = "ROLE_ADMIN")
    public @ResponseBody
    DeleteResult restoreProductJSON(@PathVariable(value = "id") long id) {

        Product product = productService.findByIdWithDeleted(id);
        DeleteResult result = new DeleteResult();
        result.setError("OK");

        if(product == null){
            result.setError("Не найден продукт для восстановления");
            return result;
        }
        if(!product.isDeleted()){
            return result;
        }

        try{
            productService.restoreProduct(product.getId());
        }catch (Exception ex){
            result.setError("Произошла ошибка при восстановлении");
            return result;
        }
        return result;
    }

    @RequestMapping(value="/delete/productVersion/{id}",method = RequestMethod.DELETE)
    @Secured(value = "ROLE_ADMIN")
    public @ResponseBody
    DeleteResult deleteProductVersionJSON(@PathVariable(value = "id") long id) {

        ProductVersion version = productVersionService.findByIdWithDeleted(id);
        DeleteResult result = new DeleteResult();
        result.setError("OK");

        if(version == null){
            result.setError("Не найдена вверсия продукта для удаления");
            return result;
        }
        if(version.isDeleted()){
            result.setError("Версия продукта уже была удалена");
            return result;
        }
        try{
            productVersionService.deleteProductVersion(version.getId());
        }catch (Exception ex){
            result.setError("Произошла ошибка при удалении");
            return result;
        }
        return result;
    }

    @RequestMapping(value="/restore/productVersion/{id}",method = RequestMethod.DELETE)
    @Secured(value = "ROLE_ADMIN")
    public @ResponseBody
    DeleteResult restoreProductVersionJSON(@PathVariable(value = "id") long id) {

        ProductVersion version = productVersionService.findByIdWithDeleted(id);
        DeleteResult result = new DeleteResult();
        result.setError("OK");

        if(version == null){
            result.setError("Не найдена вверсия продукта для восстановления");
            return result;
        }
        if(!version.isDeleted()){
            return result;
        }
        try{
            productVersionService.restoreProductVersion(version.getId());
        }catch (Exception ex){
            result.setError("Произошла ошибка при восстановлении");
            return result;
        }
        return result;
    }

    @RequestMapping(value="/delete/info/{id}",method = RequestMethod.DELETE)
    @Secured(value = "ROLE_ADMIN")
    public @ResponseBody
    DeleteResult deleteInfoJSON(@PathVariable(value = "id") long id) {

        Info info = infoService.findByIdWithDeleted(id);
        DeleteResult result = new DeleteResult();
        result.setError("OK");

        if(info == null){
            result.setError("Не найдена статья для удаления");
            return result;
        }
        if(info.isDeleted()){
            result.setError("Статья уже была удалена");
            return result;
        }
        try{
            infoService.deleteInfo(info);
        }catch (Exception ex){
            result.setError("Произошла ошибка при удалении");
            return result;
        }
        return result;
    }

    @RequestMapping(value="/restore/info/{id}",method = RequestMethod.DELETE)
    @Secured(value = "ROLE_ADMIN")
    public @ResponseBody
    DeleteResult restoreInfoJSON(@PathVariable(value = "id") long id) {

        Info info = infoService.findByIdWithDeleted(id);
        DeleteResult result = new DeleteResult();
        result.setError("OK");

        if(info == null){
            result.setError("Не найдена статья для восстановления");
            return result;
        }
        if(!info.isDeleted()){
            return result;
        }
        try{
            infoService.restoreInfo(info);
        }catch (Exception ex){
            result.setError("Произошла ошибка при восстановлении");
            return result;
        }
        return result;
    }

    @RequestMapping(value="/delete/feedback/{id}",method = RequestMethod.DELETE)
    @Secured(value = "ROLE_ADMIN")
    public @ResponseBody
    DeleteResult deleteFeebackJSON(@PathVariable(value = "id") long id) {

        Feedback feedback = feedbackService.findById(id);
        DeleteResult result = new DeleteResult();
        result.setError("OK");

        if(feedback == null){
            result.setError("Не найден лтзыв для удаления");
            return result;
        }
        if(feedback.isDeleted()){
            result.setError("Отзыв уже был удален");
            return result;
        }
        try{
            feedbackService.deleteFeedback(feedback);
        }catch (Exception ex){
            result.setError("Произошла ошибка при удалении");
            return result;
        }
        return result;
    }

    @RequestMapping(value="/approve/feedback/{id}",method = RequestMethod.POST)
    @Secured(value = "ROLE_ADMIN")
    public @ResponseBody
    DeleteResult approveeFeebackJSON(@PathVariable(value = "id") long id) {

        Feedback feedback = feedbackService.findById(id);
        DeleteResult result = new DeleteResult();
        result.setError("OK");

        if(feedback == null){
            result.setError("Не найден отзыв");
            return result;
        }
        if(feedback.isDeleted()){
            result.setError("Отзыв уже был удален");
            return result;
        }
        if(feedback.isApproved()){
            result.setError("Отзыв уже был опубликован");
            return result;
        }
        try{
            feedbackService.approveFeedback(feedback);
        }catch (Exception ex){
            result.setError("Произошла ошибка при одобрении отзыва");
            return result;
        }
        return result;
    }


}
