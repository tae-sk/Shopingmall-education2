package com.taeseok.apis.repository;

import com.taeseok.apis.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface SaleRepository extends JpaRepository<Sale, Integer> {
}
