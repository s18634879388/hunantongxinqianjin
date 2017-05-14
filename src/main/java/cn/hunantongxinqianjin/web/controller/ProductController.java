package cn.hunantongxinqianjin.web.controller;

import cn.hunantongxinqianjin.web.entity.Product;
import cn.hunantongxinqianjin.web.service.ProductService;
import cn.hunantongxinqianjin.web.utils.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by shixiaoqi on 2017/5/13.
 */
@Controller
public class ProductController {
    @Autowired
    ProductService productService;


    @RequestMapping(value = "/home.html",method = RequestMethod.GET)
    public String freeMarkerDemo(Model model){
        List<Product> products = productService.getAllProducts();
        model.addAttribute("list1",products);
        return "index";
    }

    @RequestMapping(value = "/back-record-edit.html",method = RequestMethod.GET)
    public String backRecordEdit(Model model){
        return "backhouse_edit";
    }@RequestMapping(value = "/back-record-add.html",method = RequestMethod.GET)
    public String backRecordAdd(Model model){
        return "backhouse_add";
    }
    @RequestMapping(value = "/back-record-list.html",method = RequestMethod.GET)
    public String backRecordList(Model model,@RequestParam(value = "pageNo") String pageNo){
        //
        System.out.println(pageNo+"...........");
        PageRequest pageRequest = new PageRequest(10,Integer.parseInt(pageNo),0,true);
        int count = productService.getAllProductCount();
        int pageCount = (count+pageRequest.getPageSize()-1)/pageRequest.getPageSize();
        List<Product> products = productService.getFirstProducts(pageRequest);
        model.addAttribute("pageCount",pageCount);
        model.addAttribute("products",products);
        model.addAttribute("pageNo",pageRequest.getPageNo());
        model.addAttribute("recordCount",count);
        return "backhouse_list";
    }
    @RequestMapping(value = "/back-record-produce.html",method = RequestMethod.GET)
    public String backRecordProduce(Model model){
        return "backintroduce";
    }

    @RequestMapping(value = "/back-login.html",method = RequestMethod.GET)
    public String backLogin(Model model){
        return "backlogin";
    }
    @RequestMapping(value = "/back-home.html",method = RequestMethod.POST)
    public String backHome(Model model){
        return "backindex";
    }
    @RequestMapping(value = "/back-home.html",method = RequestMethod.GET)
    public String backHome2(Model model){
        return "backindex";
    }

    @RequestMapping(value = "/add-product.html",method = RequestMethod.POST)
    public String addProduct(@RequestParam(value = "topic")String topic,@RequestParam(value = "message")String message,
                             @RequestParam(value = "productName")String productName,@RequestParam(value = "imgUrl")MultipartFile file,
                             @RequestParam(value = "productUrl")String productUrl){

        Map<String, Object> value = new HashMap<String, Object>();
        value.put("success", true);
        value.put("errorCode", 0);
        value.put("errorMsg", "");
        try {
            String head = productService.updateHead(file, 4);//此处是调用上传服务接口，4是需要更新的userId 测试数据。
            value.put("data", head);
        } catch (Exception e) {
            e.printStackTrace();
            value.put("success", false);
            value.put("errorCode", 200);
            value.put("errorMsg", "图片上传失败");
        }


        System.out.println(topic+"======"+message+"======"+productName+"======"+"======"+productUrl);
//        Product product = new Product();
//        product.setImgUrl(imgUrl);
//        product.setMessage(message);
//        product.setTopic(topic);
//        product.setProductName(productName);
//        product.setProductUrl(productUrl);
        //添加
        return "backhouse_list";
    }


}
