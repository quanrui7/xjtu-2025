package com.mybatisplusdemo.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.mybatisplusdemo.model.domain.CartEntity;
import com.mybatisplusdemo.model.dto.CartDTO;
import com.mybatisplusdemo.common.utils.PageUtils;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


public interface CartService extends IService<CartEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<CartDTO> selectListView(Wrapper<CartEntity> wrapper);

    CartDTO selectView(@Param("ew") Wrapper<CartEntity> wrapper);

    PageUtils queryPage(Map<String, Object> params, Wrapper<CartEntity> wrapper);


}

