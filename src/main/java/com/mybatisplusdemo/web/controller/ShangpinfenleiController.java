package com.mybatisplusdemo.web.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.mybatisplusdemo.assistant.IgnoreAuth;
import com.mybatisplusdemo.model.domain.LingshifenleiEntity;
import com.mybatisplusdemo.model.dto.LingshifenleiDTO;
import com.mybatisplusdemo.service.LingshifenleiService;
import com.mybatisplusdemo.common.utils.MPUtil;
import com.mybatisplusdemo.common.utils.PageUtils;
import com.mybatisplusdemo.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Map;

/**
 * 商品分类
 * 后端接口
 *
 * @author
 * @email
 * @date 2025-02-15 13:47:52
 */
@RestController
@RequestMapping("/lingshifenlei")
public class ShangpinfenleiController {
    @Autowired
    private LingshifenleiService lingshifenleiService;


    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, LingshifenleiEntity lingshifenlei,
                  HttpServletRequest request) {
        EntityWrapper<LingshifenleiEntity> ew = new EntityWrapper<LingshifenleiEntity>();


        PageUtils page = lingshifenleiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, lingshifenlei), params), params));
        return R.ok().put("data", page);
    }


    /**
     * 前端列表
     */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, LingshifenleiEntity lingshifenlei,
                  HttpServletRequest request) {
        EntityWrapper<LingshifenleiEntity> ew = new EntityWrapper<LingshifenleiEntity>();

        PageUtils page = lingshifenleiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, lingshifenlei), params), params));
        return R.ok().put("data", page);
    }

    /**
     * 列表
     */
    @RequestMapping("/lists")
    public R list(LingshifenleiEntity lingshifenlei) {
        EntityWrapper<LingshifenleiEntity> ew = new EntityWrapper<LingshifenleiEntity>();
        ew.allEq(MPUtil.allEQMapPre(lingshifenlei, "lingshifenlei"));
        return R.ok().put("data", lingshifenleiService.selectListView(ew));
    }

    /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(LingshifenleiEntity lingshifenlei) {
        EntityWrapper<LingshifenleiEntity> ew = new EntityWrapper<LingshifenleiEntity>();
        ew.allEq(MPUtil.allEQMapPre(lingshifenlei, "lingshifenlei"));
        LingshifenleiDTO lingshifenleiView = lingshifenleiService.selectView(ew);
        return R.ok("查询商品分类成功").put("data", lingshifenleiView);
    }

    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        LingshifenleiEntity lingshifenlei = lingshifenleiService.selectById(id);
        lingshifenlei = lingshifenleiService.selectView(new EntityWrapper<LingshifenleiEntity>().eq("id", id));
        return R.ok().put("data", lingshifenlei);
    }

    /**
     * 前端详情
     */
    @IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id) {
        LingshifenleiEntity lingshifenlei = lingshifenleiService.selectById(id);
        lingshifenlei = lingshifenleiService.selectView(new EntityWrapper<LingshifenleiEntity>().eq("id", id));
        return R.ok().put("data", lingshifenlei);
    }


    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody LingshifenleiEntity lingshifenlei, HttpServletRequest request) {
        //ValidatorUtils.validateEntity(lingshifenlei);
        lingshifenleiService.insert(lingshifenlei);
        return R.ok();
    }

    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody LingshifenleiEntity lingshifenlei, HttpServletRequest request) {
        //ValidatorUtils.validateEntity(lingshifenlei);
        lingshifenleiService.insert(lingshifenlei);
        return R.ok();
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody LingshifenleiEntity lingshifenlei, HttpServletRequest request) {
        //ValidatorUtils.validateEntity(lingshifenlei);
        lingshifenleiService.updateById(lingshifenlei);//全部更新
        return R.ok();
    }


    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids) {
        lingshifenleiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }


}
