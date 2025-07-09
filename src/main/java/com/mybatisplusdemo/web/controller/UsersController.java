package com.mybatisplusdemo.web.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.mybatisplusdemo.assistant.IgnoreAuth;
import com.mybatisplusdemo.model.domain.UsersEntity;
import com.mybatisplusdemo.model.dto.UsersDTO;
import com.mybatisplusdemo.service.TokenService;
import com.mybatisplusdemo.service.UsersService;
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
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private UsersService usersService;


    @Autowired
    private TokenService tokenService;

    /**
     * 登录
     */
    @IgnoreAuth
    @RequestMapping(value = "/login")
    public Return login(String username, String password, String captcha, HttpServletRequest request) {
        UsersEntity u = usersService.selectOne(new EntityWrapper<UsersEntity>().eq("username", username));
        if (u == null || !u.getUsername().equals(username)) {
            return Return.error("账号不正确");
        }
        if (u == null || !u.getPassword().equals(password)) {
            return Return.error("密码不正确");
        }
        String token = tokenService.generateToken(u.getId(), username, "users", "管理员");
        return Return.ok().put("token", token);
    }


    /**
     * 注册
     */
    @IgnoreAuth
    @RequestMapping("/register")
    public Return register(@RequestBody UsersEntity users) {
        //ValidatorUtils.validateEntity(users);
        UsersEntity u = usersService.selectOne(new EntityWrapper<UsersEntity>().eq("username", users.getUsername()));
        if (u != null) {
            return Return.error("注册用户已存在");
        }
        Long uId = new Date().getTime();
        users.setId(uId);
        users.setPassword(users.getPassword());
        usersService.insert(users);
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
        return Return.ok().put("data", usersService.selectView(new EntityWrapper<UsersEntity>().eq("id", id)));
    }

    /**
     * 密码重置
     */
    @IgnoreAuth
    @RequestMapping(value = "/resetPass")
    public Return resetPass(String username, HttpServletRequest request) {
        UsersEntity u = usersService.selectOne(new EntityWrapper<UsersEntity>().eq("username", username));
        if (u == null) {
            return Return.error("账号不存在");
        }
        u.setPassword("123456");
        usersService.updateById(u);
        return Return.ok("密码已重置为：123456");
    }


    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public Return page(@RequestParam Map<String, Object> params, UsersEntity users,
                       HttpServletRequest request) {
        EntityWrapper<UsersEntity> ew = new EntityWrapper<UsersEntity>();


        PageUtils page = usersService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, users), params), params));
        return Return.ok().put("data", page);
    }


    /**
     * 前端列表
     */
    @IgnoreAuth
    @RequestMapping("/list")
    public Return list(@RequestParam Map<String, Object> params, UsersEntity users,
                       HttpServletRequest request) {
        EntityWrapper<UsersEntity> ew = new EntityWrapper<UsersEntity>();

        PageUtils page = usersService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, users), params), params));
        return Return.ok().put("data", page);
    }

    /**
     * 列表
     */
    @RequestMapping("/lists")
    public Return list(UsersEntity users) {
        EntityWrapper<UsersEntity> ew = new EntityWrapper<UsersEntity>();
        ew.allEq(MPUtil.allEQMapPre(users, "users"));
        return Return.ok().put("data", usersService.selectListView(ew));
    }

    /**
     * 查询
     */
    @RequestMapping("/query")
    public Return query(UsersEntity users) {
        EntityWrapper<UsersEntity> ew = new EntityWrapper<UsersEntity>();
        ew.allEq(MPUtil.allEQMapPre(users, "users"));
        UsersDTO usersView = usersService.selectView(ew);
        return Return.ok("查询管理员成功").put("data", usersView);
    }

    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public Return info(@PathVariable("id") Long id) {
        UsersEntity users = usersService.selectById(id);
        users = usersService.selectView(new EntityWrapper<UsersEntity>().eq("id", id));
        return Return.ok().put("data", users);
    }

    /**
     * 前端详情
     */
    @IgnoreAuth
    @RequestMapping("/detail/{id}")
    public Return detail(@PathVariable("id") Long id) {
        UsersEntity users = usersService.selectById(id);
        users = usersService.selectView(new EntityWrapper<UsersEntity>().eq("id", id));
        return Return.ok().put("data", users);
    }


    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public Return save(@RequestBody UsersEntity users, HttpServletRequest request) {
        users.setId(new Date().getTime() + new Double(Math.floor(Math.random() * 1000)).longValue());
        //ValidatorUtils.validateEntity(users);
        UsersEntity u = usersService.selectOne(new EntityWrapper<UsersEntity>().eq("username", users.getUsername()));
        if (u != null) {
            return Return.error("用户已存在");
        }
        users.setId(new Date().getTime());
        users.setPassword(users.getPassword());
        usersService.insert(users);
        return Return.ok();
    }

    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public Return add(@RequestBody UsersEntity users, HttpServletRequest request) {
        users.setId(new Date().getTime() + new Double(Math.floor(Math.random() * 1000)).longValue());
        //ValidatorUtils.validateEntity(users);
        UsersEntity u = usersService.selectOne(new EntityWrapper<UsersEntity>().eq("username", users.getUsername()));
        if (u != null) {
            return Return.error("用户已存在");
        }
        users.setId(new Date().getTime());
        users.setPassword(users.getPassword());
        usersService.insert(users);
        return Return.ok();
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public Return update(@RequestBody UsersEntity users, HttpServletRequest request) {
        //ValidatorUtils.validateEntity(users);
        usersService.updateById(users);//全部更新
        return Return.ok();
    }


    /**
     * 删除
     */
    @RequestMapping("/delete")
    public Return delete(@RequestBody Long[] ids) {
        usersService.deleteBatchIds(Arrays.asList(ids));
        return Return.ok();
    }


}
