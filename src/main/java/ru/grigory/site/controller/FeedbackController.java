package ru.grigory.site.controller;

import org.codehaus.jackson.map.ObjectMapper;
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
import ru.grigory.site.domain.Feedback;
import ru.grigory.site.dto.FeedbackDto;
import ru.grigory.site.dto.FeedbackListDto;
import ru.grigory.site.dto.GeoIP;
import ru.grigory.site.service.CategoryService;
import ru.grigory.site.service.FeedbackService;
import ru.grigory.site.service.SettingsService;

import javax.servlet.http.HttpServletRequest;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: gr
 * Date: 30.08.14
 * Time: 22:41
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class FeedbackController {
    private final static Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private FeedbackService feedbackService;

    @Autowired
    private SettingsService settingsService;


    @RequestMapping(value = "feedback.html", method = RequestMethod.GET)
    public ModelAndView getFeedbacks(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("categories", categoryService.findAll());
        params.put("global", settingsService.findAllAsMap());
        params.put("title",  "Отзывы покупателей");

        return new ModelAndView("feedback", params);
    }

    @RequestMapping(value = "admin/feedbackView.html", method = RequestMethod.GET)
    public ModelAndView getFeedbackView(@RequestParam(value = "id", required = true) long id){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("categories", categoryService.findAll());
        params.put("global", settingsService.findAllAsMap());
        params.put("feedback", feedbackService.findById(id));

        params.put("title",  "Отзыв");

        return new ModelAndView("admin/feedbackView", params);
    }

    @RequestMapping(value = "admin/feedbackList.html", method = RequestMethod.GET)
    @Secured(value = "ROLE_ADMIN")
    public ModelAndView getFeedbacksPage(@RequestParam(value = "page", required = false) Integer page){
        if(page == null || page <=0){
            page = 1;
        }
        int recPerPage = 50;
        int total = feedbackService.count();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("categories", categoryService.findAll());
        params.put("global", settingsService.findAllAsMap());
        params.put("feedbacks", feedbackService.getPage(recPerPage * (page - 1), recPerPage * page));
        params.put("currentPage", page);;
        params.put("total", feedbackService.count());;
        params.put("pagesl", total/recPerPage + 1);;
        params.put("title",  "Отзывы покупателей");

        return new ModelAndView("admin/feedbackList", params);
    }



    @RequestMapping(value="/feedback",method = RequestMethod.GET)
    public @ResponseBody
    FeedbackListDto getPage(@RequestParam(value = "page", required = false) Integer page) {
        int FEEDBACK_PER_PAGE = Integer.parseInt(settingsService.findByKey("feedbacks_per_page").getValue());
        if(page == null || page <=0){
            page = 1;
        }
        int count = feedbackService.countApproved();
//        int totalPages = (int)Math.ceil((double)countApproved / countApproved);
        FeedbackListDto list = new FeedbackListDto();
        list.setCount(count);
        list.setCurrentPage(page);
        List<Feedback> feedbacksPage = feedbackService.getPageApproved(FEEDBACK_PER_PAGE * (page - 1), FEEDBACK_PER_PAGE * page);
        FeedbackDto[] listDto = new FeedbackDto[feedbacksPage.size()];
        int i = 0;
        for(Feedback fb : feedbacksPage){
            FeedbackDto dto = new FeedbackDto();
            dto.setDateAdd(fb.getDateAdd());
            dto.setText(fb.getText());
            dto.setAuthor(fb.getAuthor());
            dto.setId(fb.getId());
            listDto[i] = dto;
            i++;
        }
        list.setFeedbacks(listDto);
        return list;
    }

    @RequestMapping(value="/feedback",method = RequestMethod.POST)
    public @ResponseBody
    FeedbackDto addFeedBack(@RequestParam(value = "userName") String userName,
                                @RequestParam(value = "feedbackText") String feedbackText,
                                HttpServletRequest request) {
        FeedbackDto result = new FeedbackDto();
        Feedback fb = new Feedback();
        fb.setDateAdd(new Date());
        fb.setText(feedbackText);
        fb.setIp(request.getRemoteAddr());
        fb.setApproved(false);
        fb.setAuthor(userName);
        GeoIP geoip = lookup(request.getRemoteAddr());
        fb.setCountry(geoip.getCountry_name());
        fb.setCity(geoip.getCity());
        feedbackService.addFeedback(fb);
        result.setAuthor(userName);
        result.setText(feedbackText);
        result.setDateAdd(new Date());
        return result;
    }

    private GeoIP lookup(String remoteAddr) {
        try{
            HttpURLConnection connection = (HttpURLConnection) new URL("http://api.hostip.info/get_json.php?ip=" + remoteAddr).openConnection();
            connection.setReadTimeout(20000);
            connection.setConnectTimeout(5000);
            connection.setDoInput(true);
            connection.setDoOutput(false);
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> jsonMap = mapper.readValue(connection.getInputStream(), Map.class);
            connection.disconnect();
            GeoIP geoIp = new GeoIP();
            geoIp.setCity((String) jsonMap.get("city"));
            geoIp.setCountry_code((String) jsonMap.get("country_code"));
            geoIp.setCountry_name((String) jsonMap.get("country_name"));
            return geoIp;
        }catch (Exception ex){
            return new GeoIP();
        }
    }


}
