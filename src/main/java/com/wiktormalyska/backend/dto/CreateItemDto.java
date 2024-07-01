package com.wiktormalyska.backend.dto;

import com.wiktormalyska.backend.model.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateItemDto {
    private String name;
    private int price;
    private String description;

    public Item toItem() {
        new Item();
        Item item = Item.builder()
                .name(this.name)
                .price(this.price)
                .description(this.description)
                .build();
        return item;
    }
}
