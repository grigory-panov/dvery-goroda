package ru.grigory.site.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.grigory.site.domain.Info;
import ru.grigory.site.domain.Settings;
import ru.grigory.site.service.SettingsService;
import ru.grigory.site.web.Utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: gr
 * Date: 17.10.14
 * Time: 22:22
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class SettingsController {

    @Autowired
    private SettingsService settingsService;

    @RequestMapping(value = "admin/settingsList.html", method = RequestMethod.GET)
    public ModelAndView getSettingsList(@RequestParam(value = "message", required = false) String message){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("settings", settingsService.findAll());
        params.put("global", settingsService.findAllAsMap());
        String resolvedMesage = Utils.resolveMessage(message);
        if(resolvedMesage != null){
            params.put("message", resolvedMesage);
            params.put("messageClass",message.startsWith("error.") ? "text-danger" : "text-info");
        }
        params.put("title",  "Настройки");
        return new ModelAndView("admin/settingsList", params);
    }

    @RequestMapping(value = "admin/settingsList.html", method = RequestMethod.POST)
    public String saveSettings(@RequestParam(value = "key", required = true) String key,
                            @RequestParam(value = "value", required = true) String value,
                            @RequestParam(value = "description", required = false) String description){

        String code;
        Settings settings = settingsService.findByKey(key);
        if(settings != null){
            settings.setValue(value);
            settings.setDescription(description);
            try{
                settingsService.update(settings);
                code ="message.OK";
            }catch (Exception ex){
                code="error.database";
            }
        }else{
            code="error.settings.notfound";
        }
        return "redirect:/admin/settingsList.html?message="+ code;
    }


    @RequestMapping(value = "admin/settingsEdit.html", method = RequestMethod.GET)
    public ModelAndView editSettings(@RequestParam(value = "key", required = true) String key){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("settings", settingsService.findByKey(key));
        params.put("global", settingsService.findAllAsMap());
        params.put("title",  "Редактирование " + key);
        return new ModelAndView("admin/settingsEdit", params);
    }

}
