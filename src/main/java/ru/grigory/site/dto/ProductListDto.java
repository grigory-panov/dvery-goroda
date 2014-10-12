package ru.grigory.site.dto;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: gr
 * Date: 13.08.14
 * Time: 21:08
 * To change this template use File | Settings | File Templates.
 */
public class ProductListDto implements Serializable {
    private String category;
    private String description;
    private int count;
    private int currentPage;
    private ProductDto[] product;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

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

    public ProductDto[] getProduct() {
        return product;
    }

    public void setProduct(ProductDto[] product) {
        this.product = product;
    }
}
