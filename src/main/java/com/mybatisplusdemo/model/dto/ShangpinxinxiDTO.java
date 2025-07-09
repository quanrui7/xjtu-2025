package com.mybatisplusdemo.model.dto;

import com.baomidou.mybatisplus.annotations.TableName;
import com.mybatisplusdemo.model.domain.ShangpinxinxiEntity;
import org.apache.commons.beanutils.BeanUtils;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;


@TableName("lingshixinxi")
public class ShangpinxinxiDTO extends ShangpinxinxiEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    public ShangpinxinxiDTO() {
    }

    public ShangpinxinxiDTO(ShangpinxinxiEntity shangpinxinxiEntity) {
        try {
            BeanUtils.copyProperties(this, shangpinxinxiEntity);
        } catch (IllegalAccessException | InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


}
