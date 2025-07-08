package com.mybatisplusdemo.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.mybatisplusdemo.model.domain.LingshixinxiEntity;
import com.mybatisplusdemo.model.dto.LingshixinxiDTO;
import com.mybatisplusdemo.common.utils.PageUtils;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * 零食信息
 *
 * @author
 * @email
 * @date 2025-02-15 13:47:52
 */
public interface LingshixinxiService extends IService<LingshixinxiEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<LingshixinxiDTO> selectListView(Wrapper<LingshixinxiEntity> wrapper);

    LingshixinxiDTO selectView(@Param("ew") Wrapper<LingshixinxiEntity> wrapper);

    PageUtils queryPage(Map<String, Object> params, Wrapper<LingshixinxiEntity> wrapper);


}

