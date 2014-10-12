package ru.grigory.site.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: gr
 * Date: 13.08.14
 * Time: 21:10
 * To change this template use File | Settings | File Templates.
 */
public class ProductDto implements Serializable {
    private long id;
    private String name;
    private String description;
    private BigDecimal price;
    private ProductVersionDto[] versions;

    public ProductVersionDto[] getVersions() {

        return versions;
    }

    public void setVersions(ProductVersionDto[] versions) {
        this.versions = versions;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
