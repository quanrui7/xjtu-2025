package com.mybatisplusdemo.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.mybatisplusdemo.model.domain.ShangpinfenleiEntity;
import com.mybatisplusdemo.model.dto.ShangpinfenleiDTO;
import com.mybatisplusdemo.common.utils.PageUtils;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


public interface ShangpinfenleiService extends IService<ShangpinfenleiEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<ShangpinfenleiDTO> selectListView(Wrapper<ShangpinfenleiEntity> wrapper);

    ShangpinfenleiDTO selectView(@Param("ew") Wrapper<ShangpinfenleiEntity> wrapper);

    PageUtils queryPage(Map<String, Object> params, Wrapper<ShangpinfenleiEntity> wrapper);


}

