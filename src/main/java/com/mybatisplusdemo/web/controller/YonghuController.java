package com.mybatisplusdemo.web.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.mybatisplusdemo.assistant.IgnoreAuth;
import com.mybatisplusdemo.model.domain.YonghuEntity;
import com.mybatisplusdemo.model.dto.YonghuDTO;
import com.mybatisplusdemo.service.TokenService;
import com.mybatisplusdemo.service.YonghuService;
import com.mybatisplusdemo.common.utils.MPUtil;
import com.mybatisplusdemo.common.utils.PageUtils;
import com.mybatisplusdemo.common.utils.Return;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;


@RestController
@RequestMapping("/yonghu")
public class YonghuController {
    @Autowired
    private YonghuService yonghuService;


    @Autowired
    private TokenService tokenService;

    /**
     * 登录
     */
    @IgnoreAuth
    @RequestMapping(value = "/login")
    public Return login(String username, String password, String captcha, HttpServletRequest request) {
        YonghuEntity u = yonghuService.selectOne(new EntityWrapper<YonghuEntity>().eq("yonghuzhanghao", username));
        if (u == null || !u.getYonghuzhanghao().equals(username)) {
            return Return.error("账号不正确");
        }
        if (u == null || !u.getYonghumima().equals(password)) {
            return Return.error("密码不正确");
        }
        String token = tokenService.generateToken(u.getId(), username, "yonghu", "用户");
        return Return.ok().put("token", token);
    }


    /**
     * 注册
     */
    @IgnoreAuth
    @RequestMapping("/register")
    public Return register(@RequestBody YonghuEntity yonghu) {
        //ValidatorUtils.validateEntity(yonghu);
        YonghuEntity u = yonghuService.selectOne(new EntityWrapper<YonghuEntity>().eq("yonghuzhanghao", yonghu.getYonghuzhanghao()));
        if (u != null) {
            return Return.error("注册用户已存在");
        }
        Long uId = new Date().getTime();
        yonghu.setId(uId);
        yonghuService.insert(yonghu);
        return Return.ok();
    }


    /**
     * 退出
     */
    @RequestMapping("/logout")
    public Return logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return Return.ok("退出成功");
    }

    /**
     * 获取用户的session用户信息
     */
    @RequestMapping("/session")
    public Return getCurrUser(HttpServletRequest request) {
        Long id = (Long) request.getSession().getAttribute("userId");
        return Return.ok().put("data", yonghuService.selectView(new EntityWrapper<YonghuEntity>().eq("id", id)));
    }

    /**
     * 密码重置
     */
    @IgnoreAuth
    @RequestMapping(value = "/resetPass")
    public Return resetPass(String username, HttpServletRequest request) {
        YonghuEntity u = yonghuService.selectOne(new EntityWrapper<YonghuEntity>().eq("yonghuzhanghao", username));
        if (u == null) {
            return Return.error("账号不存在");
        }
        u.setYonghumima("123456");
        yonghuService.updateById(u);
        return Return.ok("密码已重置为：123456");
    }


    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public Return page(@RequestParam Map<String, Object> params, YonghuEntity yonghu,
                       HttpServletRequest request) {
        EntityWrapper<YonghuEntity> ew = new EntityWrapper<YonghuEntity>();


        PageUtils page = yonghuService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, yonghu), params), params));
        return Return.ok().put("data", page);
    }


    /**
     * 前端列表
     */
    @IgnoreAuth
    @RequestMapping("/list")
    public Return list(@RequestParam Map<String, Object> params, YonghuEntity yonghu,
                       HttpServletRequest request) {
        EntityWrapper<YonghuEntity> ew = new EntityWrapper<YonghuEntity>();

        PageUtils page = yonghuService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, yonghu), params), params));
        return Return.ok().put("data", page);
    }

    /**
     * 列表
     */
    @RequestMapping("/lists")
    public Return list(YonghuEntity yonghu) {
        EntityWrapper<YonghuEntity> ew = new EntityWrapper<YonghuEntity>();
        ew.allEq(MPUtil.allEQMapPre(yonghu, "yonghu"));
        return Return.ok().put("data", yonghuService.selectListView(ew));
    }

    /**
     * 查询
     */
    @RequestMapping("/query")
    public Return query(YonghuEntity yonghu) {
        EntityWrapper<YonghuEntity> ew = new EntityWrapper<YonghuEntity>();
        ew.allEq(MPUtil.allEQMapPre(yonghu, "yonghu"));
        YonghuDTO yonghuView = yonghuService.selectView(ew);
        return Return.ok("查询用户成功").put("data", yonghuView);
    }

    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public Return info(@PathVariable("id") Long id) {
        YonghuEntity yonghu = yonghuService.selectById(id);
        yonghu = yonghuService.selectView(new EntityWrapper<YonghuEntity>().eq("id", id));
        return Return.ok().put("data", yonghu);
    }

    /**
     * 前端详情
     */
    @IgnoreAuth
    @RequestMapping("/detail/{id}")
    public Return detail(@PathVariable("id") Long id) {
        YonghuEntity yonghu = yonghuService.selectById(id);
        yonghu = yonghuService.selectView(new EntityWrapper<YonghuEntity>().eq("id", id));
        return Return.ok().put("data", yonghu);
    }


    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public Return save(@RequestBody YonghuEntity yonghu, HttpServletRequest request) {
        if (yonghuService.selectCount(new EntityWrapper<YonghuEntity>().eq("yonghuzhanghao", yonghu.getYonghuzhanghao())) > 0) {
            return Return.error("用户账号已存在");
        }
        yonghu.setId(new Date().getTime() + new Double(Math.floor(Math.random() * 1000)).longValue());
        //ValidatorUtils.validateEntity(yonghu);
        YonghuEntity u = yonghuService.selectOne(new EntityWrapper<YonghuEntity>().eq("yonghuzhanghao", yonghu.getYonghuzhanghao()));
        if (u != null) {
            return Return.error("用户已存在");
        }
        yonghu.setId(new Date().getTime());
        yonghuService.insert(yonghu);
        return Return.ok();
    }

    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public Return add(@RequestBody YonghuEntity yonghu, HttpServletRequest request) {
        if (yonghuService.selectCount(new EntityWrapper<YonghuEntity>().eq("yonghuzhanghao", yonghu.getYonghuzhanghao())) > 0) {
            return Return.error("用户账号已存在");
        }
        yonghu.setId(new Date().getTime() + new Double(Math.floor(Math.random() * 1000)).longValue());
        //ValidatorUtils.validateEntity(yonghu);
        YonghuEntity u = yonghuService.selectOne(new EntityWrapper<YonghuEntity>().eq("yonghuzhanghao", yonghu.getYonghuzhanghao()));
        if (u != null) {
            return Return.error("用户已存在");
        }
        yonghu.setId(new Date().getTime());
        yonghuService.insert(yonghu);
        return Return.ok();
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public Return update(@RequestBody YonghuEntity yonghu, HttpServletRequest request) {
        //ValidatorUtils.validateEntity(yonghu);
        yonghuService.updateById(yonghu);//全部更新
        return Return.ok();
    }


    /**
     * 删除
     */
    @RequestMapping("/delete")
    public Return delete(@RequestBody Long[] ids) {
        yonghuService.deleteBatchIds(Arrays.asList(ids));
        return Return.ok();
    }


}
