package com.mybatisplusdemo.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.mybatisplusdemo.model.domain.ShangpinfenleiEntity;
import com.mybatisplusdemo.model.dto.ShangpinfenleiDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;


//商品分类
public interface ShangpinfenleiMapper extends BaseMapper<ShangpinfenleiEntity> {

    List<ShangpinfenleiDTO> selectListView(@Param("ew") Wrapper<ShangpinfenleiEntity> wrapper);

    List<ShangpinfenleiDTO> selectListView(Pagination page, @Param("ew") Wrapper<ShangpinfenleiEntity> wrapper);

    ShangpinfenleiDTO selectView(@Param("ew") Wrapper<ShangpinfenleiEntity> wrapper);


}
