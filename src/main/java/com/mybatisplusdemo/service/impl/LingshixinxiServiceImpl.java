package com.mybatisplusdemo.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mybatisplusdemo.mapper.LingshixinxiMapper;
import com.mybatisplusdemo.model.domain.LingshixinxiEntity;
import com.mybatisplusdemo.model.dto.LingshixinxiDTO;
import com.mybatisplusdemo.service.LingshixinxiService;
import com.mybatisplusdemo.common.utils.PageUtils;
import com.mybatisplusdemo.common.utils.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("lingshixinxiService")
public class LingshixinxiServiceImpl extends ServiceImpl<LingshixinxiMapper, LingshixinxiEntity> implements LingshixinxiService {


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<LingshixinxiEntity> page = this.selectPage(
                new Query<LingshixinxiEntity>(params).getPage(),
                new EntityWrapper<LingshixinxiEntity>()
        );
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Wrapper<LingshixinxiEntity> wrapper) {
        Page<LingshixinxiDTO> page = new Query<LingshixinxiDTO>(params).getPage();
        page.setRecords(baseMapper.selectListView(page, wrapper));
        PageUtils pageUtil = new PageUtils(page);
        return pageUtil;
    }

    @Override
    public List<LingshixinxiDTO> selectListView(Wrapper<LingshixinxiEntity> wrapper) {
        return baseMapper.selectListView(wrapper);
    }

    @Override
    public LingshixinxiDTO selectView(Wrapper<LingshixinxiEntity> wrapper) {
        return baseMapper.selectView(wrapper);
    }


}
