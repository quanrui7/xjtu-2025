package com.mybatisplusdemo.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.mybatisplusdemo.model.domain.YonghuEntity;
import com.mybatisplusdemo.model.dto.YonghuDTO;
import com.mybatisplusdemo.common.utils.PageUtils;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


public interface YonghuService extends IService<YonghuEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<YonghuDTO> selectListView(Wrapper<YonghuEntity> wrapper);

    YonghuDTO selectView(@Param("ew") Wrapper<YonghuEntity> wrapper);

    PageUtils queryPage(Map<String, Object> params, Wrapper<YonghuEntity> wrapper);


}

