package com.mybatisplusdemo.web.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.mybatisplusdemo.assistant.IgnoreAuth;
import com.mybatisplusdemo.model.domain.ShangpinxinxiEntity;
import com.mybatisplusdemo.model.domain.OrdersEntity;
import com.mybatisplusdemo.model.dto.ShangpinxinxiDTO;
import com.mybatisplusdemo.service.ShangpinxinxiService;
import com.mybatisplusdemo.service.OrdersService;
import com.mybatisplusdemo.service.StoreupService;
import com.mybatisplusdemo.common.utils.MPUtil;
import com.mybatisplusdemo.common.utils.PageUtils;
import com.mybatisplusdemo.common.utils.Return;
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
public class ShangpinxinxiController {

    @Autowired private ShangpinxinxiService shangpinxinxiService;
    @Autowired private StoreupService      storeupService;
    @Autowired private OrdersService       ordersService;

    /*──────────────────  后台分页  ──────────────────*/
    @RequestMapping("/page")
    public Return page(@RequestParam Map<String, Object> params,
                       ShangpinxinxiEntity lingshixinxi,
                       HttpServletRequest request) {

        // 商家只能查自己的商品
        String tableName = String.valueOf(request.getSession().getAttribute("tableName"));
        if ("shangjia".equals(tableName)) {
            lingshixinxi.setShangjiazhanghao(
                    String.valueOf(request.getSession().getAttribute("username")));
        }

        EntityWrapper<ShangpinxinxiEntity> ew = new EntityWrapper<>();
        PageUtils page = shangpinxinxiService.queryPage(
                params,
                MPUtil.sort(
                        MPUtil.between(MPUtil.likeOrEq(ew, lingshixinxi), params), params));

        return Return.ok().put("data", page);
    }

    /*──────────────────  前端普通列表  ──────────────────*/
    @IgnoreAuth
    @RequestMapping("/list")
    public Return list(@RequestParam Map<String, Object> params,
                       ShangpinxinxiEntity lingshixinxi) {

        EntityWrapper<ShangpinxinxiEntity> ew = new EntityWrapper<>();
        ew.eq("sfsh", "是");
        PageUtils page = shangpinxinxiService.queryPage(
                params,
                MPUtil.sort(
                        MPUtil.between(MPUtil.likeOrEq(ew, lingshixinxi), params), params));

        return Return.ok().put("data", page);
    }

    /*──────────────────  where 全等查询  ──────────────────*/
    @RequestMapping("/lists")
    public Return list(ShangpinxinxiEntity lingshixinxi) {
        EntityWrapper<ShangpinxinxiEntity> ew = new EntityWrapper<>();
        ew.eq("sfsh", "是");
        ew.allEq(MPUtil.allEQMapPre(lingshixinxi, "lingshixinxi"));
        return Return.ok().put("data", shangpinxinxiService.selectListView(ew));
    }

    /*──────────────────  单条查询  ──────────────────*/
    @RequestMapping("/query")
    public Return query(ShangpinxinxiEntity lingshixinxi) {
        EntityWrapper<ShangpinxinxiEntity> ew = new EntityWrapper<>();
        ew.eq("sfsh", "是");
        ew.allEq(MPUtil.allEQMapPre(lingshixinxi, "lingshixinxi"));
        ShangpinxinxiDTO dto = shangpinxinxiService.selectView(ew);
        return Return.ok("查询商品信息成功").put("data", dto);
    }

    /*──────────────────  后台详情（统计点击量）  ──────────────────*/
    @RequestMapping("/info/{id}")
    public Return info(@PathVariable("id") Long id) {
        ShangpinxinxiEntity entity = increaseClick(id);
        return Return.ok().put("data", entity);
    }

    /*──────────────────  前端详情（统计点击量）  ──────────────────*/
    @IgnoreAuth
    @RequestMapping("/detail/{id}")
    public Return detail(@PathVariable("id") Long id) {
        ShangpinxinxiEntity entity = increaseClick(id);
        return Return.ok().put("data", entity);
    }

    /** 共用的“点一次 +1” 方法 */
    private ShangpinxinxiEntity increaseClick(Long id) {
        ShangpinxinxiEntity e = shangpinxinxiService.selectById(id);
        if (e.getClickNumber() == null) e.setClickNumber(0);
        e.setClickNumber(e.getClickNumber() + 1);
        e.setClicktime(new Date());
        shangpinxinxiService.updateById(e);
        return shangpinxinxiService.selectView(new EntityWrapper<ShangpinxinxiEntity>().eq("id", id));
    }

    /*──────────────────  点赞 / 踩  ──────────────────*/
    @RequestMapping("/thumbsup/{id}")
    public Return vote(@PathVariable("id") Long id, String type) {
        ShangpinxinxiEntity entity = shangpinxinxiService.selectById(id);
        if ("1".equals(type)) {
            entity.setThumbsupNumber(entity.getThumbsupNumber() + 1);
        } else {
            entity.setCrazilyNumber(entity.getCrazilyNumber() + 1);
        }
        shangpinxinxiService.updateById(entity);
        return Return.ok("投票成功");
    }

    /*──────────────────  新增 / 修改 / 删除  ──────────────────*/
    @RequestMapping("/save")
    public Return save(@RequestBody ShangpinxinxiEntity lingshixinxi) {
        shangpinxinxiService.insert(lingshixinxi);
        return Return.ok();
    }

    @RequestMapping("/add")
    public Return add(@RequestBody ShangpinxinxiEntity lingshixinxi) {
        shangpinxinxiService.insert(lingshixinxi);
        return Return.ok();
    }

    @RequestMapping("/update")
    @Transactional
    public Return update(@RequestBody ShangpinxinxiEntity lingshixinxi) {
        shangpinxinxiService.updateById(lingshixinxi);
        return Return.ok();
    }

    @RequestMapping("/shBatch")
    @Transactional
    public Return auditBatch(@RequestBody Long[] ids,
                             @RequestParam String sfsh,
                             @RequestParam String shhf) {

        List<ShangpinxinxiEntity> list = new ArrayList<>();
        for (Long id : ids) {
            ShangpinxinxiEntity e = shangpinxinxiService.selectById(id);
            e.setSfsh(sfsh);
            e.setShhf(shhf);
            list.add(e);
        }
        shangpinxinxiService.updateBatchById(list);
        return Return.ok();
    }

    @RequestMapping("/delete")
    public Return delete(@RequestBody Long[] ids) {
        shangpinxinxiService.deleteBatchIds(Arrays.asList(ids));
        return Return.ok();
    }

    /*──────────────────  ★ 智能排序（未登录）★  ──────────────────*/
    @IgnoreAuth
    @RequestMapping("/autoSort")
    public Return autoSort(@RequestParam Map<String, Object> params,
                           ShangpinxinxiEntity lingshixinxi) {

        EntityWrapper<ShangpinxinxiEntity> ew = new EntityWrapper<>();
        ew.eq("sfsh", "是");                        // 只展示已审核通过的

        /* 重点①：给未传值时设置默认排序 —— 点击量高 → 时间新 */
        params.putIfAbsent("sort",  "click_number");   // 数据库字段
        params.putIfAbsent("order", "desc");

        PageUtils page = shangpinxinxiService.queryPage(
                params,
                MPUtil.sort(
                        MPUtil.between(MPUtil.likeOrEq(ew, lingshixinxi), params),
                        params));

        return Return.ok().put("data", page);
    }

    /*──────────────────  ★ 智能排序（按用户购买历史推荐）★  ──────────────────*/
    @RequestMapping("/autoSort2")
    public Return autoSort2(@RequestParam Map<String, Object> params,
                            ShangpinxinxiEntity lingshixinxi,
                            HttpServletRequest request) {

        String userId = String.valueOf(request.getSession().getAttribute("userId"));
        String goodtypeColumn = "lingshifenlei";

        // 1. 获取用户的历史订单记录
        List<OrdersEntity> orders = ordersService.selectList(
                new EntityWrapper<OrdersEntity>()
                        .eq("userid", userId)
                        .eq("tablename", "lingshixinxi")
                        .orderBy("addtime", false)
        );

        // 2. 统计每个分类的购买次数（用于优先级排序）
        Map<String, Integer> categoryCountMap = new LinkedHashMap<>();
        for (OrdersEntity o : orders) {
            String type = o.getGoodtype();
            if (type != null && !type.trim().equals("")) {
                categoryCountMap.put(type, categoryCountMap.getOrDefault(type, 0) + 1);
            }
        }

        // 3. 将分类按购买次数降序排序
        List<String> sortedCategories = new ArrayList<>(categoryCountMap.keySet());
        sortedCategories.sort((a, b) -> categoryCountMap.get(b).compareTo(categoryCountMap.get(a)));

        // 4. 每类取最多 N 条（按点击量降序）
        int perTypeLimit = 5;
        List<ShangpinxinxiEntity> recomList = new ArrayList<>();

        for (String type : sortedCategories) {
            List<ShangpinxinxiEntity> typeList = shangpinxinxiService.selectList(
                    new EntityWrapper<ShangpinxinxiEntity>()
                            .eq(goodtypeColumn, type)
                            .eq("sfsh", "是")
                            .orderBy("click_number", false)  // 点击量高的优先
            );

            for (ShangpinxinxiEntity item : typeList) {
                if (recomList.size() >= perTypeLimit * sortedCategories.size()) break;
                if (recomList.stream().noneMatch(e -> e.getId().equals(item.getId()))) {
                    recomList.add(item);
                }
            }
        }

        // 5. 如果不足 limit 条，再按点击量补全
        params.putIfAbsent("limit", 10);
        params.putIfAbsent("page", "1");
        params.putIfAbsent("sort", "click_number");
        params.putIfAbsent("order", "desc");

        PageUtils page = shangpinxinxiService.queryPage(
                params,
                MPUtil.sort(
                        MPUtil.between(new EntityWrapper<ShangpinxinxiEntity>(), params),
                        params)
        );

        List<ShangpinxinxiEntity> fallback = (List<ShangpinxinxiEntity>) page.getList();
        int limit = Integer.parseInt(params.get("limit").toString());

        for (ShangpinxinxiEntity fb : fallback) {
            if (recomList.size() >= limit) break;
            boolean exists = recomList.stream().anyMatch(e -> e.getId().equals(fb.getId()));
            if (!exists) recomList.add(fb);
        }

        // 6. 最终只返回 limit 条
        page.setList(recomList.size() > limit ? recomList.subList(0, limit) : recomList);

        return Return.ok().put("data", page);
    }

}
