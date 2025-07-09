package com.mybatisplusdemo.web.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.mybatisplusdemo.assistant.IgnoreAuth;
import com.mybatisplusdemo.model.domain.DiscusslingshixinxiEntity;
import com.mybatisplusdemo.model.dto.DiscusslingshixinxiDTO;
import com.mybatisplusdemo.service.DiscusslingshixinxiService;
import com.mybatisplusdemo.common.utils.MPUtil;
import com.mybatisplusdemo.common.utils.PageUtils;
import com.mybatisplusdemo.common.utils.Return;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


@RestController
@RequestMapping("/discusslingshixinxi")
public class DiscusslingshixinxiController {
    @Autowired
    private DiscusslingshixinxiService discusslingshixinxiService;


    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public Return page(@RequestParam Map<String, Object> params, DiscusslingshixinxiEntity discusslingshixinxi,
                       HttpServletRequest request) {
        EntityWrapper<DiscusslingshixinxiEntity> ew = new EntityWrapper<DiscusslingshixinxiEntity>();


        PageUtils page = discusslingshixinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discusslingshixinxi), params), params));
        return Return.ok().put("data", page);
    }


    /**
     * 前端列表
     */
    @IgnoreAuth
    @RequestMapping("/list")
    public Return list(@RequestParam Map<String, Object> params, DiscusslingshixinxiEntity discusslingshixinxi,
                       HttpServletRequest request) {
        EntityWrapper<DiscusslingshixinxiEntity> ew = new EntityWrapper<DiscusslingshixinxiEntity>();

        PageUtils page = discusslingshixinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discusslingshixinxi), params), params));
        return Return.ok().put("data", page);
    }

    /**
     * 列表
     */
    @RequestMapping("/lists")
    public Return list(DiscusslingshixinxiEntity discusslingshixinxi) {
        EntityWrapper<DiscusslingshixinxiEntity> ew = new EntityWrapper<DiscusslingshixinxiEntity>();
        ew.allEq(MPUtil.allEQMapPre(discusslingshixinxi, "discusslingshixinxi"));
        return Return.ok().put("data", discusslingshixinxiService.selectListView(ew));
    }

    /**
     * 查询
     */
    @RequestMapping("/query")
    public Return query(DiscusslingshixinxiEntity discusslingshixinxi) {
        EntityWrapper<DiscusslingshixinxiEntity> ew = new EntityWrapper<DiscusslingshixinxiEntity>();
        ew.allEq(MPUtil.allEQMapPre(discusslingshixinxi, "discusslingshixinxi"));
        DiscusslingshixinxiDTO discusslingshixinxiView = discusslingshixinxiService.selectView(ew);
        return Return.ok("查询商品信息评论表成功").put("data", discusslingshixinxiView);
    }

    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public Return info(@PathVariable("id") Long id) {
        DiscusslingshixinxiEntity discusslingshixinxi = discusslingshixinxiService.selectById(id);
        discusslingshixinxi = discusslingshixinxiService.selectView(new EntityWrapper<DiscusslingshixinxiEntity>().eq("id", id));
        return Return.ok().put("data", discusslingshixinxi);
    }

    /**
     * 前端详情
     */
    @IgnoreAuth
    @RequestMapping("/detail/{id}")
    public Return detail(@PathVariable("id") Long id) {
        DiscusslingshixinxiEntity discusslingshixinxi = discusslingshixinxiService.selectById(id);
        discusslingshixinxi = discusslingshixinxiService.selectView(new EntityWrapper<DiscusslingshixinxiEntity>().eq("id", id));
        return Return.ok().put("data", discusslingshixinxi);
    }


    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public Return save(@RequestBody DiscusslingshixinxiEntity discusslingshixinxi, HttpServletRequest request) {
        //ValidatorUtils.validateEntity(discusslingshixinxi);
        discusslingshixinxiService.insert(discusslingshixinxi);
        return Return.ok();
    }

    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public Return add(@RequestBody DiscusslingshixinxiEntity discusslingshixinxi, HttpServletRequest request) {
        //ValidatorUtils.validateEntity(discusslingshixinxi);
        discusslingshixinxiService.insert(discusslingshixinxi);
        return Return.ok();
    }

    /**
     * 获取用户密保
     */
    @RequestMapping("/security")
    @IgnoreAuth
    public Return security(@RequestParam String username) {
        DiscusslingshixinxiEntity discusslingshixinxi = discusslingshixinxiService.selectOne(new EntityWrapper<DiscusslingshixinxiEntity>().eq("", username));
        return Return.ok().put("data", discusslingshixinxi);
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @IgnoreAuth
    public Return update(@RequestBody DiscusslingshixinxiEntity discusslingshixinxi, HttpServletRequest request) {
        //ValidatorUtils.validateEntity(discusslingshixinxi);
        discusslingshixinxiService.updateById(discusslingshixinxi);//全部更新
        return Return.ok();
    }


    /**
     * 删除
     */
    @RequestMapping("/delete")
    public Return delete(@RequestBody Long[] ids) {
        discusslingshixinxiService.deleteBatchIds(Arrays.asList(ids));
        return Return.ok();
    }


    /**
     * 前端智能排序
     */
    @IgnoreAuth
    @RequestMapping("/autoSort")
    public Return autoSort(@RequestParam Map<String, Object> params, DiscusslingshixinxiEntity discusslingshixinxi, HttpServletRequest request, String pre) {
        EntityWrapper<DiscusslingshixinxiEntity> ew = new EntityWrapper<DiscusslingshixinxiEntity>();
        Map<String, Object> newMap = new HashMap<String, Object>();
        Map<String, Object> param = new HashMap<String, Object>();
        Iterator<Map.Entry<String, Object>> it = param.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Object> entry = it.next();
            String key = entry.getKey();
            String newKey = entry.getKey();
            if (pre.endsWith(".")) {
                newMap.put(pre + newKey, entry.getValue());
            } else if (StringUtils.isEmpty(pre)) {
                newMap.put(newKey, entry.getValue());
            } else {
                newMap.put(pre + "." + newKey, entry.getValue());
            }
        }
        params.put("sort", "clicktime");
        params.put("order", "desc");
        PageUtils page = discusslingshixinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discusslingshixinxi), params), params));
        return Return.ok().put("data", page);
    }


}
