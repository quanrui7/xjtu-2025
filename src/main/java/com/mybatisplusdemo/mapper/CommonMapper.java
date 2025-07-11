package com.mybatisplusdemo.mapper;

import java.util.List;
import java.util.Map;

//通用接口
public interface CommonMapper {
    List<String> getOption(Map<String, Object> params);

    Map<String, Object> getFollowByOption(Map<String, Object> params);

    List<String> getFollowByOption2(Map<String, Object> params);

    void sh(Map<String, Object> params);

    int remindCount(Map<String, Object> params);

    Map<String, Object> selectCal(Map<String, Object> params);

    List<Map<String, Object>> selectGroup(Map<String, Object> params);

    List<Map<String, Object>> selectValue(Map<String, Object> params);

    List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params);
}
