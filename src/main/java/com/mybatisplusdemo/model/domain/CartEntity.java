package com.mybatisplusdemo.model.domain;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;


//购物车
@TableName("cart")
public class CartEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;
    //商品表名
    private String tablename;
    //商品id
    private Long goodid;
    //商品名称
    private String goodname;
    //图片
    private String picture;
    //购买数量
    private Integer buynumber;
    //单价
    private Double price;
    //折扣价
    private Double discountprice;
    //用户id
    private Long userid;
    //商户名称
    private String shangjiazhanghao;
    //商品类型
    private String goodtype;
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat
    private Date addtime;

    public CartEntity() {

    }


    public CartEntity(T t) {
        try {
            BeanUtils.copyProperties(this, t);
        } catch (IllegalAccessException | InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getTablename() {
        return tablename;
    }


    public void setTablename(String tablename) {
        this.tablename = tablename;
    }


    public Long getGoodid() {
        return goodid;
    }


    public void setGoodid(Long goodid) {
        this.goodid = goodid;
    }


    public String getGoodname() {
        return goodname;
    }


    public void setGoodname(String goodname) {
        this.goodname = goodname;
    }


    public String getPicture() {
        return picture;
    }


    public void setPicture(String picture) {
        this.picture = picture;
    }


    public Integer getBuynumber() {
        return buynumber;
    }


    public void setBuynumber(Integer buynumber) {
        this.buynumber = buynumber;
    }


    public Double getPrice() {
        return price;
    }


    public void setPrice(Double price) {
        this.price = price;
    }


    public Double getDiscountprice() {
        return discountprice;
    }


    public void setDiscountprice(Double discountprice) {
        this.discountprice = discountprice;
    }


    public Long getUserid() {
        return userid;
    }


    public void setUserid(Long userid) {
        this.userid = userid;
    }


    public String getShangjiazhanghao() {
        return shangjiazhanghao;
    }


    public void setShangjiazhanghao(String shangjiazhanghao) {
        this.shangjiazhanghao = shangjiazhanghao;
    }


    public String getGoodtype() {
        return goodtype;
    }


    public void setGoodtype(String goodtype) {
        this.goodtype = goodtype;
    }

}
