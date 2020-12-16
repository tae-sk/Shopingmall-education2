package com.taeseok.apis.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class SalePurchaseVO {
    int userId;
    int productId;
    int paidPrice;
    int listPrice;
    int amount;

    @Override
    public String toString() {
        return String.format(
                "SalePurchaseVO[userId=%d, productId=%d, paidPrice=%d, amount=%d]",
                this.userId, this.productId, this.paidPrice, this.amount
        );
    }
}
