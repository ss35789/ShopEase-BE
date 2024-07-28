package com.shopease.shop_ease_backend.dto.modelDTO;

import com.shopease.shop_ease_backend.domain.Category;
import jakarta.persistence.*;
        import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ItemDTO {

    private Long itemKey;

    private List<Category> categories;

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotNull(message = "Price cannot be null")
    private Integer price;

    private Integer salePrice;

    private String content;

    private LocalDateTime uploadDate = LocalDateTime.now();

    private String img;

    public ItemDTO() {}

    public ItemDTO(Long itemKey, String name, Integer price, Integer salePrice, String content, LocalDateTime uploadDate, String img) {
        this.itemKey = itemKey;
        this.name = name;
        this.price = price;
        this.salePrice = salePrice;
        this.content = content;
        this.uploadDate = uploadDate;
        this.img = img;
    }

}