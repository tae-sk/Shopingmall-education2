package com.taeseok.apis.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductRegisterVO {
    private String name;
    private String description;
    private int listPrice;
    private int Price;

    @Override
    public String toString(){
        return String.format(
                "UserRegisterVO[name='%s', description='%s', listPrice=%d, Price=%d]",
                this.name, this.description, this.listPrice, this.Price
        );
    }
}
