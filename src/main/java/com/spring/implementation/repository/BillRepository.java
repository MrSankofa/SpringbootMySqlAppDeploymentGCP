package com.spring.implementation.repository;

import com.spring.implementation.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<Bill, Long> {
  // Default methods for CRUD are inherited
}
