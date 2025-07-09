package com.mybatisplusdemo.model.dto;

import com.baomidou.mybatisplus.annotations.TableName;
import com.mybatisplusdemo.model.domain.MessagesEntity;
import org.apache.commons.beanutils.BeanUtils;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;


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
