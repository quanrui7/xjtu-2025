package com.mybatisplusdemo.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.mybatisplusdemo.model.domain.ShangpinxinxiEntity;
import com.mybatisplusdemo.model.dto.ShangpinxinxiDTO;
import com.mybatisplusdemo.common.utils.PageUtils;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


public interface ShangpinxinxiService extends IService<ShangpinxinxiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    PageUtils autoSort(Map<String,Object> params);

    List<ShangpinxinxiDTO> selectListView(Wrapper<ShangpinxinxiEntity> wrapper);

    ShangpinxinxiDTO selectView(@Param("ew") Wrapper<ShangpinxinxiEntity> wrapper);

    PageUtils queryPage(Map<String, Object> params, Wrapper<ShangpinxinxiEntity> wrapper);


}

