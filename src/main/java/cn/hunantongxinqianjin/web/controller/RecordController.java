package cn.hunantongxinqianjin.web.controller;

import cn.hunantongxinqianjin.web.service.RecordService;
import cn.hunantongxinqianjin.web.utils.RequestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by shixiaoqi on 2017/5/13.
 */
@Controller
public class RecordController {

    @Autowired
    RecordService recordService;

    @RequestMapping(value = "/addRecord",method = RequestMethod.POST)
    @ResponseBody
    public String addRecord(@RequestParam(value = "id")String id, HttpServletRequest request){
        System.out.println("enter====="+id);
        String ip = RequestUtils.getIpAddr(request);
        int res = recordService.addRecord(id,ip);
        return "success";
    }
}
