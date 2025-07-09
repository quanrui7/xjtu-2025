package com.mybatisplusdemo.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.mybatisplusdemo.model.domain.ShangjiaEntity;
import com.mybatisplusdemo.model.dto.ShangjiaDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;


//商家
public interface ShangjiaMapper extends BaseMapper<ShangjiaEntity> {

    List<ShangjiaDTO> selectListView(@Param("ew") Wrapper<ShangjiaEntity> wrapper);

    List<ShangjiaDTO> selectListView(Pagination page, @Param("ew") Wrapper<ShangjiaEntity> wrapper);

    ShangjiaDTO selectView(@Param("ew") Wrapper<ShangjiaEntity> wrapper);


}
