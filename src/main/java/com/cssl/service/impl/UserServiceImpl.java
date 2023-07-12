package com.cssl.service.impl;

import com.cssl.mapper.UserMapper;
import com.cssl.pojo.User;
import com.cssl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper UM;
    @Override
    public List<User> findAllUser() {
        return UM.findAllUser();
    }

    @Override
    public List<User> fuzzySearchUser(String mark_no) {
        return UM.fuzzySearchUser(mark_no);
    }

    @Override
    public Integer addNewUser(User user) {
        return UM.addNewUser(user);
    }

    @Override
    public List<User> findNoMarkUser() {
        return UM.findNoMarkUser();
    }

    @Override
    public Integer updateUserStatus(long id) {
        return UM.updateUserStatus(id);
    }
}
