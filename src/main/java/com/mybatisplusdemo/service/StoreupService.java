package com.mybatisplusdemo.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.mybatisplusdemo.model.domain.StoreupEntity;
import com.mybatisplusdemo.model.dto.StoreupDTO;
import com.mybatisplusdemo.common.utils.PageUtils;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface StoreupService extends IService<StoreupEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<StoreupDTO> selectListView(Wrapper<StoreupEntity> wrapper);

    StoreupDTO selectView(@Param("ew") Wrapper<StoreupEntity> wrapper);

    PageUtils queryPage(Map<String, Object> params, Wrapper<StoreupEntity> wrapper);


}

