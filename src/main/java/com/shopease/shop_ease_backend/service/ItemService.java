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

    public List<ItemDTO> getItems() {
        List<Item> items = itemRepository.findAll();
        return items.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public ItemDTO addItem(ItemDTO itemDTO) {
        Item item = convertToEntity(itemDTO);
        Item savedItem = itemRepository.save(item);
        return convertToDTO(savedItem);
    }

    public ItemDTO delItem(Long itemKey) {
        Item existitem = itemRepository.findById(itemKey)
                .orElseThrow(() -> new ItemNotFoundException("Item not found with key: " + itemKey));
        itemRepository.delete(existitem);
        return convertToDTO(existitem);
    }


    private ItemDTO convertToDTO(Item item) {
        return new ItemDTO(
                item.getItemKey(),
                item.getName(),
                item.getPrice(),
                item.getSalePrice(),
                item.getContent(),
                item.getUploadDate(),
                item.getImg()
        );
    }

    private Item convertToEntity(ItemDTO itemDTO) {
        Item item = new Item();
        item.setName(itemDTO.getName());
        item.setPrice(itemDTO.getPrice());
        item.setSalePrice(itemDTO.getSalePrice());
        item.setContent(itemDTO.getContent());
        item.setUploadDate(itemDTO.getUploadDate());
        item.setImg(itemDTO.getImg());
        return item;
    }
}
