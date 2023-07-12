package com.cssl.controller;

import com.cssl.pojo.User;
import com.cssl.service.UserService;
import com.cssl.status.Status;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class UserController {
    @Autowired
    private UserService US;

    @RequestMapping(value = "/findAllUser.do", produces = "application/json")
    public List<User> findAllUser() {
        return US.findAllUser();
    }

    @RequestMapping(value = "/fuzzySearchUser.do", produces = "application/json")
    public List<User> fuzzySearchUser(@Param(value = "mark_no") String mark_no) {
        List<User> user;
        user = US.fuzzySearchUser(mark_no);
        return user;
    }

    @RequestMapping(value = "/addNewUser.do", produces = "application/json")
    @ResponseBody
    public Status addNewUser(String user_name, String phone) {
        Date date = new Date();
        User user = new User();
        user.setUser_name(user_name);
        user.setStatus(1);
        user.setPhone(phone);
        user.setOpen_date(date);
        user.setMark_no(getMark_no());
        System.out.println(user);
        Integer i = US.addNewUser(user);
        Status status = new Status();
        if ( i > 0){
            status.setStatus("OK");
            status.setMessage("新增成功");
        }else {
            status.setStatus("ERROR");
            status.setMessage("新增失败");
        }
        return status;
    }

    public static String getMark_no() {
        long timestamp = System.currentTimeMillis();
        Random random = new Random();
        int randomNumber = random.nextInt(90000000) + 10000000; // 生成8位随机数
        String code = String.valueOf(timestamp) + String.valueOf(randomNumber);
        return code;
    }

    //查询所有未中签用户
    public List<User> findNoMarkUser(){
        return US.findNoMarkUser();
    }

    /**
     *
     * @param num
     * @return
     */
    @RequestMapping(value = "/lotteryUser.do")
    @ResponseBody
    public Status lotteryUser(Integer num){
        //循环获取的用户id
        long id;
        //循环内的修改结果
        int res = 0;
        //返回状态对象
        Status status = new Status();
        //存取赢家MarkNo
        List<String> winnerMarkNo = new ArrayList<>();
        List<String> winnerNames = new ArrayList<>();
        //获取所有未中签用户合集
        List<User> list = findNoMarkUser();
        //产生随机数
        Random r = new Random();
        //此次中签的赢家合集
        List<User> WinnerList = new ArrayList<>();
        //判断 如果抽取用户数大于剩余未中签用户数 终止抽签
        if(list.size() < num){
            status.setStatus("WARNING!");
            status.setMessage("抽取用户数大于剩余未中签用户数!");
            return status;
        }
        //抽签逻辑代码
        for (int i = 0; i < num; i++) {
            //从所有未中签用户合集中产生随机数 index:中签用户下标
            int index = r.nextInt(list.size());
            //获取中签用户对象
            User winner = list.get(index);
            //移除本次赢家
            list.remove(index);
            //保存到赢家合集中
            WinnerList.add(winner);
            System.out.println(WinnerList);
            //保存赢家中签号,发送前台进行数据展示
            winnerMarkNo.add(winner.getMark_no());
            status.setWinners(winnerMarkNo);
            winnerNames.add(winner.getUser_name());
            status.setWinnerNames(winnerNames);
        }
        //修改数据库状态
        for (User user : WinnerList) {
            //获取赢家id
            id = user.getId();
            //修改赢家持证状态
            res = US.updateUserStatus(id);
            //
            if (res == 0) {
                status.setStatus("ERROR");
                status.setMessage("修改失败!");
                break;
            }
        }
        status.setStatus("SUCCESS");
        status.setMessage("修改成功!");
        return status;
    }




}
