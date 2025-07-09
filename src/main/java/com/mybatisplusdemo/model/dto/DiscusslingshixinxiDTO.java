package com.mybatisplusdemo.model.dto;

import com.baomidou.mybatisplus.annotations.TableName;
import com.mybatisplusdemo.model.domain.DiscusslingshixinxiEntity;
import org.apache.commons.beanutils.BeanUtils;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;



@TableName("discusslingshixinxi")
public class DiscusslingshixinxiDTO extends DiscusslingshixinxiEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    public DiscusslingshixinxiDTO() {
    }

    public DiscusslingshixinxiDTO(DiscusslingshixinxiEntity discusslingshixinxiEntity) {
        try {
            BeanUtils.copyProperties(this, discusslingshixinxiEntity);
        } catch (IllegalAccessException | InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


}
