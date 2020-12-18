package com.taeseok.apis.repository;

import com.taeseok.apis.model.IssuedCoupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssuedCouponRepository extends JpaRepository<IssuedCoupon, Integer> {
}
