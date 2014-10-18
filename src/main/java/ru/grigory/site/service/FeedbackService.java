package ru.grigory.site.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.grigory.site.dao.FeedbackDao;
import ru.grigory.site.domain.Feedback;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: gr
 * Date: 16.08.14
 * Time: 17:37
 * To change this template use File | Settings | File Templates.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class FeedbackService {

    @Autowired
    private FeedbackDao feedbackDao;

    @Transactional(readOnly = false)
    public void addFeedback(Feedback fb){
        feedbackDao.addFeedback(fb);
    }

    @Transactional(readOnly = false)
    public void deleteFeedback(Feedback feedback){
        feedbackDao.delete(feedback.getId());
    }

    @Transactional(readOnly = false)
    public void approveFeedback(Feedback feedback){
        feedbackDao.approve(feedback.getId());
    }


    public int countApproved(){
         return feedbackDao.countApproved();
    }

    public List<Feedback> getPageApproved(int from, int to){
        return feedbackDao.getPageApproved(from, to);
    }


    public int count(){
        return feedbackDao.count();
    }

    public List<Feedback> getPage(int from, int to){
        return feedbackDao.getPage(from, to);
    }

    public Feedback findById(long id) {
        return feedbackDao.findByIdWithDeleted(id);
    }
}
