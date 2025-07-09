package com.mybatisplusdemo.web.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.mybatisplusdemo.assistant.IgnoreAuth;
import com.mybatisplusdemo.model.domain.AddressEntity;
import com.mybatisplusdemo.model.dto.AddressDTO;
import com.mybatisplusdemo.service.AddressService;
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
@RequestMapping("/address")
public class AddressController {
    @Autowired
    private AddressService addressService;


    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public Return page(@RequestParam Map<String, Object> params, AddressEntity address,
                       HttpServletRequest request) {
        if (!request.getSession().getAttribute("role").toString().equals("管理员")) {
            address.setUserid((Long) request.getSession().getAttribute("userId"));
        }
        EntityWrapper<AddressEntity> ew = new EntityWrapper<AddressEntity>();


        PageUtils page = addressService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, address), params), params));
        return Return.ok().put("data", page);
    }


    /**
     * 前端列表
     */
    @RequestMapping("/list")
    public Return list(@RequestParam Map<String, Object> params, AddressEntity address,
                       HttpServletRequest request) {
        if (!request.getSession().getAttribute("role").toString().equals("管理员")) {
            address.setUserid((Long) request.getSession().getAttribute("userId"));
        }
        EntityWrapper<AddressEntity> ew = new EntityWrapper<AddressEntity>();

        PageUtils page = addressService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, address), params), params));
        return Return.ok().put("data", page);
    }

    /**
     * 列表
     */
    @RequestMapping("/lists")
    public Return list(AddressEntity address) {
        EntityWrapper<AddressEntity> ew = new EntityWrapper<AddressEntity>();
        ew.allEq(MPUtil.allEQMapPre(address, "address"));
        return Return.ok().put("data", addressService.selectListView(ew));
    }

    /**
     * 查询
     */
    @RequestMapping("/query")
    public Return query(AddressEntity address) {
        EntityWrapper<AddressEntity> ew = new EntityWrapper<AddressEntity>();
        ew.allEq(MPUtil.allEQMapPre(address, "address"));
        AddressDTO addressView = addressService.selectView(ew);
        return Return.ok("查询地址成功").put("data", addressView);
    }

    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public Return info(@PathVariable("id") Long id) {
        AddressEntity address = addressService.selectById(id);
        address = addressService.selectView(new EntityWrapper<AddressEntity>().eq("id", id));
        return Return.ok().put("data", address);
    }

    /**
     * 前端详情
     */
    @IgnoreAuth
    @RequestMapping("/detail/{id}")
    public Return detail(@PathVariable("id") Long id) {
        AddressEntity address = addressService.selectById(id);
        address = addressService.selectView(new EntityWrapper<AddressEntity>().eq("id", id));
        return Return.ok().put("data", address);
    }


    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public Return save(@RequestBody AddressEntity address, HttpServletRequest request) {
        //ValidatorUtils.validateEntity(address);
        address.setUserid((Long) request.getSession().getAttribute("userId"));
        Long userId = (Long) request.getSession().getAttribute("userId");
        if (address.getIsdefault().equals("是")) {
            addressService.updateForSet("isdefault='否'", new EntityWrapper<AddressEntity>().eq("userid", userId));
        }
        address.setUserid(userId);
        addressService.insert(address);
        return Return.ok();
    }

    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public Return add(@RequestBody AddressEntity address, HttpServletRequest request) {
        //ValidatorUtils.validateEntity(address);
        address.setUserid((Long) request.getSession().getAttribute("userId"));
        Long userId = (Long) request.getSession().getAttribute("userId");
        if (address.getIsdefault().equals("是")) {
            addressService.updateForSet("isdefault='否'", new EntityWrapper<AddressEntity>().eq("userid", userId));
        }
        address.setUserid(userId);
        addressService.insert(address);
        return Return.ok();
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public Return update(@RequestBody AddressEntity address, HttpServletRequest request) {
        //ValidatorUtils.validateEntity(address);
        if (address.getIsdefault().equals("是")) {
            addressService.updateForSet("isdefault='否'", new EntityWrapper<AddressEntity>().eq("userid", request.getSession().getAttribute("userId")));
        }
        addressService.updateById(address);//全部更新
        return Return.ok();
    }


    /**
     * 获取默认地址
     */
    @RequestMapping("/default")
    public Return defaultAddress(HttpServletRequest request) {
        Wrapper<AddressEntity> wrapper = new EntityWrapper<AddressEntity>().eq("isdefault", "是").eq("userid", request.getSession().getAttribute("userId"));
        AddressEntity address = addressService.selectOne(wrapper);
        return Return.ok().put("data", address);
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public Return delete(@RequestBody Long[] ids) {
        addressService.deleteBatchIds(Arrays.asList(ids));
        return Return.ok();
    }


}
