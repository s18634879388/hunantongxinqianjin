package cn.hunantongxinqianjin.web.mapper;

import cn.hunantongxinqianjin.web.entity.Record;

/**
 * Created by shixiaoqi on 2017/5/13.
 */
public interface RecordMapper {

    int addRecord(Long id, String ip);

    Record getRecordByDateAndIp(String ip,Long id);

    int getCountByPro(Long id,String type);

    int getCountByProAndType(Long id, String mon);
}
