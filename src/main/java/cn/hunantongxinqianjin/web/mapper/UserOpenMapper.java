package cn.hunantongxinqianjin.web.mapper;

import cn.hunantongxinqianjin.web.entity.UserOpen;

import java.util.Date;
import java.util.List;

/**
 * Created by shixiaoqi on 2017/5/18.
 */
public interface UserOpenMapper {
    int addMobileAndUser(String phone, String userName,Long proId);

    List<UserOpen> getMobileAndUser(Date cTime);
}
