package cn.hunantongxinqianjin.web.service;

import cn.hunantongxinqianjin.web.entity.Record;
import cn.hunantongxinqianjin.web.mapper.RecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by shixiaoqi on 2017/5/13.
 */
@Service
public class RecordService {

    @Autowired
    RecordMapper recordMapper;

    public int addRecord(String id, String ip) {
        int res = 0;
//        synchronized (this){}
        Record record = recordMapper.getRecordByDateAndIp(ip,Long.parseLong(id));
        if (record==null){

            res = recordMapper.addRecord(Long.parseLong(id),ip);
        }
        return res;
    }

    public int getCountByPro(Long id) {
        return 1;
    }
}
