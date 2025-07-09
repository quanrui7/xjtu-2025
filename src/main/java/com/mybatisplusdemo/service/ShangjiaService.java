package com.mybatisplusdemo.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.mybatisplusdemo.model.domain.ShangjiaEntity;
import com.mybatisplusdemo.model.dto.ShangjiaDTO;
import com.mybatisplusdemo.common.utils.PageUtils;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


public interface ShangjiaService extends IService<ShangjiaEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<ShangjiaDTO> selectListView(Wrapper<ShangjiaEntity> wrapper);

    ShangjiaDTO selectView(@Param("ew") Wrapper<ShangjiaEntity> wrapper);

    PageUtils queryPage(Map<String, Object> params, Wrapper<ShangjiaEntity> wrapper);


}

