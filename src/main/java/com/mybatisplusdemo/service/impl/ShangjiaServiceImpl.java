package com.mybatisplusdemo.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mybatisplusdemo.mapper.ShangjiaMapper;
import com.mybatisplusdemo.model.domain.ShangjiaEntity;
import com.mybatisplusdemo.model.dto.ShangjiaDTO;
import com.mybatisplusdemo.service.ShangjiaService;
import com.mybatisplusdemo.common.utils.PageUtils;
import com.mybatisplusdemo.common.utils.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("shangjiaService")
public class ShangjiaServiceImpl extends ServiceImpl<ShangjiaMapper, ShangjiaEntity> implements ShangjiaService {


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ShangjiaEntity> page = this.selectPage(
                new Query<ShangjiaEntity>(params).getPage(),
                new EntityWrapper<ShangjiaEntity>()
        );
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Wrapper<ShangjiaEntity> wrapper) {
        Page<ShangjiaDTO> page = new Query<ShangjiaDTO>(params).getPage();
        page.setRecords(baseMapper.selectListView(page, wrapper));
        PageUtils pageUtil = new PageUtils(page);
        return pageUtil;
    }

    @Override
    public List<ShangjiaDTO> selectListView(Wrapper<ShangjiaEntity> wrapper) {
        return baseMapper.selectListView(wrapper);
    }

    @Override
    public ShangjiaDTO selectView(Wrapper<ShangjiaEntity> wrapper) {
        return baseMapper.selectView(wrapper);
    }


}
