package com.mybatisplusdemo.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.mybatisplusdemo.model.domain.AddressEntity;
import com.mybatisplusdemo.model.dto.AddressDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 地址
 *
 * @author
 * @email
 * @date 2025-02-15 13:47:53
 */
public interface AddressMapper extends BaseMapper<AddressEntity> {

    List<AddressDTO> selectListView(@Param("ew") Wrapper<AddressEntity> wrapper);

    List<AddressDTO> selectListView(Pagination page, @Param("ew") Wrapper<AddressEntity> wrapper);

    AddressDTO selectView(@Param("ew") Wrapper<AddressEntity> wrapper);


}
