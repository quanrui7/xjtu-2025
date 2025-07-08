package com.mybatisplusdemo.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mybatisplusdemo.mapper.CartMapper;
import com.mybatisplusdemo.model.domain.CartEntity;
import com.mybatisplusdemo.model.dto.CartDTO;
import com.mybatisplusdemo.service.CartService;
import com.mybatisplusdemo.common.utils.PageUtils;
import com.mybatisplusdemo.common.utils.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("cartService")
public class CartServiceImpl extends ServiceImpl<CartMapper, CartEntity> implements CartService {


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<CartEntity> page = this.selectPage(
                new Query<CartEntity>(params).getPage(),
                new EntityWrapper<CartEntity>()
        );
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Wrapper<CartEntity> wrapper) {
        Page<CartDTO> page = new Query<CartDTO>(params).getPage();
        page.setRecords(baseMapper.selectListView(page, wrapper));
        PageUtils pageUtil = new PageUtils(page);
        return pageUtil;
    }

    @Override
    public List<CartDTO> selectListView(Wrapper<CartEntity> wrapper) {
        return baseMapper.selectListView(wrapper);
    }

    @Override
    public CartDTO selectView(Wrapper<CartEntity> wrapper) {
        return baseMapper.selectView(wrapper);
    }


}
