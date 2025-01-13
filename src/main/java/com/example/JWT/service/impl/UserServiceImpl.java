package com.example.JWT.service.impl;

import com.example.JWT.models.User;
import com.example.JWT.repository.UserRepo;
import com.example.JWT.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public boolean existByUsername(String username) {
        User user  = userRepo.findByUsername(username).orElseThrow(null);
        if (user != null){
            return true;
        }
        return false;
    }

    @Override
    public boolean existByEmail(String email) {
        User user = userRepo.findByEmail(email).orElseThrow(null);
        if (user != null){
            return true;
        }
        return false;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //find user in repository
        return userRepo.findByUsername(username)
                //if user not exist throw exception
                .orElseThrow(() -> new UsernameNotFoundException("User with name " + username + " not exist" ));
    }
}
