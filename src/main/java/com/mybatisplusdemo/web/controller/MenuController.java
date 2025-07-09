package com.mybatisplusdemo.web.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.mybatisplusdemo.assistant.IgnoreAuth;
import com.mybatisplusdemo.model.domain.MenuEntity;
import com.mybatisplusdemo.model.dto.MenuDTO;
import com.mybatisplusdemo.service.MenuService;
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
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;


    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public Return page(@RequestParam Map<String, Object> params, MenuEntity menu,
                       HttpServletRequest request) {
        EntityWrapper<MenuEntity> ew = new EntityWrapper<MenuEntity>();


        PageUtils page = menuService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, menu), params), params));
        return Return.ok().put("data", page);
    }


    /**
     * 前端列表
     */
    @IgnoreAuth
    @RequestMapping("/list")
    public Return list(@RequestParam Map<String, Object> params, MenuEntity menu,
                       HttpServletRequest request) {
        EntityWrapper<MenuEntity> ew = new EntityWrapper<MenuEntity>();

        PageUtils page = menuService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, menu), params), params));
        return Return.ok().put("data", page);
    }

    /**
     * 列表
     */
    @RequestMapping("/lists")
    public Return list(MenuEntity menu) {
        EntityWrapper<MenuEntity> ew = new EntityWrapper<MenuEntity>();
        ew.allEq(MPUtil.allEQMapPre(menu, "menu"));
        return Return.ok().put("data", menuService.selectListView(ew));
    }

    /**
     * 查询
     */
    @RequestMapping("/query")
    public Return query(MenuEntity menu) {
        EntityWrapper<MenuEntity> ew = new EntityWrapper<MenuEntity>();
        ew.allEq(MPUtil.allEQMapPre(menu, "menu"));
        MenuDTO menuView = menuService.selectView(ew);
        return Return.ok("查询菜单成功").put("data", menuView);
    }

    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public Return info(@PathVariable("id") Long id) {
        MenuEntity menu = menuService.selectById(id);
        menu = menuService.selectView(new EntityWrapper<MenuEntity>().eq("id", id));
        return Return.ok().put("data", menu);
    }

    /**
     * 前端详情
     */
    @IgnoreAuth
    @RequestMapping("/detail/{id}")
    public Return detail(@PathVariable("id") Long id) {
        MenuEntity menu = menuService.selectById(id);
        menu = menuService.selectView(new EntityWrapper<MenuEntity>().eq("id", id));
        return Return.ok().put("data", menu);
    }


    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public Return save(@RequestBody MenuEntity menu, HttpServletRequest request) {
        //ValidatorUtils.validateEntity(menu);
        menuService.insert(menu);
        return Return.ok();
    }

    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public Return add(@RequestBody MenuEntity menu, HttpServletRequest request) {
        //ValidatorUtils.validateEntity(menu);
        menuService.insert(menu);
        return Return.ok();
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public Return update(@RequestBody MenuEntity menu, HttpServletRequest request) {
        //ValidatorUtils.validateEntity(menu);
        menuService.updateById(menu);//全部更新
        return Return.ok();
    }


    /**
     * 删除
     */
    @RequestMapping("/delete")
    public Return delete(@RequestBody Long[] ids) {
        menuService.deleteBatchIds(Arrays.asList(ids));
        return Return.ok();
    }


}
