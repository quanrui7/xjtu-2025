package com.mybatisplusdemo.web.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.mybatisplusdemo.assistant.IgnoreAuth;
import com.mybatisplusdemo.model.domain.MessagesEntity;
import com.mybatisplusdemo.model.dto.MessagesDTO;
import com.mybatisplusdemo.service.MessagesService;
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
@RequestMapping("/messages")
public class MessagesController {
    @Autowired
    private MessagesService messagesService;


    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public Return page(@RequestParam Map<String, Object> params, MessagesEntity messages,
                       HttpServletRequest request) {
        EntityWrapper<MessagesEntity> ew = new EntityWrapper<MessagesEntity>();


        PageUtils page = messagesService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, messages), params), params));
        return Return.ok().put("data", page);
    }


    /**
     * 前端列表
     */
    @IgnoreAuth
    @RequestMapping("/list")
    public Return list(@RequestParam Map<String, Object> params, MessagesEntity messages,
                       HttpServletRequest request) {
        EntityWrapper<MessagesEntity> ew = new EntityWrapper<MessagesEntity>();

        PageUtils page = messagesService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, messages), params), params));
        return Return.ok().put("data", page);
    }

    /**
     * 列表
     */
    @RequestMapping("/lists")
    public Return list(MessagesEntity messages) {
        EntityWrapper<MessagesEntity> ew = new EntityWrapper<MessagesEntity>();
        ew.allEq(MPUtil.allEQMapPre(messages, "messages"));
        return Return.ok().put("data", messagesService.selectListView(ew));
    }

    /**
     * 查询
     */
    @RequestMapping("/query")
    public Return query(MessagesEntity messages) {
        EntityWrapper<MessagesEntity> ew = new EntityWrapper<MessagesEntity>();
        ew.allEq(MPUtil.allEQMapPre(messages, "messages"));
        MessagesDTO messagesView = messagesService.selectView(ew);
        return Return.ok("查询留言板成功").put("data", messagesView);
    }

    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public Return info(@PathVariable("id") Long id) {
        MessagesEntity messages = messagesService.selectById(id);
        messages = messagesService.selectView(new EntityWrapper<MessagesEntity>().eq("id", id));
        return Return.ok().put("data", messages);
    }

    /**
     * 前端详情
     */
    @IgnoreAuth
    @RequestMapping("/detail/{id}")
    public Return detail(@PathVariable("id") Long id) {
        MessagesEntity messages = messagesService.selectById(id);
        messages = messagesService.selectView(new EntityWrapper<MessagesEntity>().eq("id", id));
        return Return.ok().put("data", messages);
    }


    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public Return save(@RequestBody MessagesEntity messages, HttpServletRequest request) {
        //ValidatorUtils.validateEntity(messages);
        messagesService.insert(messages);
        return Return.ok();
    }

    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public Return add(@RequestBody MessagesEntity messages, HttpServletRequest request) {
        //ValidatorUtils.validateEntity(messages);
        messagesService.insert(messages);
        return Return.ok();
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public Return update(@RequestBody MessagesEntity messages, HttpServletRequest request) {
        //ValidatorUtils.validateEntity(messages);
        messagesService.updateById(messages);//全部更新
        return Return.ok();
    }


    /**
     * 删除
     */
    @RequestMapping("/delete")
    public Return delete(@RequestBody Long[] ids) {
        messagesService.deleteBatchIds(Arrays.asList(ids));
        return Return.ok();
    }


}
