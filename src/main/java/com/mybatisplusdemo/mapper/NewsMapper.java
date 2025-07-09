package com.mybatisplusdemo.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.mybatisplusdemo.model.domain.NewsEntity;
import com.mybatisplusdemo.model.dto.NewsDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;


//新品资讯
public interface NewsMapper extends BaseMapper<NewsEntity> {

    List<NewsDTO> selectListView(@Param("ew") Wrapper<NewsEntity> wrapper);

    List<NewsDTO> selectListView(Pagination page, @Param("ew") Wrapper<NewsEntity> wrapper);

    NewsDTO selectView(@Param("ew") Wrapper<NewsEntity> wrapper);


}
