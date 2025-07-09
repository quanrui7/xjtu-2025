package com.mybatisplusdemo.model.dto;

import com.baomidou.mybatisplus.annotations.TableName;
import com.mybatisplusdemo.model.domain.StoreupEntity;
import org.apache.commons.beanutils.BeanUtils;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;


@TableName("storeup")
public class StoreupDTO extends StoreupEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    public StoreupDTO() {
    }

    public StoreupDTO(StoreupEntity storeupEntity) {
        try {
            BeanUtils.copyProperties(this, storeupEntity);
        } catch (IllegalAccessException | InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


}
