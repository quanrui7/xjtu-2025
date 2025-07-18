package com.mybatisplusdemo.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.mybatisplusdemo.model.domain.TokenEntity;
import com.mybatisplusdemo.common.utils.PageUtils;

import java.util.List;
import java.util.Map;


public interface TokenService extends IService<TokenEntity> {
    PageUtils queryPage(Map<String, Object> params);

    List<TokenEntity> selectListView(Wrapper<TokenEntity> wrapper);

    PageUtils queryPage(Map<String, Object> params, Wrapper<TokenEntity> wrapper);

    String generateToken(Long userid, String username, String tableName, String role);

    TokenEntity getTokenEntity(String token);
}
