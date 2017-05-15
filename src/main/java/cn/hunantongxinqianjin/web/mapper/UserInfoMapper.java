package cn.hunantongxinqianjin.web.mapper;

import cn.hunantongxinqianjin.web.entity.UserInfo;

/**
 * Created by shixiaoqi on 2017/5/15.
 */
public interface UserInfoMapper {
    UserInfo getUserByPasswordAndName(String userName, String password);
}
