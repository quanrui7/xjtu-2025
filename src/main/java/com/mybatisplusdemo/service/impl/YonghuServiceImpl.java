package com.mybatisplusdemo.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mybatisplusdemo.mapper.YonghuMapper;
import com.mybatisplusdemo.model.domain.YonghuEntity;
import com.mybatisplusdemo.model.dto.YonghuDTO;
import com.mybatisplusdemo.service.YonghuService;
import com.mybatisplusdemo.common.utils.PageUtils;
import com.mybatisplusdemo.common.utils.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("yonghuService")
public class YonghuServiceImpl extends ServiceImpl<YonghuMapper, YonghuEntity> implements YonghuService {


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<YonghuEntity> page = this.selectPage(
                new Query<YonghuEntity>(params).getPage(params),
                new EntityWrapper<YonghuEntity>()
        );
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Wrapper<YonghuEntity> wrapper) {
        Page<YonghuDTO> page = new Query<YonghuDTO>(params).getPage(params);
        page.setRecords(baseMapper.selectListView(page, wrapper));
        PageUtils pageUtil = new PageUtils(page);
        return pageUtil;
    }

    @Override
    public List<YonghuDTO> selectListView(Wrapper<YonghuEntity> wrapper) {
        return baseMapper.selectListView(wrapper);
    }

    @Override
    public YonghuDTO selectView(Wrapper<YonghuEntity> wrapper) {
        return baseMapper.selectView(wrapper);
    }


}
