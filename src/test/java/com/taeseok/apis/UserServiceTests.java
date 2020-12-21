package com.taeseok.apis;

import com.taeseok.apis.datamodels.UserGradeEnum;
import com.taeseok.apis.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserServiceTests {
    private final UserService userService;

    @Autowired
    public UserServiceTests(UserService userService){
        this.userService = userService;
    }

    @Test
    public void testGetUserGradeByTotalPaidPriceFirstGrade(){
    //Given
        int totalPaidAmount = 0;

    //When
        UserGradeEnum userGrade = this.userService.getUserGradeByTotalPaidPrice(totalPaidAmount);

    //Then
        assertEquals(userGrade, UserGradeEnum.FirstGrade);
    }


    @Test
    public void testGetUserGradeByTotalPaidPriceSecondGrade(){
        //Given
        int totalPaidAmount = 100_000;

        //When
        UserGradeEnum userGrade = this.userService.getUserGradeByTotalPaidPrice(totalPaidAmount);

        //Then
        assertEquals(userGrade, UserGradeEnum.FirstGrade);
    }
    @Test
    public void testGetUserGradeByTotalPaidPriceThirdGrade(){
        //Given
        int totalPaidAmount = 0;

        //When
        UserGradeEnum userGrade = this.userService.getUserGradeByTotalPaidPrice(totalPaidAmount);

        //Then
        assertEquals(userGrade, UserGradeEnum.FirstGrade);
    }
    @Test
    public void testGetUserGradeByTotalPaidPriceFourthGrade(){
        //Given
        int totalPaidAmount = 3_000_000;

        //When
        UserGradeEnum userGrade = this.userService.getUserGradeByTotalPaidPrice(totalPaidAmount);

        //Then
        assertEquals(userGrade, UserGradeEnum.FirstGrade);
    }
    @Test
    public void testGetUserGradeByTotalPaidPriceTopTire(){
        //Given
        int totalPaidAmount = 10_000_000;

        //When
        UserGradeEnum userGrade = this.userService.getUserGradeByTotalPaidPrice(totalPaidAmount);

        //Then
        assertEquals(userGrade, UserGradeEnum.FirstGrade);
    }

}
