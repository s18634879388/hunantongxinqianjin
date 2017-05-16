package cn.hunantongxinqianjin.web.service;

import cn.hunantongxinqianjin.web.entity.UserInfo;
import cn.hunantongxinqianjin.web.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by shixiaoqi on 2017/5/15.
 */
@Service
public class UserInfoService {

    @Autowired
    UserInfoMapper userInfoMapper;

    /**
     * 根据用户名和密码查询用户是否存在
     * @return
     */
    public UserInfo getUserByPasswordAndName(String userName, String password) {
        return userInfoMapper.getUserByPasswordAndName(userName,password);
    }
}
