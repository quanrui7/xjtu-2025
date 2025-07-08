package com.mybatisplusdemo.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.mybatisplusdemo.model.domain.OrdersEntity;
import com.mybatisplusdemo.model.dto.OrdersDTO;
import com.mybatisplusdemo.common.utils.PageUtils;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * 商品订单
 *
 * @author
 * @email
 * @date 2025-02-15 13:47:53
 */
public interface OrdersService extends IService<OrdersEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<OrdersDTO> selectListView(Wrapper<OrdersEntity> wrapper);

    OrdersDTO selectView(@Param("ew") Wrapper<OrdersEntity> wrapper);

    PageUtils queryPage(Map<String, Object> params, Wrapper<OrdersEntity> wrapper);


    List<Map<String, Object>> selectValue(Map<String, Object> params, Wrapper<OrdersEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params, Wrapper<OrdersEntity> wrapper);

    List<Map<String, Object>> selectGroup(Map<String, Object> params, Wrapper<OrdersEntity> wrapper);


}

