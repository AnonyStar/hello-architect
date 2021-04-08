package online.icode.eshop.cache.eshopcache.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProductInfo implements Serializable {

    private Long id;
    private String name;
    private Double price;
}