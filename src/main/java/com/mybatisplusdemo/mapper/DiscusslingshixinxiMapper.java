package com.mybatisplusdemo.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.mybatisplusdemo.model.domain.DiscusslingshixinxiEntity;
import com.mybatisplusdemo.model.dto.DiscusslingshixinxiDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 商品信息评论表
 *
 * @author
 * @email
 * @date 2025-02-15 13:47:53
 */
public interface DiscusslingshixinxiMapper extends BaseMapper<DiscusslingshixinxiEntity> {

    List<DiscusslingshixinxiDTO> selectListView(@Param("ew") Wrapper<DiscusslingshixinxiEntity> wrapper);

    List<DiscusslingshixinxiDTO> selectListView(Pagination page, @Param("ew") Wrapper<DiscusslingshixinxiEntity> wrapper);

    DiscusslingshixinxiDTO selectView(@Param("ew") Wrapper<DiscusslingshixinxiEntity> wrapper);


}
