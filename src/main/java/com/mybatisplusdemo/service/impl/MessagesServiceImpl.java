package com.mybatisplusdemo.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mybatisplusdemo.mapper.MessagesMapper;
import com.mybatisplusdemo.model.domain.MessagesEntity;
import com.mybatisplusdemo.model.dto.MessagesDTO;
import com.mybatisplusdemo.service.MessagesService;
import com.mybatisplusdemo.common.utils.PageUtils;
import com.mybatisplusdemo.common.utils.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("messagesService")
public class MessagesServiceImpl extends ServiceImpl<MessagesMapper, MessagesEntity> implements MessagesService {


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<MessagesEntity> page = this.selectPage(
                new Query<MessagesEntity>(params).getPage(),
                new EntityWrapper<MessagesEntity>()
        );
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Wrapper<MessagesEntity> wrapper) {
        Page<MessagesDTO> page = new Query<MessagesDTO>(params).getPage();
        page.setRecords(baseMapper.selectListView(page, wrapper));
        PageUtils pageUtil = new PageUtils(page);
        return pageUtil;
    }

    @Override
    public List<MessagesDTO> selectListView(Wrapper<MessagesEntity> wrapper) {
        return baseMapper.selectListView(wrapper);
    }

    @Override
    public MessagesDTO selectView(Wrapper<MessagesEntity> wrapper) {
        return baseMapper.selectView(wrapper);
    }


}
