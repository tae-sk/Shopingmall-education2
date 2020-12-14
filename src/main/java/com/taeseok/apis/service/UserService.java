package com.taeseok.apis.service;

import com.taeseok.apis.model.User;
import com.taeseok.apis.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    public void findAll(){
        for(User user : this.userRepository.findAll()){
            System.out.println(user);
        }
    }

    public void initializeUsers(){
        User user1 = User.builder()
                .email("example1@sample.com")
                .name("Mr. Sample")
                .phone("01000001234")
                .build();

        User user2 = User.builder()
                .email("example2@sample.com")
                .name("Mrs. Sample")
                .phone("01000001234")
                .build();

        User user3 = User.builder()
                .email("example3@sample.com")
                .name("ms. Sample data")
                .phone("01000001234")
                .build();

        this.userRepository.save(user1);
        this.userRepository.save(user2);
        this.userRepository.save(user3);
        this.userRepository.flush();

    }
}
