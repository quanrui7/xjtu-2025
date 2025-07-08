package com.mybatisplusdemo.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mybatisplusdemo.mapper.DiscusslingshixinxiMapper;
import com.mybatisplusdemo.model.domain.DiscusslingshixinxiEntity;
import com.mybatisplusdemo.model.dto.DiscusslingshixinxiDTO;
import com.mybatisplusdemo.service.DiscusslingshixinxiService;
import com.mybatisplusdemo.common.utils.PageUtils;
import com.mybatisplusdemo.common.utils.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("discusslingshixinxiService")
public class DiscusslingshixinxiServiceImpl extends ServiceImpl<DiscusslingshixinxiMapper, DiscusslingshixinxiEntity> implements DiscusslingshixinxiService {


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<DiscusslingshixinxiEntity> page = this.selectPage(
                new Query<DiscusslingshixinxiEntity>(params).getPage(),
                new EntityWrapper<DiscusslingshixinxiEntity>()
        );
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Wrapper<DiscusslingshixinxiEntity> wrapper) {
        Page<DiscusslingshixinxiDTO> page = new Query<DiscusslingshixinxiDTO>(params).getPage();
        page.setRecords(baseMapper.selectListView(page, wrapper));
        PageUtils pageUtil = new PageUtils(page);
        return pageUtil;
    }

    @Override
    public List<DiscusslingshixinxiDTO> selectListView(Wrapper<DiscusslingshixinxiEntity> wrapper) {
        return baseMapper.selectListView(wrapper);
    }

    @Override
    public DiscusslingshixinxiDTO selectView(Wrapper<DiscusslingshixinxiEntity> wrapper) {
        return baseMapper.selectView(wrapper);
    }


}
