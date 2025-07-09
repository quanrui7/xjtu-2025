package com.mybatisplusdemo.web.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.mybatisplusdemo.assistant.IgnoreAuth;
import com.mybatisplusdemo.model.domain.ShangpinfenleiEntity;
import com.mybatisplusdemo.model.dto.ShangpinfenleiDTO;
import com.mybatisplusdemo.service.ShangpinfenleiService;
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
@RequestMapping("/lingshifenlei")
public class ShangpinfenleiController {
    @Autowired
    private ShangpinfenleiService shangpinfenleiService;


    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public Return page(@RequestParam Map<String, Object> params, ShangpinfenleiEntity lingshifenlei,
                       HttpServletRequest request) {
        EntityWrapper<ShangpinfenleiEntity> ew = new EntityWrapper<ShangpinfenleiEntity>();


        PageUtils page = shangpinfenleiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, lingshifenlei), params), params));
        return Return.ok().put("data", page);
    }


    /**
     * 前端列表
     */
    @IgnoreAuth
    @RequestMapping("/list")
    public Return list(@RequestParam Map<String, Object> params, ShangpinfenleiEntity lingshifenlei,
                       HttpServletRequest request) {
        EntityWrapper<ShangpinfenleiEntity> ew = new EntityWrapper<ShangpinfenleiEntity>();

        PageUtils page = shangpinfenleiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, lingshifenlei), params), params));
        return Return.ok().put("data", page);
    }

    /**
     * 列表
     */
    @RequestMapping("/lists")
    public Return list(ShangpinfenleiEntity lingshifenlei) {
        EntityWrapper<ShangpinfenleiEntity> ew = new EntityWrapper<ShangpinfenleiEntity>();
        ew.allEq(MPUtil.allEQMapPre(lingshifenlei, "lingshifenlei"));
        return Return.ok().put("data", shangpinfenleiService.selectListView(ew));
    }

    /**
     * 查询
     */
    @RequestMapping("/query")
    public Return query(ShangpinfenleiEntity lingshifenlei) {
        EntityWrapper<ShangpinfenleiEntity> ew = new EntityWrapper<ShangpinfenleiEntity>();
        ew.allEq(MPUtil.allEQMapPre(lingshifenlei, "lingshifenlei"));
        ShangpinfenleiDTO lingshifenleiView = shangpinfenleiService.selectView(ew);
        return Return.ok("查询商品分类成功").put("data", lingshifenleiView);
    }

    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public Return info(@PathVariable("id") Long id) {
        ShangpinfenleiEntity lingshifenlei = shangpinfenleiService.selectById(id);
        lingshifenlei = shangpinfenleiService.selectView(new EntityWrapper<ShangpinfenleiEntity>().eq("id", id));
        return Return.ok().put("data", lingshifenlei);
    }

    /**
     * 前端详情
     */
    @IgnoreAuth
    @RequestMapping("/detail/{id}")
    public Return detail(@PathVariable("id") Long id) {
        ShangpinfenleiEntity lingshifenlei = shangpinfenleiService.selectById(id);
        lingshifenlei = shangpinfenleiService.selectView(new EntityWrapper<ShangpinfenleiEntity>().eq("id", id));
        return Return.ok().put("data", lingshifenlei);
    }


    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public Return save(@RequestBody ShangpinfenleiEntity lingshifenlei, HttpServletRequest request) {
        //ValidatorUtils.validateEntity(lingshifenlei);
        shangpinfenleiService.insert(lingshifenlei);
        return Return.ok();
    }

    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public Return add(@RequestBody ShangpinfenleiEntity lingshifenlei, HttpServletRequest request) {
        //ValidatorUtils.validateEntity(lingshifenlei);
        shangpinfenleiService.insert(lingshifenlei);
        return Return.ok();
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public Return update(@RequestBody ShangpinfenleiEntity lingshifenlei, HttpServletRequest request) {
        //ValidatorUtils.validateEntity(lingshifenlei);
        shangpinfenleiService.updateById(lingshifenlei);//全部更新
        return Return.ok();
    }


    /**
     * 删除
     */
    @RequestMapping("/delete")
    public Return delete(@RequestBody Long[] ids) {
        shangpinfenleiService.deleteBatchIds(Arrays.asList(ids));
        return Return.ok();
    }


}
