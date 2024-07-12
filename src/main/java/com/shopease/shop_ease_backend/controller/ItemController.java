package com.shopease.shop_ease_backend.controller;

import com.shopease.shop_ease_backend.dto.ItemDTO;
import com.shopease.shop_ease_backend.service.ItemService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/")
    public ResponseEntity<List<ItemDTO>> GetAllItems(){

        List<ItemDTO> itemList = itemService.getItems();
        return ResponseEntity.ok(itemList);
    }


}
