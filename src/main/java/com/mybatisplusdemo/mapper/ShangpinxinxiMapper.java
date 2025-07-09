package com.mybatisplusdemo.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.mybatisplusdemo.model.domain.ShangpinxinxiEntity;
import com.mybatisplusdemo.model.dto.ShangpinxinxiDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;


//商品信息
public interface ShangpinxinxiMapper extends BaseMapper<ShangpinxinxiEntity> {

    List<ShangpinxinxiDTO> selectListView(@Param("ew") Wrapper<ShangpinxinxiEntity> wrapper);

    List<ShangpinxinxiDTO> selectListView(Pagination page, @Param("ew") Wrapper<ShangpinxinxiEntity> wrapper);

    ShangpinxinxiDTO selectView(@Param("ew") Wrapper<ShangpinxinxiEntity> wrapper);


}
