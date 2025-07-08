package com.mybatisplusdemo.model.dto;

import com.baomidou.mybatisplus.annotations.TableName;
import com.mybatisplusdemo.model.domain.StoreupEntity;
import org.apache.commons.beanutils.BeanUtils;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;


/**
 * 我的收藏
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 *
 * @author
 * @email
 * @date 2025-02-15 13:47:53
 */
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
