package cn.hunantongxinqianjin.web.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by shixiaoqi on 2017/5/13.
 */
public class Product implements Serializable {
    private Long id;        //唯一主键
    private String topic;   //标题
    private String message; //内容
    private String productUrl;//产品链接
    private String productName;//产品名称
    private String imgUrl;//图片链接
    private int isDelete;   //是否删除
    private Date createdAt; //创建时间
    private Date updatedAt; //修改时间
    private int clickNum;//日流量
    private int clickNumMonth;//月流量
    private int clickNumOfMonth;//一月内流量
    private int clickNumYear;//年流量
    private int clickNumAll;//所有流量
    private String state;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getClickNum() {
        return clickNum;
    }

    public void setClickNum(int clickNum) {
        this.clickNum = clickNum;
    }

    public int getClickNumMonth() {
        return clickNumMonth;
    }

    public void setClickNumMonth(int clickNumMonth) {
        this.clickNumMonth = clickNumMonth;
    }

    public int getClickNumOfMonth() {
        return clickNumOfMonth;
    }

    public void setClickNumOfMonth(int clickNumOfMonth) {
        this.clickNumOfMonth = clickNumOfMonth;
    }

    public int getClickNumYear() {
        return clickNumYear;
    }

    public void setClickNumYear(int clickNumYear) {
        this.clickNumYear = clickNumYear;
    }

    public int getClickNumAll() {
        return clickNumAll;
    }

    public void setClickNumAll(int clickNumAll) {
        this.clickNumAll = clickNumAll;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        if (this.isDelete==0){
            this.state = "已下架";
        }else {
            this.state = "已上线";
        }
    }
}
