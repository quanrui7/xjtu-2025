package com.mybatisplusdemo.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.mybatisplusdemo.model.domain.MessagesEntity;
import com.mybatisplusdemo.model.dto.MessagesDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 留言板
 *
 * @author
 * @email
 * @date 2025-02-15 13:47:52
 */
public interface MessagesMapper extends BaseMapper<MessagesEntity> {

    List<MessagesDTO> selectListView(@Param("ew") Wrapper<MessagesEntity> wrapper);

    List<MessagesDTO> selectListView(Pagination page, @Param("ew") Wrapper<MessagesEntity> wrapper);

    MessagesDTO selectView(@Param("ew") Wrapper<MessagesEntity> wrapper);


}
