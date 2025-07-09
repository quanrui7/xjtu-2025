package com.mybatisplusdemo.web.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.mybatisplusdemo.assistant.IgnoreAuth;
import com.mybatisplusdemo.model.domain.StoreupEntity;
import com.mybatisplusdemo.model.dto.StoreupDTO;
import com.mybatisplusdemo.service.StoreupService;
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
@RequestMapping("/storeup")
public class StoreupController {
    @Autowired
    private StoreupService storeupService;


    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public Return page(@RequestParam Map<String, Object> params, StoreupEntity storeup,
                       HttpServletRequest request) {
        if (!request.getSession().getAttribute("role").toString().equals("管理员")) {
            storeup.setUserid((Long) request.getSession().getAttribute("userId"));
        }
        EntityWrapper<StoreupEntity> ew = new EntityWrapper<StoreupEntity>();


        PageUtils page = storeupService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, storeup), params), params));
        return Return.ok().put("data", page);
    }


    /**
     * 前端列表
     */
    @IgnoreAuth
    @RequestMapping("/list")
    public Return list(@RequestParam Map<String, Object> params, StoreupEntity storeup,
                       HttpServletRequest request) {
        EntityWrapper<StoreupEntity> ew = new EntityWrapper<StoreupEntity>();

        PageUtils page = storeupService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, storeup), params), params));
        return Return.ok().put("data", page);
    }

    /**
     * 列表
     */
    @RequestMapping("/lists")
    public Return list(StoreupEntity storeup) {
        EntityWrapper<StoreupEntity> ew = new EntityWrapper<StoreupEntity>();
        ew.allEq(MPUtil.allEQMapPre(storeup, "storeup"));
        return Return.ok().put("data", storeupService.selectListView(ew));
    }

    /**
     * 查询
     */
    @RequestMapping("/query")
    public Return query(StoreupEntity storeup) {
        EntityWrapper<StoreupEntity> ew = new EntityWrapper<StoreupEntity>();
        ew.allEq(MPUtil.allEQMapPre(storeup, "storeup"));
        StoreupDTO storeupView = storeupService.selectView(ew);
        return Return.ok("查询我的收藏成功").put("data", storeupView);
    }

    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public Return info(@PathVariable("id") Long id) {
        StoreupEntity storeup = storeupService.selectById(id);
        storeup = storeupService.selectView(new EntityWrapper<StoreupEntity>().eq("id", id));
        return Return.ok().put("data", storeup);
    }

    /**
     * 前端详情
     */
    @IgnoreAuth
    @RequestMapping("/detail/{id}")
    public Return detail(@PathVariable("id") Long id) {
        StoreupEntity storeup = storeupService.selectById(id);
        storeup = storeupService.selectView(new EntityWrapper<StoreupEntity>().eq("id", id));
        return Return.ok().put("data", storeup);
    }


    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public Return save(@RequestBody StoreupEntity storeup, HttpServletRequest request) {
        //ValidatorUtils.validateEntity(storeup);
        storeup.setUserid((Long) request.getSession().getAttribute("userId"));
        storeupService.insert(storeup);
        return Return.ok();
    }

    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public Return add(@RequestBody StoreupEntity storeup, HttpServletRequest request) {
        //ValidatorUtils.validateEntity(storeup);
        storeupService.insert(storeup);
        return Return.ok();
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public Return update(@RequestBody StoreupEntity storeup, HttpServletRequest request) {
        //ValidatorUtils.validateEntity(storeup);
        storeupService.updateById(storeup);//全部更新
        return Return.ok();
    }


    /**
     * 删除
     */
    @RequestMapping("/delete")
    public Return delete(@RequestBody Long[] ids) {
        storeupService.deleteBatchIds(Arrays.asList(ids));
        return Return.ok();
    }


}
