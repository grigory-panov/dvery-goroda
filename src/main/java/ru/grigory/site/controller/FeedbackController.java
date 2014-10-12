package ru.grigory.site.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import ru.grigory.site.domain.Feedback;
import ru.grigory.site.dto.FeedbackDto;
import ru.grigory.site.dto.FeedbackListDto;
import ru.grigory.site.service.CategoryService;
import ru.grigory.site.service.FeedbackService;

import javax.servlet.http.HttpServletRequest;
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


    @RequestMapping(value = "feedback.html", method = RequestMethod.GET)
    public ModelAndView getFeedbacks(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("categories", categoryService.findAll());
        params.put("title",  "Двери города - отзывы покупателей");

        return new ModelAndView("feedback", params);
    }


    @RequestMapping(value="/feedback",method = RequestMethod.GET)
    public @ResponseBody
    FeedbackListDto getPage(@RequestParam(value = "page", required = false) Integer page) {
        int FEEDBACK_PER_PAGE = 5;
        if(page == null || page <=0){
            page = 1;
        }
        int count = feedbackService.count();
//        int totalPages = (int)Math.ceil((double)count / count);
        FeedbackListDto list = new FeedbackListDto();
        list.setCount(count);
        list.setCurrentPage(page);
        List<Feedback> feedbacksPage = feedbackService.getPage(FEEDBACK_PER_PAGE * (page - 1), FEEDBACK_PER_PAGE * page);
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
        fb.setIp(request.getLocalAddr());
        fb.setApproved(false);
        fb.setAuthor(userName);
        feedbackService.addFeedback(fb);  //todo: implement
        result.setAuthor(userName);
        result.setText(feedbackText);
        result.setDateAdd(new Date());
        return result;
    }


}
