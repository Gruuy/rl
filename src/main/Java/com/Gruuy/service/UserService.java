package com.Gruuy.service;

import com.Gruuy.entity.User;

import java.util.List;

public interface UserService {
    public List<User> getAllUser();
    public boolean checkLogin(String username,String password);
}
