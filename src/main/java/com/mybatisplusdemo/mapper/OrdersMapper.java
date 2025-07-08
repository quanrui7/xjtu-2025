package com.mybatisplusdemo.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.mybatisplusdemo.model.domain.OrdersEntity;
import com.mybatisplusdemo.model.dto.OrdersDTO;
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
public interface OrdersMapper extends BaseMapper<OrdersEntity> {

    List<OrdersDTO> selectListView(@Param("ew") Wrapper<OrdersEntity> wrapper);

    List<OrdersDTO> selectListView(Pagination page, @Param("ew") Wrapper<OrdersEntity> wrapper);

    OrdersDTO selectView(@Param("ew") Wrapper<OrdersEntity> wrapper);


    List<Map<String, Object>> selectValue(@Param("params") Map<String, Object> params, @Param("ew") Wrapper<OrdersEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(@Param("params") Map<String, Object> params, @Param("ew") Wrapper<OrdersEntity> wrapper);

    List<Map<String, Object>> selectGroup(@Param("params") Map<String, Object> params, @Param("ew") Wrapper<OrdersEntity> wrapper);


}
