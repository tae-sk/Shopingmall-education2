package com.taeseok.apis.route;

import com.taeseok.apis.datamodels.UserGradeEnum;
import com.taeseok.apis.datamodels.UserTotalPaidPrice;
import com.taeseok.apis.model.Sale;
import com.taeseok.apis.model.User;
import com.taeseok.apis.service.SaleService;
import com.taeseok.apis.service.UserService;
import com.taeseok.apis.vo.UserRegisterVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserRoute {
    private final UserService userService;
    private final SaleService saleService;

    @Autowired
    public UserRoute(UserService userService, SaleService saleService) {
        this.userService = userService;
        this.saleService = saleService;
    }

    @GetMapping
    @ResponseBody
    public List<User> getUsers() {
        return this.userService.findAll();
    }

    @GetMapping("/{user_id}")
    @ResponseBody
    public User getUser(@PathVariable(value="user_id") String userId) throws Exception{
        return this.userService.find(Integer.parseInt(userId));
    }

    @PostMapping
    public int createUser(UserRegisterVO user) {
        return this.userService.createUser(user);
    }

    @GetMapping("/initialize")
    public void initializeUsers() {
        this.userService.initializeUsers();
    }

    @DeleteMapping("/{user_id}")
    public void deleteUser(@PathVariable(value="user_id") String userId) {
        this.userService.deleteUser(Integer.parseInt(userId));
    }

    @GetMapping("/{user_id}/purchase_list")
    public List<Sale> getUserPurchaseList(@PathVariable(value="user_id") String userId) {
        return this.saleService.getSalesByUserId(Integer.parseInt(userId));
    }

    @GetMapping("/{user_id}/purchase_amount")
    public UserTotalPaidPrice getUserPurchaseAmount(@PathVariable(value="user_id") String userId) {
        return this.saleService.getTotalPaidPriceByUserId(Integer.parseInt(userId));
    }

    @GetMapping("/{user_id}/grade")
    @ResponseBody
    public UserGradeEnum getUserGrade(@PathVariable(value="user_id") String userId) {
        return this.userService.getUserGrade(Integer.parseInt(userId));
    }
}
