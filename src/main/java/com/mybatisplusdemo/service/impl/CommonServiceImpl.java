package com.mybatisplusdemo.service.impl;


import com.mybatisplusdemo.mapper.CommonMapper;
import com.mybatisplusdemo.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


//系统用户
@Service("commonService")
public class CommonServiceImpl implements CommonService {

    @Autowired
    private CommonMapper commonDao;

    @Override
    public List<String> getOption(Map<String, Object> params) {
        return commonDao.getOption(params);
    }

    @Override
    public Map<String, Object> getFollowByOption(Map<String, Object> params) {
        return commonDao.getFollowByOption(params);
    }

    @Override
    public void sh(Map<String, Object> params) {
        commonDao.sh(params);
    }

    @Override
    public int remindCount(Map<String, Object> params) {
        return commonDao.remindCount(params);
    }

    @Override
    public Map<String, Object> selectCal(Map<String, Object> params) {
        return commonDao.selectCal(params);
    }

    @Override
    public List<Map<String, Object>> selectGroup(Map<String, Object> params) {
        return commonDao.selectGroup(params);
    }

    @Override
    public List<Map<String, Object>> selectValue(Map<String, Object> params) {
        return commonDao.selectValue(params);
    }

    @Override
    public List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params) {
        return commonDao.selectTimeStatValue(params);
    }

}
