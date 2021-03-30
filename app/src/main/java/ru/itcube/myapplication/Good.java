package ru.itcube.myapplication;

import java.io.Serializable;
import java.math.BigDecimal;

public class Good implements Serializable {
    private String title;
    private BigDecimal price;
    private String image;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
