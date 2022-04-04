package com.endava.Supermarket.repository;

import com.endava.Supermarket.model.Item;
import com.endava.Supermarket.model.Supermarket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SupermarketRepository extends JpaRepository<Supermarket, String> {

    Optional<Supermarket> findById(String id);

    Optional<Supermarket> findByName(String name);

}
