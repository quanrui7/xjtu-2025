package com.mybatisplusdemo.model.dto;

import com.baomidou.mybatisplus.annotations.TableName;
import com.mybatisplusdemo.model.domain.MenuEntity;
import org.apache.commons.beanutils.BeanUtils;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;


@TableName("menu")
public class MenuDTO extends MenuEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    public MenuDTO() {
    }

    public MenuDTO(MenuEntity menuEntity) {
        try {
            BeanUtils.copyProperties(this, menuEntity);
        } catch (IllegalAccessException | InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


}
