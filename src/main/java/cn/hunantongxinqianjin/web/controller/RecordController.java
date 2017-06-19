package cn.hunantongxinqianjin.web.controller;

import cn.hunantongxinqianjin.web.entity.Product;
import cn.hunantongxinqianjin.web.entity.UserOpen;
import cn.hunantongxinqianjin.web.service.ProductService;
import cn.hunantongxinqianjin.web.service.RecordService;
import cn.hunantongxinqianjin.web.utils.RequestUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.enmus.ExcelType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by shixiaoqi on 2017/5/13.
 */
@Controller
public class RecordController {

    @Autowired
    RecordService recordService;
    @Autowired
    ProductService productService;

    /**
     * 用户点击后记录（每个ip每日每个产品点击多次只记录一次）
     * @param id
     * @param request
     * @return
     */
    @RequestMapping(value = "/addRecord",method = RequestMethod.POST)
    @ResponseBody
    public String addRecord(Model model, @RequestParam(value = "id")String id, HttpServletRequest request){
        System.out.println("--------------"+id);
        String ip = RequestUtils.getIpAddr(request);
        int res = recordService.addRecord(id,ip);
        model.addAttribute("proId",id);
        return "to_first";
    }
    @RequestMapping(value = "/to_first.html",method = RequestMethod.GET)
    public String toFirst(Model model, @RequestParam(value = "id")String id){
        model.addAttribute("proId",id);
        return "to_first";
    }

    @RequestMapping(value = "/addMobileAndUser",method = RequestMethod.GET)
    @ResponseBody
    public String addMobileAndUser(@RequestParam(value = "phone")String phone,@RequestParam(value = "userName")String userName,
                                   @RequestParam(value = "proId")String proId) throws UnsupportedEncodingException {
        int res = recordService.addMobileAndUser(phone,userName,Long.parseLong(proId));
        Product product = productService.getProductById(Long.parseLong(proId));
        return product.getProductUrl();
    }

    @RequestMapping(value = "/getMobileAndUser",method = RequestMethod.POST)
    @ResponseBody
    public String getMobileAndUser(@RequestParam(value = "chooseTime")String chooseTime,@RequestParam(value = "proId")String proId,
                                   HttpServletResponse response
                                   ) throws IOException, ParseException {

        List<UserOpen> userOpens = recordService.getMobileAndUser(chooseTime,Long.parseLong(proId));
        if (userOpens==null||userOpens.size()<=0){
            return "当日没有记录,导出失败";
        }
        Long time = new Date().getTime();
        response.setHeader("conent-type", "application/octet-stream");
        response.setContentType("application/octet-stream");
        response.addHeader("Content-Disposition",
                "attachment;fileName=" + time+".xls");

        ExportParams exportParams = new ExportParams("用户信息记录","用户信息", ExcelType.XSSF);
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, UserOpen.class,userOpens);
        OutputStream fileOutputStream = response.getOutputStream();
        workbook.write(fileOutputStream);
        fileOutputStream.close();
        return "操作成功";
    }


    @RequestMapping(value = "/getRecordByDate",method = RequestMethod.POST)
    public String getRecordByDate(Model model, @RequestParam(value = "chooseTime")String chooseTime) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date cTime =  simpleDateFormat.parse(chooseTime);
        List<Product> products = productService.getRecordByDate(cTime);
        model.addAttribute("products",products);
        return "backpronum_list2";
    }
}
