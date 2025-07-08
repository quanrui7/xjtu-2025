package com.mybatisplusdemo.model.dto;

import com.baomidou.mybatisplus.annotations.TableName;
import com.mybatisplusdemo.model.domain.ShangjiaEntity;
import org.apache.commons.beanutils.BeanUtils;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;


/**
 * 商家
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 *
 * @author
 * @email
 * @date 2025-02-15 13:47:52
 */
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
