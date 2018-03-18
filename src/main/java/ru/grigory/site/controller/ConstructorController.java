package ru.grigory.site.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.grigory.site.service.CategoryService;
import ru.grigory.site.service.ProductService;
import ru.grigory.site.service.SettingsService;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: gr
 * Date: 27.03.16
 * Time: 17:08
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class ConstructorController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @Autowired
    private SettingsService settingsService;

    @Autowired
    MessageSource messageSource;


    @RequestMapping(value = "constructor.html", method = RequestMethod.GET)
    public ModelAndView getHome() {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("categories", categoryService.findAll());
        params.put("global", settingsService.findAllAsMap());
        params.put("title", settingsService.findByKey("project_name").getValue());

        return new ModelAndView("constructor", params);
    }
}
