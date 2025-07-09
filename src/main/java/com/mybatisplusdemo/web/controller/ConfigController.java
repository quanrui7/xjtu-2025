package com.mybatisplusdemo.web.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.mybatisplusdemo.assistant.IgnoreAuth;
import com.mybatisplusdemo.model.domain.ConfigEntity;
import com.mybatisplusdemo.model.dto.ConfigDTO;
import com.mybatisplusdemo.service.ConfigService;
import com.mybatisplusdemo.common.utils.MPUtil;
import com.mybatisplusdemo.common.utils.PageUtils;
import com.mybatisplusdemo.common.utils.Return;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Map;


@RestController
@RequestMapping("/config")
public class ConfigController {
    @Autowired
    private ConfigService configService;


    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public Return page(@RequestParam Map<String, Object> params, ConfigEntity config,
                       HttpServletRequest request) {
        EntityWrapper<ConfigEntity> ew = new EntityWrapper<ConfigEntity>();


        PageUtils page = configService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, config), params), params));
        return Return.ok().put("data", page);
    }


    /**
     * 前端列表
     */
    @IgnoreAuth
    @RequestMapping("/list")
    public Return list(@RequestParam Map<String, Object> params, ConfigEntity config,
                       HttpServletRequest request) {
        EntityWrapper<ConfigEntity> ew = new EntityWrapper<ConfigEntity>();

        PageUtils page = configService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, config), params), params));
        return Return.ok().put("data", page);
    }

    /**
     * 列表
     */
    @RequestMapping("/lists")
    public Return list(ConfigEntity config) {
        EntityWrapper<ConfigEntity> ew = new EntityWrapper<ConfigEntity>();
        ew.allEq(MPUtil.allEQMapPre(config, "config"));
        return Return.ok().put("data", configService.selectListView(ew));
    }

    /**
     * 查询
     */
    @RequestMapping("/query")
    public Return query(ConfigEntity config) {
        EntityWrapper<ConfigEntity> ew = new EntityWrapper<ConfigEntity>();
        ew.allEq(MPUtil.allEQMapPre(config, "config"));
        ConfigDTO configView = configService.selectView(ew);
        return Return.ok("查询轮播图成功").put("data", configView);
    }

    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public Return info(@PathVariable("id") Long id) {
        ConfigEntity config = configService.selectById(id);
        config = configService.selectView(new EntityWrapper<ConfigEntity>().eq("id", id));
        return Return.ok().put("data", config);
    }

    /**
     * 前端详情
     */
    @IgnoreAuth
    @RequestMapping("/detail/{id}")
    public Return detail(@PathVariable("id") Long id) {
        ConfigEntity config = configService.selectById(id);
        config = configService.selectView(new EntityWrapper<ConfigEntity>().eq("id", id));
        return Return.ok().put("data", config);
    }


    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public Return save(@RequestBody ConfigEntity config, HttpServletRequest request) {
        //ValidatorUtils.validateEntity(config);
        configService.insert(config);
        return Return.ok();
    }

    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public Return add(@RequestBody ConfigEntity config, HttpServletRequest request) {
        //ValidatorUtils.validateEntity(config);
        configService.insert(config);
        return Return.ok();
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public Return update(@RequestBody ConfigEntity config, HttpServletRequest request) {
        //ValidatorUtils.validateEntity(config);
        configService.updateById(config);//全部更新
        return Return.ok();
    }


    /**
     * 删除
     */
    @RequestMapping("/delete")
    public Return delete(@RequestBody Long[] ids) {
        configService.deleteBatchIds(Arrays.asList(ids));
        return Return.ok();
    }


}
