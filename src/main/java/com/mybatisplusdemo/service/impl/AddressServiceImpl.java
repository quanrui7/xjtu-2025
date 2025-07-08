package com.mybatisplusdemo.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mybatisplusdemo.mapper.AddressMapper;
import com.mybatisplusdemo.model.domain.AddressEntity;
import com.mybatisplusdemo.model.dto.AddressDTO;
import com.mybatisplusdemo.service.AddressService;
import com.mybatisplusdemo.common.utils.PageUtils;
import com.mybatisplusdemo.common.utils.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("addressService")
public class AddressServiceImpl extends ServiceImpl<AddressMapper, AddressEntity> implements AddressService {


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<AddressEntity> page = this.selectPage(
                new Query<AddressEntity>(params).getPage(),
                new EntityWrapper<AddressEntity>()
        );
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Wrapper<AddressEntity> wrapper) {
        Page<AddressDTO> page = new Query<AddressDTO>(params).getPage();
        page.setRecords(baseMapper.selectListView(page, wrapper));
        PageUtils pageUtil = new PageUtils(page);
        return pageUtil;
    }

    @Override
    public List<AddressDTO> selectListView(Wrapper<AddressEntity> wrapper) {
        return baseMapper.selectListView(wrapper);
    }

    @Override
    public AddressDTO selectView(Wrapper<AddressEntity> wrapper) {
        return baseMapper.selectView(wrapper);
    }


}
