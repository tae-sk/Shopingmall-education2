package com.taeseok.apis.service;

import com.taeseok.apis.datamodels.SaleStatusEnum;
import com.taeseok.apis.model.Product;
import com.taeseok.apis.model.Sale;
import com.taeseok.apis.repository.SaleRepository;
import com.taeseok.apis.vo.SalePurchaseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class SaleService {
    private final SaleRepository saleRepository;

    public Sale find(int saleId) throws Exception{
        Optional<Sale> searchedSale = this.saleRepository.findById(saleId);
        return searchedSale.orElseThrow(() -> new Exception("해당 상품을 찾지 못했습니다"));
    }

    @Autowired
    public SaleService(SaleRepository saleRepository){
    this.saleRepository = saleRepository;
    }

    public int createSale(SalePurchaseVO salePurchaseVO){
        Sale createdSale = Sale.builder()
                .userId(salePurchaseVO.getUserId())
                .productId(salePurchaseVO.getProductId())
                .paidPrice(salePurchaseVO.getPaidPrice())
                .listPrice(salePurchaseVO.getListPrice())
                .amount(salePurchaseVO.getAmount()).build();
        this.saleRepository.save(createdSale);
        this.saleRepository.flush();

        return createdSale.getSaleId();
    }

    public void purchase(int saleId) throws Exception{
    Optional<Sale> purchaseSale = this.saleRepository.findById(saleId);
    Sale sale = purchaseSale.orElseThrow(() -> new Exception("결제 완료로 변경하는 도중에 문제가 발생하였습니다!"));

    sale.setStatus(SaleStatusEnum.PAID);
    this.saleRepository.save(sale);
    this.saleRepository.flush();
    }

    public void refund(int saleId) throws Exception{
        Optional<Sale> targetSale = this.saleRepository.findById(saleId);
        Sale sale = targetSale.orElseThrow(() -> new Exception("결제 취소로 변경하는 도중에 문제가 발생하였습니다!"));

        sale.setStatus(SaleStatusEnum.REFUNDEO);
        this.saleRepository.save(sale);
        this.saleRepository.flush();

    }

    public void initializeSales(){
        Sale sale1 = Sale.builder()
                .userId(1)
                .productId(1)
                .listPrice(1200000)
                .paidPrice(1000000)
                .amount(1).build();

        Sale sale2 = Sale.builder()
                .userId(2)
                .productId(2)
                .listPrice(1240000 * 2)
                .paidPrice(1110000 * 2)
                .amount(2).build();

        Sale sale3 = Sale.builder()
                .userId(3)
                .productId(3)
                .listPrice(230000 * 3)
                .paidPrice(210000 * 3)
                .amount(3).build();

        this.saleRepository.save(sale1);
        this.saleRepository.save(sale2);
        this.saleRepository.save(sale3);
        this.saleRepository.flush();
    }
}
