package cn.hunantongxinqianjin.web.entity;

import java.io.Serializable;

/**
 * Created by shixiaoqi on 2017/5/15.
 */
public class UserInfo implements Serializable {
    private int id;
    private String userName;        //用户名
    private String password;        //密码

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
