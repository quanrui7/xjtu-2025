package com.mybatisplusdemo.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mybatisplusdemo.mapper.ConfigMapper;
import com.mybatisplusdemo.model.domain.ConfigEntity;
import com.mybatisplusdemo.model.dto.ConfigDTO;
import com.mybatisplusdemo.service.ConfigService;
import com.mybatisplusdemo.common.utils.PageUtils;
import com.mybatisplusdemo.common.utils.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("configService")
public class ConfigServiceImpl extends ServiceImpl<ConfigMapper, ConfigEntity> implements ConfigService {


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ConfigEntity> page = this.selectPage(
                new Query<ConfigEntity>(params).getPage(),
                new EntityWrapper<ConfigEntity>()
        );
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Wrapper<ConfigEntity> wrapper) {
        Page<ConfigDTO> page = new Query<ConfigDTO>(params).getPage();
        page.setRecords(baseMapper.selectListView(page, wrapper));
        PageUtils pageUtil = new PageUtils(page);
        return pageUtil;
    }

    @Override
    public List<ConfigDTO> selectListView(Wrapper<ConfigEntity> wrapper) {
        return baseMapper.selectListView(wrapper);
    }

    @Override
    public ConfigDTO selectView(Wrapper<ConfigEntity> wrapper) {
        return baseMapper.selectView(wrapper);
    }


}
