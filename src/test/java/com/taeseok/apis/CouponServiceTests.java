package com.taeseok.apis;

import com.taeseok.apis.model.Coupon;
import com.taeseok.apis.repository.CouponRepository;
import com.taeseok.apis.service.CouponService;
import com.taeseok.apis.vo.CouponRegisterVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CouponServiceTests {
    private final CouponService couponService;
    private final CouponRepository couponRepository;

    public CouponServiceTests(){
        this.couponRepository = mock(CouponRepository.class);
        this.couponService = new CouponService()
    }

    @Autowired
    public CouponServiceTests(CouponRepository couponRepository){
        this.couponRepository = couponRepository;
        this.couponService = new CouponService(couponRepository);
    }

    @Test
    public void testCreateCouponWhenPercentageAndPriceExists(){
        //given
        CouponRegisterVO couponRegisterVO = new CouponRegisterVO(new Date(), 7,
                1,"null", 1000, 10);

        //when
        Exception thrown = assertThrows(Exception.class, () -> this.couponService.createCoupon(couponRegisterVO));

        //then
        assertEquals("할인 금액과 할인 비율이 동시에 존재할 수 없습니다", thrown.getMessage());
    }

    @Test
    public void testCreateCouponWhenNormal() throws Exception {
        //given
        CouponRegisterVO couponRegisterVO = new CouponRegisterVO(new Date(), 7, 1, "null",
                1000, 0);

        //when
        int couponId = this.couponService.createCoupon(couponRegisterVO);

        //then
        Coupon coupon = this.couponRepository.getOne(couponId);

        assertEquals(coupon.getProductID(), 1);
        assertEquals(coupon.getCategory(), "null");
        assertEquals(coupon.getDiscountPrice(), 1000);
        assertEquals(coupon.getDiscountPercentage(), 0);
        assertEquals(coupon.getAvailableDays(), 7);
        assertEquals(coupon.getExpireAt().toString(), (new Date()).toString());
    }
}
