package com.hazem.simpleWebApp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private String brand;
    private BigDecimal price;
    private String category;
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern = "dd-MM-yyy")
    private Date releaseDate;
    private boolean productAvailable;
    private int stockQuantity;

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public void setImageDate(byte[] imageDate) {
        this.imageDate = imageDate;
    }

    private String imageName;
    private String imageType;
    @Lob
    private byte[] imageDate;

    public String getImageType() {
        return imageType;
    }

    public byte[] getImageDate() {
        return imageDate;
    }

    public String getImageName() {
        return imageName;
    }
}
