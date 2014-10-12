
package ru.grigory.site.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.grigory.site.domain.Category;
import ru.grigory.site.domain.Product;
import ru.grigory.site.dto.ProductDto;
import ru.grigory.site.dto.ProductListDto;
import ru.grigory.site.service.CategoryService;
import ru.grigory.site.service.ProductService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: gr
 * Date: 29.07.14
 * Time: 22:33
 */

@Controller
public class CategoryController {
    private final static Logger logger = LoggerFactory.getLogger(CategoryController.class);

    private final static int RECORDS_PER_PAGE=6;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "index.html", method = RequestMethod.GET)
    public ModelAndView getHome(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("categories", categoryService.findAll());
        params.put("title",  "Двери города - список товаров");

        return new ModelAndView("index", params);
    }

    @RequestMapping(value = "admin/index.html", method = {RequestMethod.GET, RequestMethod.POST} )
    @Secured(value = "ROLE_ADMIN")
    public ModelAndView getAdminHome(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("categories", categoryService.findAll());
        params.put("title",  "Редактирование списка товаров");

        return new ModelAndView("admin/categoryList", params);
    }


    @RequestMapping(value = "admin/categoryList.html", method = RequestMethod.GET)
    @Secured(value = "ROLE_ADMIN")
    public ModelAndView getCategoryList(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("categories", categoryService.findAll());
        params.put("title",  "Редактирование списка категорий");

        return new ModelAndView("admin/categoryList", params);
    }

    @RequestMapping(value = "admin/categoryList.html", method = RequestMethod.POST)
    @Secured(value = "ROLE_ADMIN")
    public ModelAndView saveCategory(@RequestParam(value = "id", required = false) Long id,
                                     @RequestParam(value = "name", required = false) String name,
                                     @RequestParam(value = "description", required = false) String description,
                                     @RequestParam(value = "group", required = false) Integer group,
                                     @RequestParam(value = "order", required = false) Integer order){

        Category cat = categoryService.findById(id);
        if(cat == null){
            cat = new Category();
        }

        cat.setName(name);
        cat.setDescription(description);
        if(order != null){
            cat.setOrder(order);
        }
        if(group!= null){
            cat.setGroup(group);
        }

        if(cat.getId() == null){
            categoryService.addCategory(cat);
        }else{
            categoryService.updateCategory(cat);
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("categories", categoryService.findAll());
        params.put("message", "Успешно осхранено");
        params.put("title",  "Редактирование списка категорий");

        return new ModelAndView("admin/categoryList", params);
    }

    @RequestMapping(value = "admin/categoryEdit.html", method = RequestMethod.GET)
    @Secured(value = "ROLE_ADMIN")
    public ModelAndView editCategory(@RequestParam(value = "id") Long categoryId){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("category",  categoryService.findById(categoryId));
        params.put("title",  "Редактирование категории");
        return new ModelAndView("admin/categoryEdit", params);
    }

    @RequestMapping(value = "admin/categoryAdd.html", method = RequestMethod.GET)
    @Secured(value = "ROLE_ADMIN")
    public ModelAndView addCategory(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("title",  "Добавление категории");
        return new ModelAndView("admin/categoryAdd", params);
    }


    @RequestMapping(value="/category",method = RequestMethod.GET)
	public @ResponseBody
    ProductListDto getCategoryJSON(@RequestParam(value = "id") long id,
                                   @RequestParam(value = "page", required = false) Integer page) {

        if(page == null || page <= 0){
            page = 1;
        }
        //todo: ошибки должны быть оработаны и возвращены в теге errorText
        Category category = categoryService.findById(id);
        ProductListDto result = new ProductListDto();
        List<Product> products = productService.getPageByCategoryID(id, (page-1) * RECORDS_PER_PAGE, page * RECORDS_PER_PAGE );
        ProductDto[] productsDto = new ProductDto[products.size()];
        int i = 0;
        for(Product product : products){
            ProductDto dto = new ProductDto();
            dto.setDescription(product.getDescription());
            dto.setId(product.getId());
            dto.setName(product.getName());
            dto.setPrice(product.getPrice());
            productsDto[i] = dto;
            i++;
        }
        result.setCount(productService.countByCategory(id));
        result.setCategory(category.getName());
        result.setCurrentPage(page);
        result.setDescription(category.getDescription());
        result.setProduct(productsDto);
        return result;
	}
}