package cn.hunantongxinqianjin.web.controller;

import cn.hunantongxinqianjin.web.entity.Product;
import cn.hunantongxinqianjin.web.entity.UserInfo;
import cn.hunantongxinqianjin.web.service.ProductService;
import cn.hunantongxinqianjin.web.service.RecordService;
import cn.hunantongxinqianjin.web.service.UserInfoService;
import cn.hunantongxinqianjin.web.utils.Md5Utils;
import cn.hunantongxinqianjin.web.utils.PageRequest;
import cn.hunantongxinqianjin.web.utils.WebSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by shixiaoqi on 2017/5/13.
 * 产品控制器类
 */
@Controller
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    RecordService recordService;
    @Autowired
    UserInfoService userInfoService;

    /**
     *  前台客户端展示   获取所有上架的产品信息
     * @param model
     * @return
     */
    @RequestMapping(value = "/home",method = RequestMethod.GET)
    public String freeMarkerDemo(Model model,@RequestParam(value = "last",required = false) String last){
        List<Product> products = productService.getAllProducts();
        model.addAttribute("list1",products);
        model.addAttribute("proUpdate",products.get(0).getUpdatedAt());
        return "home2";
    }

    /**
     * 后台修改产品信息用于数据反显接口
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value = "/back-record-edit.html",method = RequestMethod.GET)
    public String backRecordEdit(Model model,@RequestParam(value = "proId") String id){
        //查询
        Product product = productService.getProductById(Long.parseLong(id));
        model.addAttribute("proMod",product);
        return "backhouse_edit";
    }

    /**
     * 跳转修改产品页
     * @param model
     * @return
     */
    @RequestMapping(value = "/back-record-add.html",method = RequestMethod.GET)
    public String backRecordAdd(Model model){
        return "backhouse_add";
    }

    /**
     * 后台产品列表（分页显示） 获取所有的产品信息
     * @param model
     * @param pageNo
     * @return
     */
    @RequestMapping(value = "/back-record-list.html",method = RequestMethod.GET)
    public String backRecordList(Model model,@RequestParam(value = "pageNo") String pageNo){
        //
        PageRequest pageRequest = new PageRequest(10,Integer.parseInt(pageNo),0,true);
        int count = productService.getAllProductCount();
        int pageCount = count % 10 == 0 ? count/10 : (count/10) +1;
        List<Product> products = productService.getFirstProducts(pageRequest);
//        for (Product p:products
//             ) {
//            int clickNum = recordService.getCountByPro(p.getId());
//            p.setClickNum(clickNum);
//        }
        model.addAttribute("pageCount",pageCount);
        model.addAttribute("products",products);
        model.addAttribute("pageNo",pageRequest.getPageNo());
        model.addAttribute("recordCount",count);
        return "backhouse_list";
    }

    /**
     * 跳转登录页
     * @param model
     * @return
     */
    @RequestMapping(value = "/back-login.html",method = RequestMethod.GET)
    public String backLogin(Model model,HttpSession session){
        session.removeAttribute(WebSecurityConfig.SESSION_KEY);
        return "backlogin";
    }

    /**
     * 登录功能   跳转首页
     * @param model
     * @param userName
     * @param password
     * @param session
     * @return
     */
    @RequestMapping(value = "/back-home-go.html",method = RequestMethod.POST)
    public String backHomeGo(Model model, @RequestParam(value = "userName")String userName, @RequestParam(value = "password")String password, HttpSession session){
        String md5Pass = Md5Utils.md5(password);
        UserInfo userInfo =  userInfoService.getUserByPasswordAndName(userName,md5Pass);
        if (userInfo==null){
            return "backlogin";
        }else {
            session.setAttribute(WebSecurityConfig.SESSION_KEY,userName);
            return "backindex";
        }
    }

    /**
     *
     * @param model
     * @return
     */
//    @RequestMapping(value = "/back-home.html",method = RequestMethod.GET)
//    public String backHome2(Model model){
//        return "backindex";
//    }

    /**
     * 添加产品   功能实现
     * @param topic
     * @param message
     * @param productName
     * @param file
     * @param productUrl
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/add-product.html",method = RequestMethod.POST)
    @ResponseBody
    public String addProduct(@RequestParam(value = "topic")String topic,@RequestParam(value = "message")String message,
                             @RequestParam(value = "productName")String productName,@RequestParam(value = "imgUrl")MultipartFile file,
                             @RequestParam(value = "productUrl")String productUrl,@RequestParam(value = "displayOrder")String displayOrder,Model model) throws Exception {
        String imgUrl = null;
        try {
            imgUrl = productService.updateHead(file);//此处是调用上传服务接口，4是需要更新的userId 测试数据。
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("图片上传失败");
        }
        Product product = new Product();
        product.setImgUrl(imgUrl);
        product.setMessage(message);
        product.setTopic(topic);
        product.setProductName(productName);
        product.setProductUrl(productUrl);
        product.setDisplayOrder(Integer.parseInt(displayOrder));
        //添加
        int res = productService.addProduct(product);
        model.addAttribute("addMessage","添加成功");
        return "操作成功";
    }

    /**
     * 后台修改产品信息  真实修改操作
     * @param topic
     * @param message
     * @param productName
     * @param file
     * @param productUrl
     * @param id
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/modify-product.html",method = RequestMethod.POST)
    @ResponseBody
    public String modifyProduct(@RequestParam(value = "topic")String topic,@RequestParam(value = "message")String message,
                             @RequestParam(value = "productName")String productName,@RequestParam(value = "imgUrl",required = false)MultipartFile file,
                             @RequestParam(value = "productUrl")String productUrl,@RequestParam(value = "proId")String id,@RequestParam(value = "displayOrder")String displayOrder,Model model) throws Exception {
        Product product = new Product();
        product.setId(Long.parseLong(id));
        product.setMessage(message);
        product.setTopic(topic);
        product.setProductName(productName);
        product.setProductUrl(productUrl);
        product.setDisplayOrder(Integer.parseInt(displayOrder));
        if (file==null){
            int res = productService.modifyProduct(product);
        }else {
            String imgUrl = null;
            try {
                imgUrl = productService.updateHead(file);//此处是调用上传服务接口，4是需要更新的userId 测试数据。
                product.setImgUrl(imgUrl);
                int res = productService.modifyProduct(product);
            } catch (Exception e) {
                e.printStackTrace();
                throw new Exception("图片上传失败");
            }
        }
        return "修改成功";
    }

    /**
     * 流量统计  （分页实现）
     * @param model
     * @param pageNo
     * @return
     */
    @RequestMapping(value = "/back-product-num.html",method = RequestMethod.GET)
    public String backProNumList(Model model,@RequestParam(value = "pageNo") String pageNo){
        PageRequest pageRequest = new PageRequest(10,Integer.parseInt(pageNo),0,true);
        List<Product> products = productService.getFirstProducts(pageRequest);
        for (Product p:products
                ) {
            int clickNum = recordService.getCountByPro(p.getId());
            int clickNumMonth = recordService.getclickNumMonthByPro(p.getId());
            int clickNumOfMonth = recordService.getclickNumOfMonthByPro(p.getId());
            int clickNumYear = recordService.getclickNumYearByPro(p.getId());
            int clickNumAll = recordService.getclickNumAllByPro(p.getId());
            p.setClickNum(clickNum);
            p.setClickNumMonth(clickNumMonth);
            p.setClickNumOfMonth(clickNumOfMonth);
            p.setClickNumYear(clickNumYear);
            p.setClickNumYear(clickNumYear);
            p.setClickNumAll(clickNumAll);
        }
        int count = productService.getAllProductCount();
        int pageCount = (count+pageRequest.getPageSize()-1)/pageRequest.getPageSize();
        model.addAttribute("pageCount",pageCount);
        model.addAttribute("products",products);
        model.addAttribute("pageNo",pageRequest.getPageNo());
        model.addAttribute("recordCount",count);
        return "backpronum_list";
    }

    /**
     * 修改产品上下架状态
     * @param pageNo
     * @param proId
     * @return
     */
    @RequestMapping(value = "/modify-pro-state.html",method = RequestMethod.GET)
    public String modifyProState(@RequestParam(value = "pageNo")String pageNo,@RequestParam(value = "proId")String proId){
        int res = productService.modifyProState(Long.parseLong(proId));
        return "forward:/back-record-list.html?pageNo";
    }





}
