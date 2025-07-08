package com.mybatisplusdemo.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.mybatisplusdemo.model.domain.CartEntity;
import com.mybatisplusdemo.model.dto.CartDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 购物车
 *
 * @author
 * @email
 * @date 2025-02-15 13:47:53
 */
public interface CartMapper extends BaseMapper<CartEntity> {

    List<CartDTO> selectListView(@Param("ew") Wrapper<CartEntity> wrapper);

    List<CartDTO> selectListView(Pagination page, @Param("ew") Wrapper<CartEntity> wrapper);

    CartDTO selectView(@Param("ew") Wrapper<CartEntity> wrapper);


}
