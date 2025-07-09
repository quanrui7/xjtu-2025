package com.mybatisplusdemo.model.dto;

import com.baomidou.mybatisplus.annotations.TableName;
import com.mybatisplusdemo.model.domain.ShangjiaEntity;
import org.apache.commons.beanutils.BeanUtils;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;


@TableName("shangjia")
public class ShangjiaDTO extends ShangjiaEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    public ShangjiaDTO() {
    }

    public ShangjiaDTO(ShangjiaEntity shangjiaEntity) {
        try {
            BeanUtils.copyProperties(this, shangjiaEntity);
        } catch (IllegalAccessException | InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


}
