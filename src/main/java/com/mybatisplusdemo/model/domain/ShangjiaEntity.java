package com.mybatisplusdemo.model.domain;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;


/**
 * 商家
 * 数据库通用操作实体类（普通增删改查）
 *
 * @author
 * @email
 * @date 2025-02-15 13:47:52
 */
@TableName("shangjia")
public class ShangjiaEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键id
     */
    @TableId
    private Long id;
    /**
     * 商家账号
     */

    private String shangjiazhanghao;
    /**
     * 商家密码
     */

    private String shangjiamima;
    /**
     * 商家姓名
     */

    private String shangjiaxingming;
    /**
     * 头像
     */

    private String touxiang;
    /**
     * 性别
     */

    private String xingbie;
    /**
     * 手机号码
     */

    private String shoujihaoma;
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat
    private Date addtime;

    public ShangjiaEntity() {

    }


    public ShangjiaEntity(T t) {
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
     * 获取：商家密码
     */
    public String getShangjiamima() {
        return shangjiamima;
    }

    /**
     * 设置：商家密码
     */
    public void setShangjiamima(String shangjiamima) {
        this.shangjiamima = shangjiamima;
    }

    /**
     * 获取：商家姓名
     */
    public String getShangjiaxingming() {
        return shangjiaxingming;
    }

    /**
     * 设置：商家姓名
     */
    public void setShangjiaxingming(String shangjiaxingming) {
        this.shangjiaxingming = shangjiaxingming;
    }

    /**
     * 获取：头像
     */
    public String getTouxiang() {
        return touxiang;
    }

    /**
     * 设置：头像
     */
    public void setTouxiang(String touxiang) {
        this.touxiang = touxiang;
    }

    /**
     * 获取：性别
     */
    public String getXingbie() {
        return xingbie;
    }

    /**
     * 设置：性别
     */
    public void setXingbie(String xingbie) {
        this.xingbie = xingbie;
    }

    /**
     * 获取：手机号码
     */
    public String getShoujihaoma() {
        return shoujihaoma;
    }

    /**
     * 设置：手机号码
     */
    public void setShoujihaoma(String shoujihaoma) {
        this.shoujihaoma = shoujihaoma;
    }

}
