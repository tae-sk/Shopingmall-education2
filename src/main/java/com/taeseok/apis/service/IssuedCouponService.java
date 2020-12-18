package com.taeseok.apis.service;

import com.taeseok.apis.repository.IssuedCouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class IssuedCouponService {
    private final IssuedCouponRepository issuedCouponRepository;

    @Autowired
    public IssuedCouponService(IssuedCouponRepository issuedCouponRepository){
        this.issuedCouponRepository = issuedCouponRepository;
    }

    public int issueCouple(){

    }
}
