package com.mybatisplusdemo.web.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.mybatisplusdemo.assistant.IgnoreAuth;
import com.mybatisplusdemo.model.domain.LingshixinxiEntity;
import com.mybatisplusdemo.model.domain.OrdersEntity;
import com.mybatisplusdemo.model.dto.LingshixinxiDTO;
import com.mybatisplusdemo.service.LingshixinxiService;
import com.mybatisplusdemo.service.OrdersService;
import com.mybatisplusdemo.service.StoreupService;
import com.mybatisplusdemo.common.utils.MPUtil;
import com.mybatisplusdemo.common.utils.PageUtils;
import com.mybatisplusdemo.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 商品信息
 */
@RestController
@RequestMapping("/lingshixinxi")
public class LingshixinxiController {

    @Autowired private LingshixinxiService lingshixinxiService;
    @Autowired private StoreupService      storeupService;
    @Autowired private OrdersService       ordersService;

    /*──────────────────  后台分页  ──────────────────*/
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,
                  LingshixinxiEntity lingshixinxi,
                  HttpServletRequest request) {

        // 商家只能查自己的商品
        String tableName = String.valueOf(request.getSession().getAttribute("tableName"));
        if ("shangjia".equals(tableName)) {
            lingshixinxi.setShangjiazhanghao(
                    String.valueOf(request.getSession().getAttribute("username")));
        }

        EntityWrapper<LingshixinxiEntity> ew = new EntityWrapper<>();
        PageUtils page = lingshixinxiService.queryPage(
                params,
                MPUtil.sort(
                        MPUtil.between(MPUtil.likeOrEq(ew, lingshixinxi), params), params));

        return R.ok().put("data", page);
    }

    /*──────────────────  前端普通列表  ──────────────────*/
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,
                  LingshixinxiEntity lingshixinxi) {

        EntityWrapper<LingshixinxiEntity> ew = new EntityWrapper<>();
        PageUtils page = lingshixinxiService.queryPage(
                params,
                MPUtil.sort(
                        MPUtil.between(MPUtil.likeOrEq(ew, lingshixinxi), params), params));

        return R.ok().put("data", page);
    }

    /*──────────────────  where 全等查询  ──────────────────*/
    @RequestMapping("/lists")
    public R list(LingshixinxiEntity lingshixinxi) {
        EntityWrapper<LingshixinxiEntity> ew = new EntityWrapper<>();
        ew.allEq(MPUtil.allEQMapPre(lingshixinxi, "lingshixinxi"));
        return R.ok().put("data", lingshixinxiService.selectListView(ew));
    }

    /*──────────────────  单条查询  ──────────────────*/
    @RequestMapping("/query")
    public R query(LingshixinxiEntity lingshixinxi) {
        EntityWrapper<LingshixinxiEntity> ew = new EntityWrapper<>();
        ew.allEq(MPUtil.allEQMapPre(lingshixinxi, "lingshixinxi"));
        LingshixinxiDTO dto = lingshixinxiService.selectView(ew);
        return R.ok("查询商品信息成功").put("data", dto);
    }

    /*──────────────────  后台详情（统计点击量）  ──────────────────*/
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        LingshixinxiEntity entity = increaseClick(id);
        return R.ok().put("data", entity);
    }

    /*──────────────────  前端详情（统计点击量）  ──────────────────*/
    @IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id) {
        LingshixinxiEntity entity = increaseClick(id);
        return R.ok().put("data", entity);
    }

    /** 共用的“点一次 +1” 方法 */
    private LingshixinxiEntity increaseClick(Long id) {
        LingshixinxiEntity e = lingshixinxiService.selectById(id);
        if (e.getClickNumber() == null) e.setClickNumber(0);
        e.setClickNumber(e.getClickNumber() + 1);
        e.setClicktime(new Date());
        lingshixinxiService.updateById(e);
        return lingshixinxiService.selectView(new EntityWrapper<LingshixinxiEntity>().eq("id", id));
    }

    /*──────────────────  点赞 / 踩  ──────────────────*/
    @RequestMapping("/thumbsup/{id}")
    public R vote(@PathVariable("id") Long id, String type) {
        LingshixinxiEntity entity = lingshixinxiService.selectById(id);
        if ("1".equals(type)) {
            entity.setThumbsupNumber(entity.getThumbsupNumber() + 1);
        } else {
            entity.setCrazilyNumber(entity.getCrazilyNumber() + 1);
        }
        lingshixinxiService.updateById(entity);
        return R.ok("投票成功");
    }

    /*──────────────────  新增 / 修改 / 删除  ──────────────────*/
    @RequestMapping("/save")
    public R save(@RequestBody LingshixinxiEntity lingshixinxi) {
        lingshixinxiService.insert(lingshixinxi);
        return R.ok();
    }

    @RequestMapping("/add")
    public R add(@RequestBody LingshixinxiEntity lingshixinxi) {
        lingshixinxiService.insert(lingshixinxi);
        return R.ok();
    }

    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody LingshixinxiEntity lingshixinxi) {
        lingshixinxiService.updateById(lingshixinxi);
        return R.ok();
    }

    @RequestMapping("/shBatch")
    @Transactional
    public R auditBatch(@RequestBody Long[] ids,
                        @RequestParam String sfsh,
                        @RequestParam String shhf) {

        List<LingshixinxiEntity> list = new ArrayList<>();
        for (Long id : ids) {
            LingshixinxiEntity e = lingshixinxiService.selectById(id);
            e.setSfsh(sfsh);
            e.setShhf(shhf);
            list.add(e);
        }
        lingshixinxiService.updateBatchById(list);
        return R.ok();
    }

    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids) {
        lingshixinxiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

    /*──────────────────  ★ 智能排序（未登录）★  ──────────────────*/
    @IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,
                      LingshixinxiEntity lingshixinxi) {

        EntityWrapper<LingshixinxiEntity> ew = new EntityWrapper<>();
        ew.eq("sfsh", "是");                        // 只展示已审核通过的

        /* 重点①：给未传值时设置默认排序 —— 点击量高 → 时间新 */
        params.putIfAbsent("sort",  "click_number");   // 数据库字段
        params.putIfAbsent("order", "desc");

        PageUtils page = lingshixinxiService.queryPage(
                params,
                MPUtil.sort(
                        MPUtil.between(MPUtil.likeOrEq(ew, lingshixinxi), params),
                        params));

        return R.ok().put("data", page);
    }

    /*──────────────────  ★ 智能排序（按用户购买历史推荐）★  ──────────────────*/
    @RequestMapping("/autoSort2")
    public R autoSort2(@RequestParam Map<String, Object> params,
                       LingshixinxiEntity lingshixinxi,
                       HttpServletRequest request) {

        String userId = String.valueOf(request.getSession().getAttribute("userId"));
        String goodtypeColumn = "lingshifenlei";

        /* 1. 找到当前用户买过的不同“零食分类” */
        List<OrdersEntity> orders = ordersService.selectList(
                new EntityWrapper<OrdersEntity>()
                        .eq("userid", userId)
                        .eq("tablename", "lingshixinxi")
                        .orderBy("addtime", false));

        // 按分类去重
        List<String> boughtTypes = new ArrayList<>();
        for (OrdersEntity o : orders) {
            if (!boughtTypes.contains(o.getGoodtype())) {
                boughtTypes.add(o.getGoodtype());
            }
        }

        /* 2. 取出这些分类下的商品，当作“相关推荐” */
        List<LingshixinxiEntity> recomList = new ArrayList<>();
        for (String type : boughtTypes) {
            recomList.addAll(
                    lingshixinxiService.selectList(
                            new EntityWrapper<LingshixinxiEntity>()
                                    .eq(goodtypeColumn, type)
                                    .eq("sfsh", "是")
                    )
            );
        }

        /* 3. 如数量不足，再用普通排序兜底补足到 limit 条 */
        params.putIfAbsent("limit", 10);
        params.putIfAbsent("page",  "1");
        params.putIfAbsent("sort",  "click_number");
        params.putIfAbsent("order", "desc");

        PageUtils page = lingshixinxiService.queryPage(
                params,
                MPUtil.sort(
                        MPUtil.between(new EntityWrapper<LingshixinxiEntity>(), params),
                        params));

        List<LingshixinxiEntity> fallback = (List<LingshixinxiEntity>) page.getList();
        int limit = Integer.parseInt(params.get("limit").toString());

        for (LingshixinxiEntity fb : fallback) {
            if (recomList.size() >= limit) break;
            // 去重
            boolean exists = recomList.stream().anyMatch(e -> e.getId().equals(fb.getId()));
            if (!exists) recomList.add(fb);
        }

        /* 4. 最终结果写回 PageUtils 并返回 */
        page.setList(recomList.size() > limit ? recomList.subList(0, limit) : recomList);
        return R.ok().put("data", page);
    }
}
