package com.taeseok.apis.repository;

import com.taeseok.apis.datamodels.SaleGroupByUserId;
import com.taeseok.apis.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Integer> {
    public List<Sale> findByUserId(int userId);

    @Query(value =
            "SELECT USER_ID as userId, SUM(PAID_PRICE) as totalPaidPrice "+
            "FROM SALE WHERE user_id = ?1 GROUP BY USER_ID",
            nativeQuery = true)
    public SaleGroupByUserId PurchaseAmountGroupByUserId(int userId);
}
