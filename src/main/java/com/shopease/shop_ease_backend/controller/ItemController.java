package com.shopease.shop_ease_backend.controller;

import com.shopease.shop_ease_backend.dto.modelDTO.ItemDTO;
import com.shopease.shop_ease_backend.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping
    public ResponseEntity<List<ItemDTO>> getAllItems() {
        List<ItemDTO> items = itemService.getItems();
        return ResponseEntity.ok(items);
    }

    @PostMapping
    public ResponseEntity<ItemDTO> addItem(@RequestBody ItemDTO itemDTO) {
        ItemDTO createdItem = itemService.addItem(itemDTO);
        return ResponseEntity.ok(createdItem);
    }

    @DeleteMapping("/{itemKey}")
    public ResponseEntity<ItemDTO> delItem(@PathVariable Long itemKey){
        ItemDTO deleteItem = itemService.delItem(itemKey);
        return ResponseEntity.ok(deleteItem);
    }
}