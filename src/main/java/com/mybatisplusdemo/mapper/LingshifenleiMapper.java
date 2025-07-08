package com.mybatisplusdemo.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.mybatisplusdemo.model.domain.LingshifenleiEntity;
import com.mybatisplusdemo.model.dto.LingshifenleiDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 零食分类
 *
 * @author
 * @email
 * @date 2025-02-15 13:47:52
 */
public interface LingshifenleiMapper extends BaseMapper<LingshifenleiEntity> {

    List<LingshifenleiDTO> selectListView(@Param("ew") Wrapper<LingshifenleiEntity> wrapper);

    List<LingshifenleiDTO> selectListView(Pagination page, @Param("ew") Wrapper<LingshifenleiEntity> wrapper);

    LingshifenleiDTO selectView(@Param("ew") Wrapper<LingshifenleiEntity> wrapper);


}
