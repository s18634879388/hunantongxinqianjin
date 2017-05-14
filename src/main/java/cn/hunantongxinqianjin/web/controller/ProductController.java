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
import java.util.List;

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
    public String backRecordEdit(Model model,@RequestParam(value = "proId") String id){
        //查询
        Product product = productService.getProductById(Long.parseLong(id));
        model.addAttribute("proMod",product);
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
    @ResponseBody
    public String addProduct(@RequestParam(value = "topic")String topic,@RequestParam(value = "message")String message,
                             @RequestParam(value = "productName")String productName,@RequestParam(value = "imgUrl")MultipartFile file,
                             @RequestParam(value = "productUrl")String productUrl,Model model) throws Exception {
        String imgUrl = null;
        try {
            imgUrl = productService.updateHead(file);//此处是调用上传服务接口，4是需要更新的userId 测试数据。
            System.out.println("yes++++++++++"+imgUrl);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("图片上传失败");
        }
        System.out.println(topic+"======"+message+"======"+productName+"======"+"======"+productUrl);
        Product product = new Product();
        product.setImgUrl(imgUrl);
        product.setMessage(message);
        product.setTopic(topic);
        product.setProductName(productName);
        product.setProductUrl(productUrl);
        //添加
        int res = productService.addProduct(product);
        model.addAttribute("addMessage","添加成功");
        return "操作成功";
    }
    @RequestMapping(value = "/modify-product.html",method = RequestMethod.POST)
    @ResponseBody
    public String modifyProduct(@RequestParam(value = "topic")String topic,@RequestParam(value = "message")String message,
                             @RequestParam(value = "productName")String productName,@RequestParam(value = "imgUrl",required = false)MultipartFile file,
                             @RequestParam(value = "productUrl")String productUrl,@RequestParam(value = "proId")String id,Model model) throws Exception {
        Product product = new Product();
        product.setId(Long.parseLong(id));
        product.setMessage(message);
        product.setTopic(topic);
        product.setProductName(productName);
        product.setProductUrl(productUrl);
        if (file==null){
            int res = productService.modifyProduct(product);
        }else {
            String imgUrl = null;
            try {
                imgUrl = productService.updateHead(file);//此处是调用上传服务接口，4是需要更新的userId 测试数据。
                product.setImgUrl(imgUrl);
                int res = productService.modifyProduct(product);
                System.out.println("yes++++++++++"+imgUrl);
            } catch (Exception e) {
                e.printStackTrace();
                throw new Exception("图片上传失败");
            }
        }
        return "修改成功";
    }



}
