package com.cssl.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
@Getter
@Setter
public class User implements Serializable {
    private long id;
    //编号
    private String user_name;
    //用户名
    private String mark_no;
    //摇号
    private Integer status;
    //持证状态
    private String phone;
    //电话号码
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date open_date;
    //开户日期


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", user_name='" + user_name + '\'' +
                ", mark_no='" + mark_no + '\'' +
                ", status=" + status +
                ", phone='" + phone + '\'' +
                ", open_date=" + open_date +
                '}';
    }

    public User(long id, String user_name, String mark_no, Integer status, String phone, Date open_date) {
        this.id = id;
        this.user_name = user_name;
        this.mark_no = mark_no;
        this.status = status;
        this.phone = phone;
        this.open_date = open_date;
    }

    public User() {
    }
}
