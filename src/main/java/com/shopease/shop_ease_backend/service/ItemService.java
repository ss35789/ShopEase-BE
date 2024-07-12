package com.shopease.shop_ease_backend.service;

import com.shopease.shop_ease_backend.domain.Item;
import com.shopease.shop_ease_backend.dto.ItemDTO;
import com.shopease.shop_ease_backend.exception.ItemNotFoundException;
import com.shopease.shop_ease_backend.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public List<ItemDTO> getItems(){

        List<Item> items = itemRepository.findAll();


        if(items.isEmpty()){
            throw new ItemNotFoundException("상품 데이터를 불러올 수 없습니다.");
        }


        return items.stream()
                .map(this::convertDTO)
                .collect(Collectors.toList());


    }


    public ItemDTO convertDTO(Item item){
        return new ItemDTO(
                item.getName(),
                item.getPrice(),
                item.getSalePrice(),
                item.getContent()
        );
    }


}
