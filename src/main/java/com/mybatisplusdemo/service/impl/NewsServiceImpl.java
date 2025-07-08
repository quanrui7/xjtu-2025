package com.mybatisplusdemo.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mybatisplusdemo.mapper.NewsMapper;
import com.mybatisplusdemo.model.domain.NewsEntity;
import com.mybatisplusdemo.model.dto.NewsDTO;
import com.mybatisplusdemo.service.NewsService;
import com.mybatisplusdemo.common.utils.PageUtils;
import com.mybatisplusdemo.common.utils.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("newsService")
public class NewsServiceImpl extends ServiceImpl<NewsMapper, NewsEntity> implements NewsService {


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<NewsEntity> page = this.selectPage(
                new Query<NewsEntity>(params).getPage(params),
                new EntityWrapper<NewsEntity>()
        );
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Wrapper<NewsEntity> wrapper) {
        Page<NewsDTO> page = new Query<NewsDTO>(params).getPage(params);
        page.setRecords(baseMapper.selectListView(page, wrapper));
        PageUtils pageUtil = new PageUtils(page);
        return pageUtil;
    }

    @Override
    public List<NewsDTO> selectListView(Wrapper<NewsEntity> wrapper) {
        return baseMapper.selectListView(wrapper);
    }

    @Override
    public NewsDTO selectView(Wrapper<NewsEntity> wrapper) {
        return baseMapper.selectView(wrapper);
    }


}
