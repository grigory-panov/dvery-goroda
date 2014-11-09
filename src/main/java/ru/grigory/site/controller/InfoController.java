package ru.grigory.site.controller;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import ru.grigory.site.dao.InfoDao;
import ru.grigory.site.domain.Category;
import ru.grigory.site.domain.Info;
import ru.grigory.site.dto.InfoDto;
import ru.grigory.site.service.CategoryService;
import ru.grigory.site.service.InfoService;
import ru.grigory.site.service.SettingsService;
import ru.grigory.site.web.Utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PropertyResourceBundle;

/**
 * Created with IntelliJ IDEA.
 * User: gr
 * Date: 30.08.14
 * Time: 20:19
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class InfoController {
    private final static Logger logger = LoggerFactory.getLogger(InfoController.class);

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private InfoService infoService;
    @Autowired
    private SettingsService settingsService;


    @RequestMapping(value = "info.html", method = RequestMethod.GET)
    public ModelAndView getCategory(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("categories", categoryService.findAll());
        params.put("global", settingsService.findAllAsMap());
        params.put("title",  "Информация");

        return new ModelAndView("info", params);
    }

    @RequestMapping(value = "admin/infoList.html", method = RequestMethod.GET)
    @Secured(value = "ROLE_ADMIN")
    public ModelAndView getInfoList(@RequestParam(value = "message", required = false) String message){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("infos", infoService.findAll());
        params.put("global", settingsService.findAllAsMap());
        String resolvedMesage = Utils.resolveMessage(message);
        if(resolvedMesage != null){
            params.put("message", resolvedMesage);
            params.put("messageClass",message.startsWith("error.") ? "text-danger" : "text-info");
        }
        params.put("title",  "Список информационных статей");

        return new ModelAndView("admin/infoList", params);

    }

    @RequestMapping(value = "admin/infoList.html", method = RequestMethod.POST)
    @Secured(value = "ROLE_ADMIN")
    public String saveInfo(@RequestParam(value = "id", required = false) Long id,
                            @RequestParam(value = "header", required = true) String header,
                            @RequestParam(value = "body", required = true) String body){

        String code;
        Info info = null;
        if(id != null){
             info = infoService.findById(id);
        }else{
            info = new Info();
        }
        if(info != null){
            info.setHeader(header);
            info.setBody(body);

            try{
                if(info.getId() == null){
                    infoService.addInfo(info);
                }else{
                    infoService.updateInfo(info);
                }
                code ="message.OK";
            }catch (Exception ex){
                code="error.database";
            }
        }else{
            code="error.info.notfound";
        }
        return "redirect:/admin/infoList.html?message="+ code;
    }


    @RequestMapping(value = "admin/infoEdit.html", method = RequestMethod.GET)
    @Secured(value = "ROLE_ADMIN")
    public ModelAndView editInfo(@RequestParam(value = "id", required = true) Long id){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("infoMessage", infoService.findById(id));
        params.put("global", settingsService.findAllAsMap());
        params.put("title",  "Редактирование информации");

        return new ModelAndView("admin/infoEdit", params);
    }

    @RequestMapping(value = "admin/infoAdd.html", method = RequestMethod.GET)
    @Secured(value = "ROLE_ADMIN")
    public ModelAndView addInfo(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("global", settingsService.findAllAsMap());
        params.put("title",  "Добавление информации");

        return new ModelAndView("admin/infoAdd", params);
    }



    @RequestMapping(value="/info",method = RequestMethod.GET)
    public @ResponseBody
    InfoDto getInfoJSON(@RequestParam(value = "id", required = false) Long id) {

        Info info = null;
        InfoDto result = new InfoDto();
        Long prevId = null;
        Long nextId = null;
        List<Info> list = infoService.findAll();
        if(list.isEmpty()){
            result.setBody("Пока нет ни одной новости");
            result.setHeader("Увы!");
            result.setNextId(null);
            result.setPrevId(null);
            return result;
        }
        if(id == null || id <= 0){
            info = list.get(0);
            nextId = list.size() > 1 ? list.get(1).getId() : null;
        }else{
            for(int i = 0 ; i<list.size(); i++){
                if(list.get(i).getId().equals(id)){
                    info = list.get(i);
                    nextId = list.size() > i+1 ? list.get(i+1).getId() : null;
                    prevId = i != 0 ? list.get(i-1).getId() : null;
                }
            }
            if(info == null){
                result.setBody("Запрошенная новость не найдена в базе");
                result.setHeader("Увы!");
                result.setNextId(null);
                result.setPrevId(null);
                return result;
            }
        }
        //todo: ошибки должны быть оработаны и возвращены в теге errorText

        result.setBody(info.getBody());
        result.setHeader(info.getHeader());
        result.setPrevId(prevId);
        result.setNextId(nextId);
        return result;
    }

}
