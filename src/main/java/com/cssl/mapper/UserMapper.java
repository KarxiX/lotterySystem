package com.cssl.mapper;

import com.cssl.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    List<User> findAllUser();
    //查询全部用户

    List<User> fuzzySearchUser(String mark_no);
    //模糊查找中签编码

    Integer addNewUser(User user);
    //添加新用户

    List<User> findNoMarkUser();
    //查询所有未中签用户

    Integer updateUserStatus(long id);
    //修改中签用户持证状态
}
