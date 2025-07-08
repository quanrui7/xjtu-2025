package com.mybatisplusdemo.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mybatisplusdemo.mapper.StoreupMapper;
import com.mybatisplusdemo.model.domain.StoreupEntity;
import com.mybatisplusdemo.model.dto.StoreupDTO;
import com.mybatisplusdemo.service.StoreupService;
import com.mybatisplusdemo.common.utils.PageUtils;
import com.mybatisplusdemo.common.utils.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("storeupService")
public class StoreupServiceImpl extends ServiceImpl<StoreupMapper, StoreupEntity> implements StoreupService {


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<StoreupEntity> page = this.selectPage(
                new Query<StoreupEntity>(params).getPage(),
                new EntityWrapper<StoreupEntity>()
        );
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Wrapper<StoreupEntity> wrapper) {
        Page<StoreupDTO> page = new Query<StoreupDTO>(params).getPage();
        page.setRecords(baseMapper.selectListView(page, wrapper));
        PageUtils pageUtil = new PageUtils(page);
        return pageUtil;
    }

    @Override
    public List<StoreupDTO> selectListView(Wrapper<StoreupEntity> wrapper) {
        return baseMapper.selectListView(wrapper);
    }

    @Override
    public StoreupDTO selectView(Wrapper<StoreupEntity> wrapper) {
        return baseMapper.selectView(wrapper);
    }


}
