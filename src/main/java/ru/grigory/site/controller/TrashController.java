package ru.grigory.site.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.grigory.site.service.CategoryService;
import ru.grigory.site.service.InfoService;
import ru.grigory.site.service.ProductService;
import ru.grigory.site.service.ProductVersionService;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: gr
 * Date: 11.10.14
 * Time: 16:40
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class TrashController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductVersionService productVersionService;

    @Autowired
    private InfoService infoService;




    @RequestMapping(value = "admin/trashbin.html", method = RequestMethod.GET)
    @Secured(value = "ROLE_ADMIN")
    public ModelAndView getTrashBin(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("categoriesTrash", categoryService.findDeleted());
        params.put("productsTrash", productService.findDeleted());
        params.put("versionsTrash", productVersionService.findDeleted());
        params.put("infosTrash", infoService.findDeleted());
        params.put("title",  "Восстановление удаленного");

        return new ModelAndView("admin/trashbin", params);
    }

}
