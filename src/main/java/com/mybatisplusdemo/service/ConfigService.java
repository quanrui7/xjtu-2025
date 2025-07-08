package com.mybatisplusdemo.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.mybatisplusdemo.model.domain.ConfigEntity;
import com.mybatisplusdemo.model.dto.ConfigDTO;
import com.mybatisplusdemo.common.utils.PageUtils;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * 轮播图
 *
 * @author
 * @email
 * @date 2025-02-15 13:47:52
 */
public interface ConfigService extends IService<ConfigEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<ConfigDTO> selectListView(Wrapper<ConfigEntity> wrapper);

    ConfigDTO selectView(@Param("ew") Wrapper<ConfigEntity> wrapper);

    PageUtils queryPage(Map<String, Object> params, Wrapper<ConfigEntity> wrapper);


}

