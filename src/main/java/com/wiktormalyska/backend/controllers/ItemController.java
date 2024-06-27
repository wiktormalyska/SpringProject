package com.wiktormalyska.backend.controllers;

import com.wiktormalyska.backend.dto.CreateItemDto;
import com.wiktormalyska.backend.dto.ItemDto;
import com.wiktormalyska.backend.model.Item;
import com.wiktormalyska.backend.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/items")
public class ItemController {
    @Autowired

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    private final ItemService itemService;

    @GetMapping("/all")
    public ResponseEntity<Collection<ItemDto>> getItems() {
        Collection<ItemDto> items = itemService.getItems();
        return ResponseEntity.ok(items);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addItem(@RequestBody CreateItemDto item) {
        new Item();
        Item newItem = Item.builder()
                .description(item.getDescription())
                .price(item.getPrice())
                .name(item.getName())
                .build();
        try {
            itemService.addItem(newItem);
        }catch (Exception e){
                return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok("Item added successfully");
    }

    @GetMapping("/get/{item}")
    public ResponseEntity<ItemDto> getItem(@PathVariable Long item) {
        ItemDto itemdto = itemService.getItem(item);
        if (itemdto != null) {
            return ResponseEntity.ok(itemdto);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/remove/{itemId}")
    public ResponseEntity<String> removeItem(@PathVariable Long itemId) {

        try {
            ItemDto item = itemService.getItem(itemId);
            Item itemToRemove = item.toItem();
            itemService.removeItem(itemToRemove);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok("Successfully removed item");
    }

}
