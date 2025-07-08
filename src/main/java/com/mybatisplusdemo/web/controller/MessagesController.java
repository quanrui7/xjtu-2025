package com.mybatisplusdemo.web.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.mybatisplusdemo.assistant.IgnoreAuth;
import com.mybatisplusdemo.model.domain.MessagesEntity;
import com.mybatisplusdemo.model.dto.MessagesDTO;
import com.mybatisplusdemo.service.MessagesService;
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
 * 留言板
 * 后端接口
 *
 * @author
 * @email
 * @date 2025-02-15 13:47:52
 */
@RestController
@RequestMapping("/messages")
public class MessagesController {
    @Autowired
    private MessagesService messagesService;


    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, MessagesEntity messages,
                  HttpServletRequest request) {
        EntityWrapper<MessagesEntity> ew = new EntityWrapper<MessagesEntity>();


        PageUtils page = messagesService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, messages), params), params));
        return R.ok().put("data", page);
    }


    /**
     * 前端列表
     */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, MessagesEntity messages,
                  HttpServletRequest request) {
        EntityWrapper<MessagesEntity> ew = new EntityWrapper<MessagesEntity>();

        PageUtils page = messagesService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, messages), params), params));
        return R.ok().put("data", page);
    }

    /**
     * 列表
     */
    @RequestMapping("/lists")
    public R list(MessagesEntity messages) {
        EntityWrapper<MessagesEntity> ew = new EntityWrapper<MessagesEntity>();
        ew.allEq(MPUtil.allEQMapPre(messages, "messages"));
        return R.ok().put("data", messagesService.selectListView(ew));
    }

    /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(MessagesEntity messages) {
        EntityWrapper<MessagesEntity> ew = new EntityWrapper<MessagesEntity>();
        ew.allEq(MPUtil.allEQMapPre(messages, "messages"));
        MessagesDTO messagesView = messagesService.selectView(ew);
        return R.ok("查询留言板成功").put("data", messagesView);
    }

    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        MessagesEntity messages = messagesService.selectById(id);
        messages = messagesService.selectView(new EntityWrapper<MessagesEntity>().eq("id", id));
        return R.ok().put("data", messages);
    }

    /**
     * 前端详情
     */
    @IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id) {
        MessagesEntity messages = messagesService.selectById(id);
        messages = messagesService.selectView(new EntityWrapper<MessagesEntity>().eq("id", id));
        return R.ok().put("data", messages);
    }


    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody MessagesEntity messages, HttpServletRequest request) {
        //ValidatorUtils.validateEntity(messages);
        messagesService.insert(messages);
        return R.ok();
    }

    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody MessagesEntity messages, HttpServletRequest request) {
        //ValidatorUtils.validateEntity(messages);
        messagesService.insert(messages);
        return R.ok();
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody MessagesEntity messages, HttpServletRequest request) {
        //ValidatorUtils.validateEntity(messages);
        messagesService.updateById(messages);//全部更新
        return R.ok();
    }


    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids) {
        messagesService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }


}
