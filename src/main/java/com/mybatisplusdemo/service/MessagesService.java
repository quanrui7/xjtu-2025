package com.mybatisplusdemo.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.mybatisplusdemo.model.domain.MessagesEntity;
import com.mybatisplusdemo.model.dto.MessagesDTO;
import com.mybatisplusdemo.common.utils.PageUtils;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


public interface MessagesService extends IService<MessagesEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<MessagesDTO> selectListView(Wrapper<MessagesEntity> wrapper);

    MessagesDTO selectView(@Param("ew") Wrapper<MessagesEntity> wrapper);

    PageUtils queryPage(Map<String, Object> params, Wrapper<MessagesEntity> wrapper);


}

