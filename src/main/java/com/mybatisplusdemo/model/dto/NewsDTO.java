package com.mybatisplusdemo.model.dto;

import com.baomidou.mybatisplus.annotations.TableName;
import com.mybatisplusdemo.model.domain.NewsEntity;
import org.apache.commons.beanutils.BeanUtils;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;


@TableName("news")
public class NewsDTO extends NewsEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    public NewsDTO() {
    }

    public NewsDTO(NewsEntity newsEntity) {
        try {
            BeanUtils.copyProperties(this, newsEntity);
        } catch (IllegalAccessException | InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


}
