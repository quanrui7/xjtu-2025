package com.mybatisplusdemo.model.dto;

import com.baomidou.mybatisplus.annotations.TableName;
import com.mybatisplusdemo.model.domain.OrdersEntity;
import org.apache.commons.beanutils.BeanUtils;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;


@TableName("orders")
public class OrdersDTO extends OrdersEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    public OrdersDTO() {
    }

    public OrdersDTO(OrdersEntity ordersEntity) {
        try {
            BeanUtils.copyProperties(this, ordersEntity);
        } catch (IllegalAccessException | InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


}
