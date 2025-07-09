package com.mybatisplusdemo.web.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.mybatisplusdemo.assistant.IgnoreAuth;
import com.mybatisplusdemo.model.domain.CartEntity;
import com.mybatisplusdemo.model.dto.CartDTO;
import com.mybatisplusdemo.service.CartService;
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
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;


    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public Return page(@RequestParam Map<String, Object> params, CartEntity cart,
                       HttpServletRequest request) {
        if (!request.getSession().getAttribute("role").toString().equals("管理员")) {
            cart.setUserid((Long) request.getSession().getAttribute("userId"));
        }
        EntityWrapper<CartEntity> ew = new EntityWrapper<CartEntity>();


        PageUtils page = cartService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, cart), params), params));
        return Return.ok().put("data", page);
    }


    /**
     * 前端列表
     */
    @IgnoreAuth
    @RequestMapping("/list")
    public Return list(@RequestParam Map<String, Object> params, CartEntity cart,
                       HttpServletRequest request) {
        EntityWrapper<CartEntity> ew = new EntityWrapper<CartEntity>();

        PageUtils page = cartService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, cart), params), params));
        return Return.ok().put("data", page);
    }

    /**
     * 列表
     */
    @RequestMapping("/lists")
    public Return list(CartEntity cart) {
        EntityWrapper<CartEntity> ew = new EntityWrapper<CartEntity>();
        ew.allEq(MPUtil.allEQMapPre(cart, "cart"));
        return Return.ok().put("data", cartService.selectListView(ew));
    }

    /**
     * 查询
     */
    @RequestMapping("/query")
    public Return query(CartEntity cart) {
        EntityWrapper<CartEntity> ew = new EntityWrapper<CartEntity>();
        ew.allEq(MPUtil.allEQMapPre(cart, "cart"));
        CartDTO cartView = cartService.selectView(ew);
        return Return.ok("查询购物车成功").put("data", cartView);
    }

    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public Return info(@PathVariable("id") Long id) {
        CartEntity cart = cartService.selectById(id);
        cart = cartService.selectView(new EntityWrapper<CartEntity>().eq("id", id));
        return Return.ok().put("data", cart);
    }

    /**
     * 前端详情
     */
    @IgnoreAuth
    @RequestMapping("/detail/{id}")
    public Return detail(@PathVariable("id") Long id) {
        CartEntity cart = cartService.selectById(id);
        cart = cartService.selectView(new EntityWrapper<CartEntity>().eq("id", id));
        return Return.ok().put("data", cart);
    }


    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public Return save(@RequestBody CartEntity cart, HttpServletRequest request) {
        //ValidatorUtils.validateEntity(cart);
        cart.setUserid((Long) request.getSession().getAttribute("userId"));
        cartService.insert(cart);
        return Return.ok();
    }

    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public Return add(@RequestBody CartEntity cart, HttpServletRequest request) {
        //ValidatorUtils.validateEntity(cart);
        cartService.insert(cart);
        return Return.ok();
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public Return update(@RequestBody CartEntity cart, HttpServletRequest request) {
        //ValidatorUtils.validateEntity(cart);
        cartService.updateById(cart);//全部更新
        return Return.ok();
    }


    /**
     * 删除
     */
    @RequestMapping("/delete")
    public Return delete(@RequestBody Long[] ids) {
        cartService.deleteBatchIds(Arrays.asList(ids));
        return Return.ok();
    }


}
