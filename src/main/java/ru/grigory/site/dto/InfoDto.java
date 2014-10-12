package ru.grigory.site.dto;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: gr
 * Date: 30.08.14
 * Time: 19:57
 * To change this template use File | Settings | File Templates.
 */
public class InfoDto implements Serializable {

    private Long nextId;
    private Long prevId;

    private String header;
    private String body;

    public Long getNextId() {
        return nextId;
    }

    public void setNextId(Long nextId) {
        this.nextId = nextId;
    }

    public Long getPrevId() {
        return prevId;
    }

    public void setPrevId(Long prevId) {
        this.prevId = prevId;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
