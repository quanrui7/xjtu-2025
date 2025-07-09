package com.mybatisplusdemo.web.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.mybatisplusdemo.assistant.IgnoreAuth;
import com.mybatisplusdemo.model.domain.ShangjiaEntity;
import com.mybatisplusdemo.model.dto.ShangjiaDTO;
import com.mybatisplusdemo.service.ShangjiaService;
import com.mybatisplusdemo.service.TokenService;
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
@RequestMapping("/shangjia")
public class ShangjiaController {
    @Autowired
    private ShangjiaService shangjiaService;


    @Autowired
    private TokenService tokenService;

    /**
     * 登录
     */
    @IgnoreAuth
    @RequestMapping(value = "/login")
    public Return login(String username, String password, String captcha, HttpServletRequest request) {
        ShangjiaEntity u = shangjiaService.selectOne(new EntityWrapper<ShangjiaEntity>().eq("shangjiazhanghao", username));
        if (u == null || !u.getShangjiazhanghao().equals(username)) {
            return Return.error("账号不正确");
        }
        if (u == null || !u.getShangjiamima().equals(password)) {
            return Return.error("密码不正确");
        }
        String token = tokenService.generateToken(u.getId(), username, "shangjia", "管理员");
        return Return.ok().put("token", token);
    }


    /**
     * 注册
     */
    @IgnoreAuth
    @RequestMapping("/register")
    public Return register(@RequestBody ShangjiaEntity shangjia) {
        //ValidatorUtils.validateEntity(shangjia);
        ShangjiaEntity u = shangjiaService.selectOne(new EntityWrapper<ShangjiaEntity>().eq("shangjiazhanghao", shangjia.getShangjiazhanghao()));
        if (u != null) {
            return Return.error("注册用户已存在");
        }
        Long uId = new Date().getTime();
        shangjia.setId(uId);
        shangjiaService.insert(shangjia);
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
        return Return.ok().put("data", shangjiaService.selectView(new EntityWrapper<ShangjiaEntity>().eq("id", id)));
    }

    /**
     * 密码重置
     */
    @IgnoreAuth
    @RequestMapping(value = "/resetPass")
    public Return resetPass(String username, HttpServletRequest request) {
        ShangjiaEntity u = shangjiaService.selectOne(new EntityWrapper<ShangjiaEntity>().eq("shangjiazhanghao", username));
        if (u == null) {
            return Return.error("账号不存在");
        }
        u.setShangjiamima("123456");
        shangjiaService.updateById(u);
        return Return.ok("密码已重置为：123456");
    }


    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public Return page(@RequestParam Map<String, Object> params, ShangjiaEntity shangjia,
                       HttpServletRequest request) {
        EntityWrapper<ShangjiaEntity> ew = new EntityWrapper<ShangjiaEntity>();


        PageUtils page = shangjiaService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, shangjia), params), params));
        return Return.ok().put("data", page);
    }


    /**
     * 前端列表
     */
    @IgnoreAuth
    @RequestMapping("/list")
    public Return list(@RequestParam Map<String, Object> params, ShangjiaEntity shangjia,
                       HttpServletRequest request) {
        EntityWrapper<ShangjiaEntity> ew = new EntityWrapper<ShangjiaEntity>();

        PageUtils page = shangjiaService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, shangjia), params), params));
        return Return.ok().put("data", page);
    }

    /**
     * 列表
     */
    @RequestMapping("/lists")
    public Return list(ShangjiaEntity shangjia) {
        EntityWrapper<ShangjiaEntity> ew = new EntityWrapper<ShangjiaEntity>();
        ew.allEq(MPUtil.allEQMapPre(shangjia, "shangjia"));
        return Return.ok().put("data", shangjiaService.selectListView(ew));
    }

    /**
     * 查询
     */
    @RequestMapping("/query")
    public Return query(ShangjiaEntity shangjia) {
        EntityWrapper<ShangjiaEntity> ew = new EntityWrapper<ShangjiaEntity>();
        ew.allEq(MPUtil.allEQMapPre(shangjia, "shangjia"));
        ShangjiaDTO shangjiaView = shangjiaService.selectView(ew);
        return Return.ok("查询商家成功").put("data", shangjiaView);
    }

    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public Return info(@PathVariable("id") Long id) {
        ShangjiaEntity shangjia = shangjiaService.selectById(id);
        shangjia = shangjiaService.selectView(new EntityWrapper<ShangjiaEntity>().eq("id", id));
        return Return.ok().put("data", shangjia);
    }

    /**
     * 前端详情
     */
    @IgnoreAuth
    @RequestMapping("/detail/{id}")
    public Return detail(@PathVariable("id") Long id) {
        ShangjiaEntity shangjia = shangjiaService.selectById(id);
        shangjia = shangjiaService.selectView(new EntityWrapper<ShangjiaEntity>().eq("id", id));
        return Return.ok().put("data", shangjia);
    }


    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public Return save(@RequestBody ShangjiaEntity shangjia, HttpServletRequest request) {
        if (shangjiaService.selectCount(new EntityWrapper<ShangjiaEntity>().eq("shangjiazhanghao", shangjia.getShangjiazhanghao())) > 0) {
            return Return.error("商家账号已存在");
        }
        shangjia.setId(new Date().getTime() + new Double(Math.floor(Math.random() * 1000)).longValue());
        //ValidatorUtils.validateEntity(shangjia);
        ShangjiaEntity u = shangjiaService.selectOne(new EntityWrapper<ShangjiaEntity>().eq("shangjiazhanghao", shangjia.getShangjiazhanghao()));
        if (u != null) {
            return Return.error("用户已存在");
        }
        shangjia.setId(new Date().getTime());
        shangjiaService.insert(shangjia);
        return Return.ok();
    }

    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public Return add(@RequestBody ShangjiaEntity shangjia, HttpServletRequest request) {
        if (shangjiaService.selectCount(new EntityWrapper<ShangjiaEntity>().eq("shangjiazhanghao", shangjia.getShangjiazhanghao())) > 0) {
            return Return.error("商家账号已存在");
        }
        shangjia.setId(new Date().getTime() + new Double(Math.floor(Math.random() * 1000)).longValue());
        //ValidatorUtils.validateEntity(shangjia);
        ShangjiaEntity u = shangjiaService.selectOne(new EntityWrapper<ShangjiaEntity>().eq("shangjiazhanghao", shangjia.getShangjiazhanghao()));
        if (u != null) {
            return Return.error("用户已存在");
        }
        shangjia.setId(new Date().getTime());
        shangjiaService.insert(shangjia);
        return Return.ok();
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public Return update(@RequestBody ShangjiaEntity shangjia, HttpServletRequest request) {
        //ValidatorUtils.validateEntity(shangjia);
        shangjiaService.updateById(shangjia);//全部更新
        return Return.ok();
    }


    /**
     * 删除
     */
    @RequestMapping("/delete")
    public Return delete(@RequestBody Long[] ids) {
        shangjiaService.deleteBatchIds(Arrays.asList(ids));
        return Return.ok();
    }


}
