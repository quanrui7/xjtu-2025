package com.mybatisplusdemo.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.mybatisplusdemo.model.domain.YonghuEntity;
import com.mybatisplusdemo.model.dto.YonghuDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 用户
 *
 * @author
 * @email
 * @date 2025-02-15 13:47:52
 */
public interface YonghuMapper extends BaseMapper<YonghuEntity> {

    List<YonghuDTO> selectListView(@Param("ew") Wrapper<YonghuEntity> wrapper);

    List<YonghuDTO> selectListView(Pagination page, @Param("ew") Wrapper<YonghuEntity> wrapper);

    YonghuDTO selectView(@Param("ew") Wrapper<YonghuEntity> wrapper);


}
