package com.mybatisplusdemo.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mybatisplusdemo.mapper.UsersMapper;
import com.mybatisplusdemo.model.domain.UsersEntity;
import com.mybatisplusdemo.model.dto.UsersDTO;
import com.mybatisplusdemo.service.UsersService;
import com.mybatisplusdemo.common.utils.PageUtils;
import com.mybatisplusdemo.common.utils.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("usersService")
public class UsersServiceImpl extends ServiceImpl<UsersMapper, UsersEntity> implements UsersService {


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<UsersEntity> page = this.selectPage(
                new Query<UsersEntity>(params).getPage(),
                new EntityWrapper<UsersEntity>()
        );
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Wrapper<UsersEntity> wrapper) {
        Page<UsersDTO> page = new Query<UsersDTO>(params).getPage();
        page.setRecords(baseMapper.selectListView(page, wrapper));
        PageUtils pageUtil = new PageUtils(page);
        return pageUtil;
    }

    @Override
    public List<UsersDTO> selectListView(Wrapper<UsersEntity> wrapper) {
        return baseMapper.selectListView(wrapper);
    }

    @Override
    public UsersDTO selectView(Wrapper<UsersEntity> wrapper) {
        return baseMapper.selectView(wrapper);
    }


}
