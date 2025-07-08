package com.mybatisplusdemo.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.mybatisplusdemo.model.domain.LingshifenleiEntity;
import com.mybatisplusdemo.model.dto.LingshifenleiDTO;
import com.mybatisplusdemo.common.utils.PageUtils;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * 商品分类
 *
 * @author
 * @email
 * @date 2025-02-15 13:47:52
 */
public interface LingshifenleiService extends IService<LingshifenleiEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<LingshifenleiDTO> selectListView(Wrapper<LingshifenleiEntity> wrapper);

    LingshifenleiDTO selectView(@Param("ew") Wrapper<LingshifenleiEntity> wrapper);

    PageUtils queryPage(Map<String, Object> params, Wrapper<LingshifenleiEntity> wrapper);


}

