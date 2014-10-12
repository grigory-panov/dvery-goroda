package ru.grigory.site.dto;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: gr
 * Date: 30.08.14
 * Time: 23:46
 * To change this template use File | Settings | File Templates.
 */
public class FeedbackListDto implements Serializable {
    private int count;
    private int currentPage;
    private FeedbackDto[] feedbacks;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public FeedbackDto[] getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(FeedbackDto[] feedbacks) {
        this.feedbacks = feedbacks;
    }
}
