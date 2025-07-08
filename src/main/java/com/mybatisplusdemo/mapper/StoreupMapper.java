package com.mybatisplusdemo.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.mybatisplusdemo.model.domain.StoreupEntity;
import com.mybatisplusdemo.model.dto.StoreupDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 我的收藏
 *
 * @author
 * @email
 * @date 2025-02-15 13:47:53
 */
public interface StoreupMapper extends BaseMapper<StoreupEntity> {

    List<StoreupDTO> selectListView(@Param("ew") Wrapper<StoreupEntity> wrapper);

    List<StoreupDTO> selectListView(Pagination page, @Param("ew") Wrapper<StoreupEntity> wrapper);

    StoreupDTO selectView(@Param("ew") Wrapper<StoreupEntity> wrapper);


}
