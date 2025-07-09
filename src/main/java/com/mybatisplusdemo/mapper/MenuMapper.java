package com.mybatisplusdemo.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.mybatisplusdemo.model.domain.MenuEntity;
import com.mybatisplusdemo.model.dto.MenuDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;


//菜单
public interface MenuMapper extends BaseMapper<MenuEntity> {

    List<MenuDTO> selectListView(@Param("ew") Wrapper<MenuEntity> wrapper);

    List<MenuDTO> selectListView(Pagination page, @Param("ew") Wrapper<MenuEntity> wrapper);

    MenuDTO selectView(@Param("ew") Wrapper<MenuEntity> wrapper);


}
