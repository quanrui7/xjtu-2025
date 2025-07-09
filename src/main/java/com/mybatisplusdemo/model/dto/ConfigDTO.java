package com.mybatisplusdemo.model.dto;

import com.baomidou.mybatisplus.annotations.TableName;
import com.mybatisplusdemo.model.domain.ConfigEntity;
import org.apache.commons.beanutils.BeanUtils;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;


@TableName("config")
public class ConfigDTO extends ConfigEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    public ConfigDTO() {
    }

    public ConfigDTO(ConfigEntity configEntity) {
        try {
            BeanUtils.copyProperties(this, configEntity);
        } catch (IllegalAccessException | InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


}
