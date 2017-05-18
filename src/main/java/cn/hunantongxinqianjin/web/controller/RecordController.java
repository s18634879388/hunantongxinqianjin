package cn.hunantongxinqianjin.web.controller;

import cn.hunantongxinqianjin.web.entity.Product;
import cn.hunantongxinqianjin.web.service.ProductService;
import cn.hunantongxinqianjin.web.service.RecordService;
import cn.hunantongxinqianjin.web.utils.RequestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

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
        int res = recordService.addMobileAndUser(phone,userName);
        Product product = productService.getProductById(Long.parseLong(proId));
        return product.getProductUrl();
    }
}
