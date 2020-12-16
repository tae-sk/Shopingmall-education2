package com.taeseok.apis.route;

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
    public UserRoute(UserService userService, SaleService saleService){
        this.userService = userService;
        this.saleService = saleService;
    }

    @GetMapping("")
    @ResponseBody
    public List<User> getUsers(){
        return this.userService.findAll();
    }

    @GetMapping("/{user_id}")
    @ResponseBody
    public User getUsers(@PathVariable(value="user_id") String userId) throws Exception{
        return this.userService.find(Integer.parseInt(userId));
    }

    @GetMapping("/initialize")
    public void initializeUser(){
        this.userService.initializeUsers();
    }

    @DeleteMapping("/{user_id}")
    public void deleteUser(@PathVariable(value="user_id")String userId){
        this.userService.deleteUser(Integer.parseInt(userId));
    }

    @PostMapping("")
    public void createUser(UserRegisterVO user){
        this.userService.createUser(user);
    }

    @GetMapping("/{user_id}/purchase_list")
    public List<Sale> userPurchaseList(@PathVariable(value = "user_id") String userId){
        return this.saleService.getSalesByUserId(Integer.parseInt(userId));
    }
}
