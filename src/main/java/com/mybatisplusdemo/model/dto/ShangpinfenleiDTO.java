package com.mybatisplusdemo.model.dto;

import com.baomidou.mybatisplus.annotations.TableName;
import com.mybatisplusdemo.model.domain.ShangpinfenleiEntity;
import org.apache.commons.beanutils.BeanUtils;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;


@TableName("lingshifenlei")
public class ShangpinfenleiDTO extends ShangpinfenleiEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    public ShangpinfenleiDTO() {
    }

    public ShangpinfenleiDTO(ShangpinfenleiEntity shangpinfenleiEntity) {
        try {
            BeanUtils.copyProperties(this, shangpinfenleiEntity);
        } catch (IllegalAccessException | InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


}
