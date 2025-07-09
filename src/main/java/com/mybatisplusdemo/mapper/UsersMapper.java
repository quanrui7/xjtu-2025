package com.mybatisplusdemo.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.mybatisplusdemo.model.domain.UsersEntity;
import com.mybatisplusdemo.model.dto.UsersDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;


//管理员
public interface UsersMapper extends BaseMapper<UsersEntity> {

    List<UsersDTO> selectListView(@Param("ew") Wrapper<UsersEntity> wrapper);

    List<UsersDTO> selectListView(Pagination page, @Param("ew") Wrapper<UsersEntity> wrapper);

    UsersDTO selectView(@Param("ew") Wrapper<UsersEntity> wrapper);


}
