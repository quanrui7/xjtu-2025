package com.mybatisplusdemo.model.dto;

import com.baomidou.mybatisplus.annotations.TableName;
import com.mybatisplusdemo.model.domain.MessagesEntity;
import org.apache.commons.beanutils.BeanUtils;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;


/**
 * 留言板
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 *
 * @author
 * @email
 * @date 2025-02-15 13:47:52
 */
@TableName("messages")
public class MessagesDTO extends MessagesEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    public MessagesDTO() {
    }

    public MessagesDTO(MessagesEntity messagesEntity) {
        try {
            BeanUtils.copyProperties(this, messagesEntity);
        } catch (IllegalAccessException | InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


}
