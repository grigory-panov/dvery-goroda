package ru.grigory.site.dto;

import java.io.Serializable;
import java.lang.String;

/**
 * Created with IntelliJ IDEA.
 * User: gr
 * Date: 11.10.14
 * Time: 14:28
 * To change this template use File | Settings | File Templates.
 */
public class DeleteResult implements Serializable{
    private String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
