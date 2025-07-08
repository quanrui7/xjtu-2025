package com.mybatisplusdemo.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.mybatisplusdemo.model.domain.LingshixinxiEntity;
import com.mybatisplusdemo.model.dto.LingshixinxiDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 零食信息
 *
 * @author
 * @email
 * @date 2025-02-15 13:47:52
 */
public interface LingshixinxiMapper extends BaseMapper<LingshixinxiEntity> {

    List<LingshixinxiDTO> selectListView(@Param("ew") Wrapper<LingshixinxiEntity> wrapper);

    List<LingshixinxiDTO> selectListView(Pagination page, @Param("ew") Wrapper<LingshixinxiEntity> wrapper);

    LingshixinxiDTO selectView(@Param("ew") Wrapper<LingshixinxiEntity> wrapper);


}
