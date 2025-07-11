package com.mybatisplusdemo.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.mybatisplusdemo.model.domain.DiscusslingshixinxiEntity;
import com.mybatisplusdemo.model.dto.DiscusslingshixinxiDTO;
import com.mybatisplusdemo.common.utils.PageUtils;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;



public interface DiscusslingshixinxiService extends IService<DiscusslingshixinxiEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<DiscusslingshixinxiDTO> selectListView(Wrapper<DiscusslingshixinxiEntity> wrapper);

    DiscusslingshixinxiDTO selectView(@Param("ew") Wrapper<DiscusslingshixinxiEntity> wrapper);

    PageUtils queryPage(Map<String, Object> params, Wrapper<DiscusslingshixinxiEntity> wrapper);


}

