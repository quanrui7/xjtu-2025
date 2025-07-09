package com.mybatisplusdemo.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mybatisplusdemo.mapper.ShangpinxinxiMapper;
import com.mybatisplusdemo.model.domain.ShangpinxinxiEntity;
import com.mybatisplusdemo.model.dto.ShangpinxinxiDTO;
import com.mybatisplusdemo.service.ShangpinxinxiService;
import com.mybatisplusdemo.common.utils.PageUtils;
import com.mybatisplusdemo.common.utils.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("lingshixinxiService")
public class ShangpinxinxiServiceImpl extends ServiceImpl<ShangpinxinxiMapper, ShangpinxinxiEntity> implements ShangpinxinxiService {


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ShangpinxinxiEntity> page = this.selectPage(
                new Query<ShangpinxinxiEntity>(params).getPage(params),
                new EntityWrapper<ShangpinxinxiEntity>()
        );
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Wrapper<ShangpinxinxiEntity> wrapper) {
        Page<ShangpinxinxiDTO> page = new Query<ShangpinxinxiDTO>(params).getPage(params);
        page.setRecords(baseMapper.selectListView(page, wrapper));
        PageUtils pageUtil = new PageUtils(page);
        return pageUtil;
    }



    @Override
    public List<ShangpinxinxiDTO> selectListView(Wrapper<ShangpinxinxiEntity> wrapper) {
        return baseMapper.selectListView(wrapper);
    }

    @Override
    public ShangpinxinxiDTO selectView(Wrapper<ShangpinxinxiEntity> wrapper) {
        return baseMapper.selectView(wrapper);
    }


    // 智能排序：点击量高置前，若相同则时间新置前
    @Override
    public PageUtils autoSort(Map<String, Object> params) {
        // 默认每页10条
        params.putIfAbsent("limit", "10");
        params.putIfAbsent("page",  "1");

        EntityWrapper<ShangpinxinxiEntity> ew = new EntityWrapper<>();
        ew.eq("sfsh", "是")
                .orderBy("click_number", false)
                .orderBy("addtime",      false);

        Page<ShangpinxinxiEntity> page = this.selectPage(
                new Query<ShangpinxinxiEntity>().getPage(params), ew);

        return new PageUtils(page);
    }
}
