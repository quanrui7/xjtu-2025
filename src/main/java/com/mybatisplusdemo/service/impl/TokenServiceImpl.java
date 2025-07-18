package com.mybatisplusdemo.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mybatisplusdemo.mapper.TokenMapper;
import com.mybatisplusdemo.model.domain.TokenEntity;
import com.mybatisplusdemo.service.TokenService;
import com.mybatisplusdemo.common.utils.CommonUtil;
import com.mybatisplusdemo.common.utils.PageUtils;
import com.mybatisplusdemo.common.utils.Query;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * token
 */
@Service("tokenService")
public class TokenServiceImpl extends ServiceImpl<TokenMapper, TokenEntity> implements TokenService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<TokenEntity> page = this.selectPage(
                new Query<TokenEntity>(params).getPage(params),
                new EntityWrapper<TokenEntity>()
        );
        return new PageUtils(page);
    }

    @Override
    public List<TokenEntity> selectListView(Wrapper<TokenEntity> wrapper) {
        return baseMapper.selectListView(wrapper);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params,
                               Wrapper<TokenEntity> wrapper) {
        Page<TokenEntity> page = new Query<TokenEntity>(params).getPage(params);
        page.setRecords(baseMapper.selectListView(page, wrapper));
        PageUtils pageUtil = new PageUtils(page);
        return pageUtil;
    }

    @Override
    public String generateToken(Long userid, String username, String tableName, String role) {
        TokenEntity tokenEntity = this.selectOne(new EntityWrapper<TokenEntity>().eq("userid", userid).eq("role", role));
        String token = CommonUtil.getRandomString(32);
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.HOUR_OF_DAY, 1);
        if (tokenEntity != null) {
            tokenEntity.setToken(token);
            tokenEntity.setExpiratedtime(cal.getTime());
            this.updateById(tokenEntity);
        } else {
            this.insert(new TokenEntity(userid, username, tableName, role, token, cal.getTime()));
        }
        return token;
    }

    @Override
    public TokenEntity getTokenEntity(String token) {
        TokenEntity tokenEntity = this.selectOne(new EntityWrapper<TokenEntity>().eq("token", token));
        if (tokenEntity == null || tokenEntity.getExpiratedtime().getTime() < new Date().getTime()) {
            return null;
        }
        return tokenEntity;
    }
}
