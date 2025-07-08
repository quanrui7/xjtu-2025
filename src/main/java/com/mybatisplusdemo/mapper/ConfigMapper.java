package com.mybatisplusdemo.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.mybatisplusdemo.model.domain.ConfigEntity;
import com.mybatisplusdemo.model.dto.ConfigDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 轮播图
 *
 * @author
 * @email
 * @date 2025-02-15 13:47:52
 */
public interface ConfigMapper extends BaseMapper<ConfigEntity> {

    List<ConfigDTO> selectListView(@Param("ew") Wrapper<ConfigEntity> wrapper);

    List<ConfigDTO> selectListView(Pagination page, @Param("ew") Wrapper<ConfigEntity> wrapper);

    ConfigDTO selectView(@Param("ew") Wrapper<ConfigEntity> wrapper);


}
