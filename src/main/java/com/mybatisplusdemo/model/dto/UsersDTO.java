package com.mybatisplusdemo.model.dto;

import com.baomidou.mybatisplus.annotations.TableName;
import com.mybatisplusdemo.model.domain.UsersEntity;
import org.apache.commons.beanutils.BeanUtils;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;


@TableName("users")
public class UsersDTO extends UsersEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    public UsersDTO() {
    }

    public UsersDTO(UsersEntity usersEntity) {
        try {
            BeanUtils.copyProperties(this, usersEntity);
        } catch (IllegalAccessException | InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


}
