package com.endava.Supermarket.repository;

import com.endava.Supermarket.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase, String> {

}
