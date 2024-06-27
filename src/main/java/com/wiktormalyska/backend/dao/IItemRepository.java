package com.wiktormalyska.backend.dao;

import com.wiktormalyska.backend.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IItemRepository extends JpaRepository<Item, Long> {
    Optional<Item> findByName(String name);
}
