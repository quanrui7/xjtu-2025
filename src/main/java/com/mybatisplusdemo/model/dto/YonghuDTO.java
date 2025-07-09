package com.mybatisplusdemo.model.dto;

import com.baomidou.mybatisplus.annotations.TableName;
import com.mybatisplusdemo.model.domain.YonghuEntity;
import org.apache.commons.beanutils.BeanUtils;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;


@TableName("yonghu")
public class YonghuDTO extends YonghuEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    public YonghuDTO() {
    }

    public YonghuDTO(YonghuEntity yonghuEntity) {
        try {
            BeanUtils.copyProperties(this, yonghuEntity);
        } catch (IllegalAccessException | InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


}
