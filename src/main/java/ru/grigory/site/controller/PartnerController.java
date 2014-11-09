package ru.grigory.site.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ru.grigory.site.domain.Partner;
import ru.grigory.site.domain.ProductVersion;
import ru.grigory.site.dto.PartnerDto;
import ru.grigory.site.dto.PartnerListDto;
import ru.grigory.site.exception.BusinessException;
import ru.grigory.site.service.CategoryService;
import ru.grigory.site.service.PartnerService;
import ru.grigory.site.service.SettingsService;
import ru.grigory.site.web.Utils;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by grigory on 26.10.14.
 */
@Controller
public class PartnerController {

    @Autowired
    private PartnerService partnerService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SettingsService settingsService;

    @RequestMapping(value = "partners.html", method = RequestMethod.GET)
    public ModelAndView getPartners(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("categories", categoryService.findAll());
        params.put("global", settingsService.findAllAsMap());
        params.put("title",  "Партнеры");

        return new ModelAndView("partners", params);
    }
    @RequestMapping(value = "admin/partnerEdit.html", method = RequestMethod.GET)
    @Secured(value = "ROLE_ADMIN")
    public ModelAndView editPartner(@RequestParam(value = "id") long id) {
        Map<String, Object> params = new HashMap<String, Object>();
        Partner partner = partnerService.findById(id);
        if (partner == null) {
            throw new BusinessException("Partner not found!");
        }
        params.put("global", settingsService.findAllAsMap());
        params.put("partner", partner);
        params.put("title", "Редактирование партнера");

        return new ModelAndView("admin/partnerEdit", params);
    }

    @RequestMapping(value = "admin/partnerAdd.html", method = RequestMethod.GET)
    @Secured(value = "ROLE_ADMIN")
    public ModelAndView addProductVersion() {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("global", settingsService.findAllAsMap());
        params.put("title", "Добавление партнера");

        return new ModelAndView("admin/partnerAdd", params);
    }


    @RequestMapping(value = "admin/partnersList.html", method = RequestMethod.GET)
    @Secured(value = "ROLE_ADMIN")
    public ModelAndView getInfoList(@RequestParam(value = "message", required = false) String message){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("partners", partnerService.findAll());
        params.put("global", settingsService.findAllAsMap());
        String resolvedMesage = Utils.resolveMessage(message);
        if(resolvedMesage != null){
            params.put("message", resolvedMesage);
            params.put("messageClass",message.startsWith("error.") ? "text-danger" : "text-info");
        }
        params.put("title",  "Список партнеров");

        return new ModelAndView("admin/partnersList", params);

    }

    @RequestMapping(value = "admin/partnersList.html", method = RequestMethod.POST)
    @Secured(value = "ROLE_ADMIN")
    public String saveProductVersion(@RequestParam(value = "id", required = false) Long id,
                                     @RequestParam(value = "name", required = true) String name,
                                     @RequestParam(value = "url", required = true) String url,
                                     @RequestParam(value = "banner", required = false) MultipartFile banner) {

        Partner partner;
        String code;
        if (id != null) {
            partner = partnerService.findById(id);
        } else {
            partner = new Partner();
        }
        if (partner != null) {
            partner.setName(name);
            partner.setUrl(url);
            try {
                if (!banner.isEmpty()) {
                    partner.setBanner(banner.getBytes());
                }

                if (partner.getId() == null) {
                    partnerService.addPartner(partner);
                } else {
                    partnerService.updatePartner(partner);
                }
                code = "message.OK";
            }catch (IOException ex){
                code = "error.partner.image";
            } catch (Exception ex) {
                code = "error.database";
            }

        } else {
            code = "error.partner.notfound";
        }

        return "redirect:/admin/partnersList.html?&message=" + code;
    }



    @RequestMapping(value = "/partners", method = RequestMethod.GET)
    public
    @ResponseBody
    PartnerListDto getProductVersionsJSON() {

        //todo: ошибки должны быть оработаны и возвращены в теге errorText
        PartnerListDto result = new PartnerListDto();
        List<Partner> partners = partnerService.findAll();
        PartnerDto[] partnersDto = new PartnerDto[partners.size()];
        int i = 0;
        for (Partner v : partners) {
            PartnerDto dto = new PartnerDto();
            dto.setId(v.getId());
            dto.setName(v.getName());
            dto.setUrl(v.getUrl());
            partnersDto[i] = dto;
            i++;
        }
        result.setPartners(partnersDto);
        return result;
    }



}
