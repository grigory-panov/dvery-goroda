package ru.grigory.site.dto;

import java.io.Serializable;

/**
 * Created by grigory on 02.11.14.
 */
public class PartnerDto implements Serializable {
    private Long id;
    private String name;
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
