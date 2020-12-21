package com.taeseok.apis.datamodels.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserRegisterVO {
    private String email;
    private String name;
    private String phone;

    @Override
    public String toString(){
        return String.format(
                "UserRegisterVO[email='%s', name='%s', phone='%s']",
                this.email, this.name, this.phone
        );
    }
}
