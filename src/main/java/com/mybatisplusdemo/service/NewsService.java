package com.mybatisplusdemo.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.mybatisplusdemo.model.domain.NewsEntity;
import com.mybatisplusdemo.model.dto.NewsDTO;
import com.mybatisplusdemo.common.utils.PageUtils;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * 新品资讯
 *
 * @author
 * @email
 * @date 2025-02-15 13:47:52
 */
public interface NewsService extends IService<NewsEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<NewsDTO> selectListView(Wrapper<NewsEntity> wrapper);

    NewsDTO selectView(@Param("ew") Wrapper<NewsEntity> wrapper);

    PageUtils queryPage(Map<String, Object> params, Wrapper<NewsEntity> wrapper);


}

