package com.mybatisplusdemo.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.mybatisplusdemo.model.domain.NewsEntity;
import com.mybatisplusdemo.model.dto.NewsDTO;
import com.mybatisplusdemo.common.utils.PageUtils;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


public interface NewsService extends IService<NewsEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<NewsDTO> selectListView(Wrapper<NewsEntity> wrapper);

    NewsDTO selectView(@Param("ew") Wrapper<NewsEntity> wrapper);

    PageUtils queryPage(Map<String, Object> params, Wrapper<NewsEntity> wrapper);


}

