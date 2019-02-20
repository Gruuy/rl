package com.Gruuy.dao;

import com.Gruuy.entity.User;

import java.util.List;

public interface UserDao {
    public List<User> getAll();
    public int chechLogin(User user);
}
