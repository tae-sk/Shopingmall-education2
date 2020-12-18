package com.taeseok.apis.vo;

import com.sun.istack.Nullable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class CouponRegisterVO {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date expireAt;

    int availableDays;

    @Nullable
    int productID;

    @Nullable
    String category;

    int discountPrice = 0;

    int discountPercentage = 0;

    @Override
    public String toString() {
        return String.format(
                "CouponRegisterVO[expireAt='%s', availableDays=%d, productId=%d, category='%s' " +
                        "discountPrice=%d, discountPercentage=%d]",
                this.expireAt, this.availableDays, this.productID, this.discountPrice, this.discountPercentage
        );
    }
}
