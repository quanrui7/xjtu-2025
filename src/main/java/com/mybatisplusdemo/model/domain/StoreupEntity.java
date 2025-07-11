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


//我的收藏
@TableName("storeup")
public class StoreupEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * refid
     */

    private Long refid;
    /**
     * 表名
     */

    private String tablename;
    /**
     * 名称
     */

    private String name;
    /**
     * 图片
     */

    private String picture;
    /**
     * 类型(1:收藏,21:赞,22:踩,31:竞拍参与,41:关注)
     */

    private String type;
    /**
     * 推荐类型
     */

    private String inteltype;
    /**
     * 备注
     */

    private String remark;
    /**
     * 用户id
     */

    private Long userid;
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat
    private Date addtime;

    public StoreupEntity() {

    }


    public StoreupEntity(T t) {
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
     * 获取：refid
     */
    public Long getRefid() {
        return refid;
    }

    /**
     * 设置：refid
     */
    public void setRefid(Long refid) {
        this.refid = refid;
    }

    /**
     * 获取：表名
     */
    public String getTablename() {
        return tablename;
    }

    /**
     * 设置：表名
     */
    public void setTablename(String tablename) {
        this.tablename = tablename;
    }

    /**
     * 获取：名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置：名称
     */
    public void setName(String name) {
        this.name = name;
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
     * 获取：类型(1:收藏,21:赞,22:踩,31:竞拍参与,41:关注)
     */
    public String getType() {
        return type;
    }

    /**
     * 设置：类型(1:收藏,21:赞,22:踩,31:竞拍参与,41:关注)
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取：推荐类型
     */
    public String getInteltype() {
        return inteltype;
    }

    /**
     * 设置：推荐类型
     */
    public void setInteltype(String inteltype) {
        this.inteltype = inteltype;
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

}
