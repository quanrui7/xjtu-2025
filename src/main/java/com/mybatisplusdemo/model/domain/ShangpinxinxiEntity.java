package com.mybatisplusdemo.model.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;


//商品信息
@TableName("lingshixinxi")
public class ShangpinxinxiEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 商品名称
     */

    private String lingshimingcheng;
    /**
     * 图片
     */

    private String tupian;
    /**
     * 商品分类
     */

    private String lingshifenlei;
    /**
     * 规格
     */

    private String guige;
    /**
     * 品牌
     */

    private String pinpai;
    /**
     * 商品详情
     */

    private String lingshixiangqing;
    /**
     * 视频
     */

    private String shipin;
    /**
     * 最近点击时间
     */

    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat
    private Date clicktime;
    /**
     * 价格
     */

    private Double price;
    /**
     * 商家账号
     */


    private String shangjiazhanghao;
    /**
     * 商家名称
     */

    private String shangjiamingcheng;
    /**
     * 是否审核
     */

    private String sfsh;
    /**
     * 回复内容
     */

    private String shhf;
    /**
     * 赞
     */

    @TableField("thumbsup_number")
    private Integer thumbsupNumber;
    /**
     * 踩
     */

    private Integer crazilyNumber;
    /**
     * 收藏数
     */

    @TableField("storeup_number")
    private Integer storeupNumber;
    /**
     * 评论数
     */

    private Integer discussNumber;
    /**
     * 点击次数
     */

    @TableField("click_number")
    private Integer clickNumber;
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat
    private Date addtime;

    public ShangpinxinxiEntity() {

    }


    public ShangpinxinxiEntity(T t) {
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
     * 获取：商品名称
     */
    public String getLingshimingcheng() {
        return lingshimingcheng;
    }

    /**
     * 设置：商品名称
     */
    public void setLingshimingcheng(String lingshimingcheng) {
        this.lingshimingcheng = lingshimingcheng;
    }

    /**
     * 获取：图片
     */
    public String getTupian() {
        return tupian;
    }

    /**
     * 设置：图片
     */
    public void setTupian(String tupian) {
        this.tupian = tupian;
    }

    /**
     * 获取：商品分类
     */
    public String getLingshifenlei() {
        return lingshifenlei;
    }

    /**
     * 设置：商品分类
     */
    public void setLingshifenlei(String lingshifenlei) {
        this.lingshifenlei = lingshifenlei;
    }

    /**
     * 获取：规格
     */
    public String getGuige() {
        return guige;
    }

    /**
     * 设置：规格
     */
    public void setGuige(String guige) {
        this.guige = guige;
    }

    /**
     * 获取：品牌
     */
    public String getPinpai() {
        return pinpai;
    }

    /**
     * 设置：品牌
     */
    public void setPinpai(String pinpai) {
        this.pinpai = pinpai;
    }

    /**
     * 获取：商品详情
     */
    public String getLingshixiangqing() {
        return lingshixiangqing;
    }

    /**
     * 设置：商品详情
     */
    public void setLingshixiangqing(String lingshixiangqing) {
        this.lingshixiangqing = lingshixiangqing;
    }

    /**
     * 获取：视频
     */
    public String getShipin() {
        return shipin;
    }

    /**
     * 设置：视频
     */
    public void setShipin(String shipin) {
        this.shipin = shipin;
    }

    /**
     * 获取：最近点击时间
     */
    public Date getClicktime() {
        return clicktime;
    }

    /**
     * 设置：最近点击时间
     */
    public void setClicktime(Date clicktime) {
        this.clicktime = clicktime;
    }

    /**
     * 获取：价格
     */
    public Double getPrice() {
        return price;
    }

    /**
     * 设置：价格
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * 获取：商家账号
     */
    public String getShangjiazhanghao() {
        return shangjiazhanghao;
    }

    /**
     * 设置：商家账号
     */
    public void setShangjiazhanghao(String shangjiazhanghao) {
        this.shangjiazhanghao = shangjiazhanghao;
    }

    /**
     * 获取：商家名称
     */
    public String getShangjiamingcheng() {
        return shangjiamingcheng;
    }

    /**
     * 设置：商家名称
     */
    public void setShangjiamingcheng(String shangjiamingcheng) {
        this.shangjiamingcheng = shangjiamingcheng;
    }

    /**
     * 获取：是否审核
     */
    public String getSfsh() {
        return sfsh;
    }

    /**
     * 设置：是否审核
     */
    public void setSfsh(String sfsh) {
        this.sfsh = sfsh;
    }

    /**
     * 获取：回复内容
     */
    public String getShhf() {
        return shhf;
    }

    /**
     * 设置：回复内容
     */
    public void setShhf(String shhf) {
        this.shhf = shhf;
    }

    /**
     * 获取：赞
     */
    public Integer getThumbsupNumber() {
        return thumbsupNumber;
    }

    /**
     * 设置：赞
     */
    public void setThumbsupNumber(Integer thumbsupNumber) {
        this.thumbsupNumber = thumbsupNumber;
    }

    /**
     * 获取：踩
     */
    public Integer getCrazilyNumber() {
        return crazilyNumber;
    }

    /**
     * 设置：踩
     */
    public void setCrazilyNumber(Integer crazilyNumber) {
        this.crazilyNumber = crazilyNumber;
    }

    /**
     * 获取：收藏数
     */
    public Integer getStoreupNumber() {
        return storeupNumber;
    }

    /**
     * 设置：收藏数
     */
    public void setStoreupNumber(Integer storeupNumber) {
        this.storeupNumber = storeupNumber;
    }

    /**
     * 获取：评论数
     */
    public Integer getDiscussNumber() {
        return discussNumber;
    }

    /**
     * 设置：评论数
     */
    public void setDiscussNumber(Integer discussNumber) {
        this.discussNumber = discussNumber;
    }

    /**
     * 获取：点击次数
     */
    public Integer getClickNumber() {
        return clickNumber;
    }

    /**
     * 设置：点击次数
     */
    public void setClickNumber(Integer clickNumber) {
        this.clickNumber = clickNumber;
    }

}
