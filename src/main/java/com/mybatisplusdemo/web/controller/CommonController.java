package com.mybatisplusdemo.web.controller;

import com.baidu.aip.face.AipFace;
import com.mybatisplusdemo.assistant.IgnoreAuth;
import com.mybatisplusdemo.service.CommonService;
import com.mybatisplusdemo.service.ConfigService;
import com.mybatisplusdemo.common.utils.MPUtil;
import com.mybatisplusdemo.common.utils.Return;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 通用接口
 */
@RestController
public class CommonController {
    private static AipFace client = null;
    @Autowired
    private CommonService commonService;
    @Autowired
    private ConfigService configService;

    /**
     * 获取table表中的column列表(联动接口)
     */
    @IgnoreAuth
    @RequestMapping("/option/{tableName}/{columnName}")
    public Return getOption(@PathVariable("tableName") String tableName, @PathVariable("columnName") String columnName, @RequestParam(required = false) String conditionColumn, @RequestParam(required = false) String conditionValue, String level, String parent) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("table", tableName);
        params.put("column", columnName);
        if (StringUtils.isNotBlank(level)) {
            params.put("level", level);
        }
        if (StringUtils.isNotBlank(parent)) {
            params.put("parent", parent);
        }
        if (StringUtils.isNotBlank(conditionColumn)) {
            params.put("conditionColumn", conditionColumn);
        }
        if (StringUtils.isNotBlank(conditionValue)) {
            params.put("conditionValue", conditionValue);
        }
        List<String> data = commonService.getOption(params);
        return Return.ok().put("data", data);
    }

    /**
     * 根据table中的column获取单条记录
     */
    @IgnoreAuth
    @RequestMapping("/follow/{tableName}/{columnName}")
    public Return getFollowByOption(@PathVariable("tableName") String tableName, @PathVariable("columnName") String columnName, @RequestParam String columnValue) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("table", MPUtil.camelToSnake(tableName));
        params.put("column", MPUtil.camelToSnake(columnName));
        params.put("columnValue", MPUtil.camelToSnake(columnValue));
        Map<String, Object> result = commonService.getFollowByOption(params);
        return Return.ok().put("data", MPUtil.snakeMapToCamel(result));
    }

    /**
     * 修改table表的sfsh状态
     */
    @RequestMapping("/sh/{tableName}")
    public Return sh(@PathVariable("tableName") String tableName, @RequestBody Map<String, Object> map) {
        map.put("table", tableName);
        commonService.sh(map);
        return Return.ok();
    }

    /**
     * 获取需要提醒的记录数
     */
    @IgnoreAuth
    @RequestMapping("/remind/{tableName}/{columnName}/{type}")
    public Return remindCount(@PathVariable("tableName") String tableName, @PathVariable("columnName") String columnName,
                              @PathVariable("type") String type, @RequestParam Map<String, Object> map) {
        map.put("table", tableName);
        map.put("column", columnName);
        map.put("type", type);

        if (type.equals("2")) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar c = Calendar.getInstance();
            Date remindStartDate = null;
            Date remindEndDate = null;
            if (map.get("remindstart") != null) {
                Integer remindStart = Integer.parseInt(map.get("remindstart").toString());
                c.setTime(new Date());
                c.add(Calendar.DAY_OF_MONTH, remindStart);
                remindStartDate = c.getTime();
                map.put("remindstart", sdf.format(remindStartDate));
            }
            if (map.get("remindend") != null) {
                Integer remindEnd = Integer.parseInt(map.get("remindend").toString());
                c.setTime(new Date());
                c.add(Calendar.DAY_OF_MONTH, remindEnd);
                remindEndDate = c.getTime();
                map.put("remindend", sdf.format(remindEndDate));
            }
        }

        int count = commonService.remindCount(map);
        return Return.ok().put("count", count);
    }

    /**
     * 单列求和
     */
    @IgnoreAuth
    @RequestMapping("/cal/{tableName}/{columnName}")
    public Return cal(@PathVariable("tableName") String tableName, @PathVariable("columnName") String columnName) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("table", tableName);
        params.put("column", columnName);
        Map<String, Object> result = commonService.selectCal(params);
        return Return.ok().put("data", result);
    }

    /**
     * 分组统计
     */
    @IgnoreAuth
    @RequestMapping("/group/{tableName}/{columnName}")
    public Return group(@PathVariable("tableName") String tableName, @PathVariable("columnName") String columnName) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("table", tableName);
        params.put("column", columnName);
        List<Map<String, Object>> result = commonService.selectGroup(params);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (Map<String, Object> m : result) {
            for (String k : m.keySet()) {
                if (m.get(k) instanceof Date) {
                    m.put(k, sdf.format((Date) m.get(k)));
                }
            }
        }
        return Return.ok().put("data", result);
    }

    /**
     * （按值统计）
     */
    @IgnoreAuth
    @RequestMapping("/value/{tableName}/{xColumnName}/{yColumnName}")
    public Return value(@PathVariable("tableName") String tableName, @PathVariable("yColumnName") String yColumnName, @PathVariable("xColumnName") String xColumnName) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("table", tableName);
        params.put("xColumn", xColumnName);
        params.put("yColumn", yColumnName);
        List<Map<String, Object>> result = commonService.selectValue(params);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (Map<String, Object> m : result) {
            for (String k : m.keySet()) {
                if (m.get(k) instanceof Date) {
                    m.put(k, sdf.format((Date) m.get(k)));
                }
            }
        }
        return Return.ok().put("data", result);
    }

    /**
     * （按值统计）时间统计类型
     */
    @IgnoreAuth
    @RequestMapping("/value/{tableName}/{xColumnName}/{yColumnName}/{timeStatType}")
    public Return valueDay(@PathVariable("tableName") String tableName, @PathVariable("yColumnName") String yColumnName, @PathVariable("xColumnName") String xColumnName, @PathVariable("timeStatType") String timeStatType) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("table", tableName);
        params.put("xColumn", xColumnName);
        params.put("yColumn", yColumnName);
        params.put("timeStatType", timeStatType);
        List<Map<String, Object>> result = commonService.selectTimeStatValue(params);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (Map<String, Object> m : result) {
            for (String k : m.keySet()) {
                if (m.get(k) instanceof Date) {
                    m.put(k, sdf.format((Date) m.get(k)));
                }
            }
        }
        return Return.ok().put("data", result);
    }


}
