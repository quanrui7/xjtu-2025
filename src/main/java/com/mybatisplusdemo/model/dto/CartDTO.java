package com.mybatisplusdemo.model.dto;

import com.baomidou.mybatisplus.annotations.TableName;
import com.mybatisplusdemo.model.domain.CartEntity;
import org.apache.commons.beanutils.BeanUtils;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;


@TableName("cart")
public class CartDTO extends CartEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    public CartDTO() {
    }

    public CartDTO(CartEntity cartEntity) {
        try {
            BeanUtils.copyProperties(this, cartEntity);
        } catch (IllegalAccessException | InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


}
