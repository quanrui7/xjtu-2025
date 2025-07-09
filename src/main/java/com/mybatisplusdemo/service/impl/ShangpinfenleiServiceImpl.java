package com.mybatisplusdemo.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mybatisplusdemo.mapper.ShangpinfenleiMapper;
import com.mybatisplusdemo.model.domain.ShangpinfenleiEntity;
import com.mybatisplusdemo.model.dto.ShangpinfenleiDTO;
import com.mybatisplusdemo.service.ShangpinfenleiService;
import com.mybatisplusdemo.common.utils.PageUtils;
import com.mybatisplusdemo.common.utils.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("lingshifenleiService")
public class ShangpinfenleiServiceImpl extends ServiceImpl<ShangpinfenleiMapper, ShangpinfenleiEntity> implements ShangpinfenleiService {


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ShangpinfenleiEntity> page = this.selectPage(
                new Query<ShangpinfenleiEntity>(params).getPage(params),
                new EntityWrapper<ShangpinfenleiEntity>()
        );
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Wrapper<ShangpinfenleiEntity> wrapper) {
        Page<ShangpinfenleiDTO> page = new Query<ShangpinfenleiDTO>(params).getPage(params);
        page.setRecords(baseMapper.selectListView(page, wrapper));
        PageUtils pageUtil = new PageUtils(page);
        return pageUtil;
    }

    @Override
    public List<ShangpinfenleiDTO> selectListView(Wrapper<ShangpinfenleiEntity> wrapper) {
        return baseMapper.selectListView(wrapper);
    }

    @Override
    public ShangpinfenleiDTO selectView(Wrapper<ShangpinfenleiEntity> wrapper) {
        return baseMapper.selectView(wrapper);
    }


}
