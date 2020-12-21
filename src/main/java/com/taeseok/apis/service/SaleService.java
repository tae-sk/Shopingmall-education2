package com.taeseok.apis.service;

import com.taeseok.apis.datamodels.SaleGroupByUserId;
import com.taeseok.apis.datamodels.dto.SaleDTO;
import com.taeseok.apis.datamodels.enumModel.SaleStatusEnum;
import com.taeseok.apis.datamodels.UserTotalPaidPrice;
import com.taeseok.apis.model.*;
import com.taeseok.apis.repository.*;
import com.taeseok.apis.vo.SalePurchaseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Controller
public class SaleService {
    private final SaleRepository saleRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CouponRepository couponRepository;
    private final IssuedCouponRepository issuedCouponRepository;

    @Autowired
    public SaleService(SaleRepository saleRepository,
                       UserRepository userRepository,
                       ProductRepository productRepository,
                       CouponRepository couponRepository,
                       IssuedCouponRepository issuedCouponRepository) {
        this.saleRepository = saleRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.couponRepository = couponRepository;
        this.issuedCouponRepository = issuedCouponRepository;
    }

    public SaleDTO saleById(int saleId) throws Exception {
        Optional<Sale> searchedSale = this.saleRepository.findById(saleId);
        return new SaleDTO(searchedSale.orElseThrow(() -> new Exception("해당 상품을 찾지 못하였습니다.")));
    }

    public List sales() {
        return this.saleRepository.findAll();
    }

    private int getDiscountAmount(int originAmount, int discountAmount, int discountPercentage) {
        if (discountAmount != 0) {
            return discountAmount;
        } else if (discountPercentage != 0) {
            return (int) Math.floor(originAmount * (discountPercentage / 100));
        }
        return 0;
    }

    public int createSale(SalePurchaseVO salePurchaseVO) throws Exception{
        Optional<Product> product = this.productRepository.findById(salePurchaseVO.getProductId());
        Optional<User> user = this.userRepository.findById(salePurchaseVO.getUserId());

        Product findProduct = product.orElseThrow(() -> new Exception("해당 상품 ID가 존재하지 않습니다."));
        user.orElseThrow(() -> new Exception("해당 유저 ID가 존재하지 않습니다."));

        if (salePurchaseVO.getListPrice() != findProduct.getListPrice() * salePurchaseVO.getAmount()) {
            throw new Exception("정가가 상품정보에 등록된 가격과 다릅니다.");
        }
        if (salePurchaseVO.getPaidPrice() != findProduct.getPrice() * salePurchaseVO.getAmount()) {
            throw new Exception("실제 구매 금액이 상품정보에 등록된 가격과 다릅니다.");
        }

        int issuedCouponId = salePurchaseVO.getIssuedCouponId();
        IssuedCoupon issuedCoupon = this.issuedCouponRepository.findById(issuedCouponId)
                .orElseThrow(() -> new Exception("해당 발급된 주문이 존재하지 않습니다."));

        Coupon coupon = this.couponRepository.findById(issuedCoupon.getCouponId())
                .orElseThrow(() -> new Exception("해당 쿠폰이 존재하지 않습니다"));

        int discountAmount = this.getDiscountAmount(salePurchaseVO.getPaidPrice(),
                coupon.getDiscountPrice(),
                coupon.getDiscountPercentage());

        Sale createdSale = Sale.builder()
                .userId(salePurchaseVO.getUserId())
                .productId(salePurchaseVO.getProductId())
                .paidPrice(salePurchaseVO.getPaidPrice() - discountAmount)
                .listPrice(salePurchaseVO.getListPrice())
                .amount(salePurchaseVO.getAmount())
                .build();

        issuedCoupon.setUsed(true);

        this.saleRepository.save(createdSale);
        this.issuedCouponRepository.save(issuedCoupon);
        this.saleRepository.flush();
        this.issuedCouponRepository.flush();

        return createdSale.getSaleId();
    }

    public void purchase(int saleId) throws Exception {
        Optional<Sale> purchaseSale = this.saleRepository.findById(saleId);
        Sale sale = purchaseSale.orElseThrow(() -> new Exception(("결제 완료로 변경하는 도중에 문제가 발생하였습니다.")));

        sale.setStatus(SaleStatusEnum.PAID);
        this.saleRepository.save(sale);
        this.saleRepository.flush();
    }

    public void refund(int saleId) throws Exception {
        Optional<Sale> purchaseSale = this.saleRepository.findById(saleId);
        Sale sale = purchaseSale.orElseThrow(() -> new Exception(("결제 취소로 변경하는 도중에 문제가 발생하였습니다.")));

        sale.setStatus(SaleStatusEnum.REFUNDEO);
        this.saleRepository.save(sale);
        this.saleRepository.flush();
    }

    public void initializeSales() {
        Sale sale1 = Sale.builder()
                .userId(1)
                .productId(1)
                .listPrice(1200000)
                .paidPrice(1000000)
                .amount(1)
                .build();

        Sale sale2 = Sale.builder()
                .userId(2)
                .productId(2)
                .listPrice(1240000 * 2)
                .paidPrice(1110000 * 2)
                .amount(2)
                .build();

        Sale sale3 = Sale.builder()
                .userId(3)
                .productId(3)
                .listPrice(230000 * 3)
                .paidPrice(210000 * 3)
                .amount(3)
                .build();

        this.saleRepository.save(sale1);
        this.saleRepository.save(sale2);
        this.saleRepository.save(sale3);
        this.saleRepository.flush();
    }

    public List<SaleDTO> getSalesByUserId(int userId) {
        return this.saleRepository.findByUserId(userId).stream()
                .map(SaleDTO::new)
                .collect(Collectors.toList());
    }

    public UserTotalPaidPrice getTotalPaidPriceByUserId(int userId) {
        SaleGroupByUserId groupData = this.saleRepository.PurchaseAmountGroupByUserId(userId);
        return new UserTotalPaidPrice(groupData);
    }
}