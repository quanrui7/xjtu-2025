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


//商品订单
@TableName("orders")
public class OrdersEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 订单编号
     */

    private String orderid;
    /**
     * 商品表名
     */

    private String tablename;
    /**
     * 商品id
     */

    private Long goodid;
    /**
     * 商品名称
     */

    private String goodname;
    /**
     * 图片
     */

    private String picture;
    /**
     * 购买数量
     */

    private Integer buynumber;
    /**
     * 单价
     */

    private Double price;
    /**
     * 折扣价
     */

    private Double discountprice;
    /**
     * 总价
     */

    private Double total;
    /**
     * 折扣总价格
     */

    private Double discounttotal;
    /**
     * 支付类型
     */

    private String type;
    /**
     * 订单状态
     */

    private String status;
    /**
     * 地址
     */

    private String address;
    /**
     * 电话
     */

    private String tel;
    /**
     * 收货人
     */

    private String consignee;
    /**
     * 备注
     */

    private String remark;
    /**
     * 物流
     */

    private String logistics;
    /**
     * 用户角色
     */

    private String role;
    /**
     * 用户id
     */

    private Long userid;
    /**
     * 商户名称
     */

    private String shangjiazhanghao;
    /**
     * 商品类型
     */

    private String goodtype;
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat
    private Date addtime;

    public OrdersEntity() {

    }


    public OrdersEntity(T t) {
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

    /**
     * 获取：订单编号
     */
    public String getOrderid() {
        return orderid;
    }

    /**
     * 设置：订单编号
     */
    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    /**
     * 获取：商品表名
     */
    public String getTablename() {
        return tablename;
    }

    /**
     * 设置：商品表名
     */
    public void setTablename(String tablename) {
        this.tablename = tablename;
    }

    /**
     * 获取：商品id
     */
    public Long getGoodid() {
        return goodid;
    }

    /**
     * 设置：商品id
     */
    public void setGoodid(Long goodid) {
        this.goodid = goodid;
    }

    /**
     * 获取：商品名称
     */
    public String getGoodname() {
        return goodname;
    }

    /**
     * 设置：商品名称
     */
    public void setGoodname(String goodname) {
        this.goodname = goodname;
    }

    /**
     * 获取：图片
     */
    public String getPicture() {
        return picture;
    }

    /**
     * 设置：图片
     */
    public void setPicture(String picture) {
        this.picture = picture;
    }

    /**
     * 获取：购买数量
     */
    public Integer getBuynumber() {
        return buynumber;
    }

    /**
     * 设置：购买数量
     */
    public void setBuynumber(Integer buynumber) {
        this.buynumber = buynumber;
    }

    /**
     * 获取：单价
     */
    public Double getPrice() {
        return price;
    }

    /**
     * 设置：单价
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * 获取：折扣价
     */
    public Double getDiscountprice() {
        return discountprice;
    }

    /**
     * 设置：折扣价
     */
    public void setDiscountprice(Double discountprice) {
        this.discountprice = discountprice;
    }

    /**
     * 获取：总价
     */
    public Double getTotal() {
        return total;
    }

    /**
     * 设置：总价
     */
    public void setTotal(Double total) {
        this.total = total;
    }

    /**
     * 获取：折扣总价格
     */
    public Double getDiscounttotal() {
        return discounttotal;
    }

    /**
     * 设置：折扣总价格
     */
    public void setDiscounttotal(Double discounttotal) {
        this.discounttotal = discounttotal;
    }

    /**
     * 获取：支付类型
     */
    public String getType() {
        return type;
    }

    /**
     * 设置：支付类型
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取：订单状态
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置：订单状态
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取：地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置：地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取：电话
     */
    public String getTel() {
        return tel;
    }

    /**
     * 设置：电话
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * 获取：收货人
     */
    public String getConsignee() {
        return consignee;
    }

    /**
     * 设置：收货人
     */
    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    /**
     * 获取：备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置：备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取：物流
     */
    public String getLogistics() {
        return logistics;
    }

    /**
     * 设置：物流
     */
    public void setLogistics(String logistics) {
        this.logistics = logistics;
    }

    /**
     * 获取：用户角色
     */
    public String getRole() {
        return role;
    }

    /**
     * 设置：用户角色
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * 获取：用户id
     */
    public Long getUserid() {
        return userid;
    }

    /**
     * 设置：用户id
     */
    public void setUserid(Long userid) {
        this.userid = userid;
    }

    /**
     * 获取：商户名称
     */
    public String getShangjiazhanghao() {
        return shangjiazhanghao;
    }

    /**
     * 设置：商户名称
     */
    public void setShangjiazhanghao(String shangjiazhanghao) {
        this.shangjiazhanghao = shangjiazhanghao;
    }

    /**
     * 获取：商品类型
     */
    public String getGoodtype() {
        return goodtype;
    }

    /**
     * 设置：商品类型
     */
    public void setGoodtype(String goodtype) {
        this.goodtype = goodtype;
    }

}
