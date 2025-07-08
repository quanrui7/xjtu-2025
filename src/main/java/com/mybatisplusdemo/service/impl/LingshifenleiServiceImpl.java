package com.mybatisplusdemo.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mybatisplusdemo.mapper.LingshifenleiMapper;
import com.mybatisplusdemo.model.domain.LingshifenleiEntity;
import com.mybatisplusdemo.model.dto.LingshifenleiDTO;
import com.mybatisplusdemo.service.LingshifenleiService;
import com.mybatisplusdemo.common.utils.PageUtils;
import com.mybatisplusdemo.common.utils.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("lingshifenleiService")
public class LingshifenleiServiceImpl extends ServiceImpl<LingshifenleiMapper, LingshifenleiEntity> implements LingshifenleiService {


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<LingshifenleiEntity> page = this.selectPage(
                new Query<LingshifenleiEntity>(params).getPage(params),
                new EntityWrapper<LingshifenleiEntity>()
        );
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Wrapper<LingshifenleiEntity> wrapper) {
        Page<LingshifenleiDTO> page = new Query<LingshifenleiDTO>(params).getPage(params);
        page.setRecords(baseMapper.selectListView(page, wrapper));
        PageUtils pageUtil = new PageUtils(page);
        return pageUtil;
    }

    @Override
    public List<LingshifenleiDTO> selectListView(Wrapper<LingshifenleiEntity> wrapper) {
        return baseMapper.selectListView(wrapper);
    }

    @Override
    public LingshifenleiDTO selectView(Wrapper<LingshifenleiEntity> wrapper) {
        return baseMapper.selectView(wrapper);
    }


}
