package com.mybatisplusdemo.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.mybatisplusdemo.model.domain.UsersEntity;
import com.mybatisplusdemo.model.dto.UsersDTO;
import com.mybatisplusdemo.common.utils.PageUtils;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * 管理员
 *
 * @author
 * @email
 * @date 2025-02-15 13:47:52
 */
public interface UsersService extends IService<UsersEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<UsersDTO> selectListView(Wrapper<UsersEntity> wrapper);

    UsersDTO selectView(@Param("ew") Wrapper<UsersEntity> wrapper);

    PageUtils queryPage(Map<String, Object> params, Wrapper<UsersEntity> wrapper);


}

