package com.mybatisplusdemo.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.mybatisplusdemo.model.domain.MenuEntity;
import com.mybatisplusdemo.model.dto.MenuDTO;
import com.mybatisplusdemo.common.utils.PageUtils;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * 菜单
 *
 * @author
 * @email
 * @date 2025-02-15 13:47:52
 */
public interface MenuService extends IService<MenuEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<MenuDTO> selectListView(Wrapper<MenuEntity> wrapper);

    MenuDTO selectView(@Param("ew") Wrapper<MenuEntity> wrapper);

    PageUtils queryPage(Map<String, Object> params, Wrapper<MenuEntity> wrapper);


}

