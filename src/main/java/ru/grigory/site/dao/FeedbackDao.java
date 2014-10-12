package ru.grigory.site.dao;

import org.springframework.stereotype.Repository;
import ru.grigory.site.domain.Feedback;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: gr
 * Date: 31.08.14
 * Time: 0:05
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class FeedbackDao {

    private static Map<Long, Feedback> map = new TreeMap<Long, Feedback>();
    private static Long uid = 7L;
    static {
        {
            Long id = 1L;
            Feedback fb = new Feedback();
            fb.setId(id);
            fb.setApproved(true);
            fb.setAuthor("Вася");
            fb.setDateAdd(new Date());
            fb.setDeleted(false);
            fb.setIp("192.168.0.1");
            fb.setText("Привет, это тестовое сообщение");
            map.put(id, fb);
        }
        {
            Long id = 2L;
            Feedback fb = new Feedback();
            fb.setId(id);
            fb.setApproved(true);
            fb.setAuthor("Вася");
            fb.setDateAdd(new Date());
            fb.setDeleted(false);
            fb.setIp("192.168.0.1");
            fb.setText("Привет, это тестовое сообщение");
            map.put(id, fb);
        }
        {
            Long id = 3L;
            Feedback fb = new Feedback();
            fb.setId(id);
            fb.setApproved(true);
            fb.setAuthor("Вася");
            fb.setDateAdd(new Date());
            fb.setDeleted(false);
            fb.setIp("192.168.0.1");
            fb.setText("Привет, это тестовое сообщение");
            map.put(id, fb);
        }
        {
            Long id = 4L;
            Feedback fb = new Feedback();
            fb.setId(id);
            fb.setApproved(true);
            fb.setAuthor("Вася");
            fb.setDateAdd(new Date());
            fb.setDeleted(false);
            fb.setIp("192.168.0.1");
            fb.setText("Привет, это тестовое сообщение");
            map.put(id, fb);
        }
        {
            Long id = 5L;
            Feedback fb = new Feedback();
            fb.setId(id);
            fb.setApproved(true);
            fb.setAuthor("Вася");
            fb.setDateAdd(new Date());
            fb.setDeleted(false);
            fb.setIp("192.168.0.1");
            fb.setText("Привет, это тестовое сообщение");
            map.put(id, fb);
        }
        {
            Long id = 6L;
            Feedback fb = new Feedback();
            fb.setId(id);
            fb.setApproved(true);
            fb.setAuthor("Вася");
            fb.setDateAdd(new Date());
            fb.setDeleted(false);
            fb.setIp("192.168.0.1");
            fb.setText("Привет, это тестовое сообщение");
            map.put(id, fb);
        }

    }

    public int count() {
        return map.keySet().size();
    }

    public List<Feedback> getPage(int from, int to) {
        List<Feedback> result = new ArrayList<Feedback>();
        int i = 0;
        for(Feedback fb : map.values()){
             if(i>=from && i<to){
                  result.add(fb);
             }
            i++;
        }
        return result;
    }

    public void addFeedback(Feedback fb) {
        fb.setId(uid++);
        map.put(fb.getId(), fb);
    }
}
