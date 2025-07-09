package com.mybatisplusdemo.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.mybatisplusdemo.model.domain.AddressEntity;
import com.mybatisplusdemo.model.dto.AddressDTO;
import com.mybatisplusdemo.common.utils.PageUtils;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


public interface AddressService extends IService<AddressEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<AddressDTO> selectListView(Wrapper<AddressEntity> wrapper);

    AddressDTO selectView(@Param("ew") Wrapper<AddressEntity> wrapper);

    PageUtils queryPage(Map<String, Object> params, Wrapper<AddressEntity> wrapper);


}

