package com.mybatisplusdemo.model.dto;

import com.baomidou.mybatisplus.annotations.TableName;
import com.mybatisplusdemo.model.domain.LingshixinxiEntity;
import org.apache.commons.beanutils.BeanUtils;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;


/**
 * 零食信息
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 *
 * @author
 * @email
 * @date 2025-02-15 13:47:52
 */
@TableName("lingshixinxi")
public class LingshixinxiDTO extends LingshixinxiEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    public LingshixinxiDTO() {
    }

    public LingshixinxiDTO(LingshixinxiEntity lingshixinxiEntity) {
        try {
            BeanUtils.copyProperties(this, lingshixinxiEntity);
        } catch (IllegalAccessException | InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


}
