package com.mybatisplusdemo.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mybatisplusdemo.mapper.MenuMapper;
import com.mybatisplusdemo.model.domain.MenuEntity;
import com.mybatisplusdemo.model.dto.MenuDTO;
import com.mybatisplusdemo.service.MenuService;
import com.mybatisplusdemo.common.utils.PageUtils;
import com.mybatisplusdemo.common.utils.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("menuService")
public class MenuServiceImpl extends ServiceImpl<MenuMapper, MenuEntity> implements MenuService {


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<MenuEntity> page = this.selectPage(
                new Query<MenuEntity>(params).getPage(params),
                new EntityWrapper<MenuEntity>()
        );
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Wrapper<MenuEntity> wrapper) {
        Page<MenuDTO> page = new Query<MenuDTO>(params).getPage(params);
        page.setRecords(baseMapper.selectListView(page, wrapper));
        PageUtils pageUtil = new PageUtils(page);
        return pageUtil;
    }

    @Override
    public List<MenuDTO> selectListView(Wrapper<MenuEntity> wrapper) {
        return baseMapper.selectListView(wrapper);
    }

    @Override
    public MenuDTO selectView(Wrapper<MenuEntity> wrapper) {
        return baseMapper.selectView(wrapper);
    }


}
