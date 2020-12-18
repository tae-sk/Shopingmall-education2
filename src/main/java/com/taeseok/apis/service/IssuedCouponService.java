package com.taeseok.apis.service;

import com.taeseok.apis.model.Coupon;
import com.taeseok.apis.model.IssuedCoupon;
import com.taeseok.apis.repository.CouponRepository;
import com.taeseok.apis.repository.IssuedCouponRepository;
import com.taeseok.apis.util.DateUtil;
import com.taeseok.apis.vo.IssueCouponVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Date;
import java.util.Optional;

@Controller
public class IssuedCouponService {
    private final IssuedCouponRepository issuedCouponRepository;
    private final CouponRepository couponRepository;

    @Autowired
    public IssuedCouponService(IssuedCouponRepository issuedCouponRepository, CouponRepository couponRepository){
        this.issuedCouponRepository = issuedCouponRepository;
        this.couponRepository = couponRepository;
    }

    public int issueCoupon(IssuedCouponRepository issuedCouponRepository) throws Exception{
        Optional<Coupon> SearchedCoupon = this.couponRepository.findById(IssueCouponVO.getCouponId());
        Coupon coupon = SearchedCoupon.orElseThrow(() -> new Exception("해당 쿠폰을 찾지 못하였습니다."));

        Date expireDate = null;
        Date addedDate = DateUtil.addDugs(new Date(), coupon.getAvailableDays());

        int compareDate = addedDate.compareTo(coupon.getExpireAt());
        if(compareDate == 1){
            expireDate = coupon.getExpireAt();
        }
        else if(compareDate == -1){
            expireDate = addedDate;
        }
        else {
            expireDate = addedDate;
        }
        IssuedCoupon issuedCoupon = IssuedCoupon.builder()
                .couponId(issueCouponVO.getCouponId())
                .expiredAt(expireDate)
                .userId(issueCouponVO.getUserId())
                .build();

        this.issuedCouponRepository.save(issuedCoupon);
        this.issuedCouponRepository.flush();

        return issuedCoupon.getIssuedCouponId();
    }
}
