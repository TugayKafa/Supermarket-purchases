package com.endava.Supermarket.repository;

import com.endava.Supermarket.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, String> {

    Optional<Item> findById(String id);

    Optional<Item> findByName(String name);
}
