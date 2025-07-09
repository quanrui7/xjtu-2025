package com.mybatisplusdemo.model.dto;

import com.baomidou.mybatisplus.annotations.TableName;
import com.mybatisplusdemo.model.domain.AddressEntity;
import org.apache.commons.beanutils.BeanUtils;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;



@TableName("address")
public class AddressDTO extends AddressEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    public AddressDTO() {
    }

    public AddressDTO(AddressEntity addressEntity) {
        try {
            BeanUtils.copyProperties(this, addressEntity);
        } catch (IllegalAccessException | InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


}
