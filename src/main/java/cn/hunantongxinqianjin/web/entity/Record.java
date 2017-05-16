package cn.hunantongxinqianjin.web.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 点击记录实体类
 * Created by shixiaoqi on 2017/5/13.
 */
public class Record implements Serializable {
    private Long id;        //唯一主键
    private String ip;      //访问者ip
    private Long productId;   //产品id
    private Long clickNum;      //点击数
    private Date createdAt;     //点击时间
    private Date updatedAt;     //修改时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getClickNum() {
        return clickNum;
    }

    public void setClickNum(Long clickNum) {
        this.clickNum = clickNum;
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
}
