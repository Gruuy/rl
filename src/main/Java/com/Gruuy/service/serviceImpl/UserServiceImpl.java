package com.Gruuy.service.serviceImpl;

import com.Gruuy.dao.AdminDao;
import com.Gruuy.dao.UserDao;
import com.Gruuy.entity.Admin;
import com.Gruuy.entity.User;
import com.Gruuy.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;
    @Resource
    private AdminDao adminDao;
    @Override
    public List<User> getAllUser() {
        return userDao.getAll();
    }

    @Override
    @Transactional
    public boolean checkLogin(String username, String password) {
        Admin admin=new Admin();
        admin.setUsername(username);
        admin.setPassword(password);
        if(adminDao.checkLogin(admin)>0)
            return true;
        return false;
    }
}
