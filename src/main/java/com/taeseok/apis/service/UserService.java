package com.taeseok.apis.service;

import com.taeseok.apis.datamodels.SaleGroupByUserId;
import com.taeseok.apis.datamodels.UserGradeEnum;
import com.taeseok.apis.datamodels.UserTotalPaidPrice;
import com.taeseok.apis.model.User;
import com.taeseok.apis.repository.SaleRepository;
import com.taeseok.apis.vo.UserRegisterVO;
import com.taeseok.apis.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class UserService {
    private final UserRepository userRepository;
    private final SaleRepository saleRepository;

    @Autowired
    public UserService(UserRepository userRepository, SaleRepository saleRepository) {
        this.userRepository = userRepository;
        this.saleRepository = saleRepository;
    }

    public User find(int userId) throws Exception{
        Optional<User> searchedUser = this.userRepository.findById(userId);
        return searchedUser.orElseThrow(() -> new Exception("해당 유저를 찾지 못하였습니다"));
    }

    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    public void initializeUsers() {
        User user1 = User.builder()
                .email("example1@sample.com")
                .name("Mr. Example")
                .phone("01000000000")
                .build();

        User user2 = User.builder()
                .email("example2@sample.com")
                .name("Mrs. Sample")
                .phone("01000001234")
                .build();

        User user3 = User.builder()
                .email("example3@sample.com")
                .name("ms. Sample Data")
                .phone("01012341234")
                .build();

        this.userRepository.save(user1);
        this.userRepository.save(user2);
        this.userRepository.save(user3);
        this.userRepository.flush();
    }

    public int createUser(UserRegisterVO userRegisterVO) {
        User createUser = User.builder()
                .email(userRegisterVO.getEmail())
                .phone(userRegisterVO.getPhone())
                .name(userRegisterVO.getName())
                .build();

        this.userRepository.save(createUser);
        this.userRepository.flush();

        return createUser.getUserId();
    }

    public void deleteUser(int userId) {
        this.userRepository.deleteById(userId);
    }

    public UserGradeEnum getUserGrade(int userId) {
        SaleGroupByUserId groupData = this.saleRepository.PurchaseAmountGroupByUserId(userId);
        UserTotalPaidPrice userTotalPaidPrice = new UserTotalPaidPrice(groupData);
        return this.getUserGradeByTotalPaidPrice(userTotalPaidPrice.getTotalPaidPrice());

    }
        public UserGradeEnum getUserGradeByTotalPaidPrice(int totalPaidPrice){

        if (totalPaidPrice < 100000) {
            return UserGradeEnum.FirstGrade;
        }
        else if (totalPaidPrice < 1000000) {
            return UserGradeEnum.SecondGrade;
        }
        else if (totalPaidPrice < 3000000) {
            return UserGradeEnum.ThirdGrade;
        }
        else if (totalPaidPrice < 10000000) {
            return UserGradeEnum.FourthGrade;
        }
        else {
            return UserGradeEnum.TopTier;
        }
    }
}