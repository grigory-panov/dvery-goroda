package ru.grigory.site.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.grigory.site.service.CategoryService;
import ru.grigory.site.service.ProductService;
import ru.grigory.site.service.ProductVersionService;
import ru.grigory.site.service.SettingsService;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: gr
 * Date: 16.08.14
 * Time: 21:28
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class ContactsController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SettingsService settingsService;



    @RequestMapping(value = "contacts.html", method = RequestMethod.GET)
    public ModelAndView getProduct(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("categories", categoryService.findAll());
        params.put("global", settingsService.findAllAsMap());
        params.put("title",  "Контакты");

        return new ModelAndView("contacts", params);
    }
}
