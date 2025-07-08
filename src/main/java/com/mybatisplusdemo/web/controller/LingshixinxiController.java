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
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 零食信息
 * 后端接口
 *
 * @author
 * @email
 * @date 2025-02-15 13:47:52
 */
@RestController
@RequestMapping("/lingshixinxi")
public class LingshixinxiController {
    @Autowired
    private LingshixinxiService lingshixinxiService;

    @Autowired
    private StoreupService storeupService;

    @Autowired
    private OrdersService ordersService;


    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, LingshixinxiEntity lingshixinxi,
                  HttpServletRequest request) {
        String tableName = request.getSession().getAttribute("tableName").toString();
        if (tableName.equals("shangjia")) {
            lingshixinxi.setShangjiazhanghao((String) request.getSession().getAttribute("username"));
        }
        EntityWrapper<LingshixinxiEntity> ew = new EntityWrapper<LingshixinxiEntity>();


        PageUtils page = lingshixinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, lingshixinxi), params), params));
        return R.ok().put("data", page);
    }


    /**
     * 前端列表
     */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, LingshixinxiEntity lingshixinxi,
                  HttpServletRequest request) {
        EntityWrapper<LingshixinxiEntity> ew = new EntityWrapper<LingshixinxiEntity>();

        PageUtils page = lingshixinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, lingshixinxi), params), params));
        return R.ok().put("data", page);
    }

    /**
     * 列表
     */
    @RequestMapping("/lists")
    public R list(LingshixinxiEntity lingshixinxi) {
        EntityWrapper<LingshixinxiEntity> ew = new EntityWrapper<LingshixinxiEntity>();
        ew.allEq(MPUtil.allEQMapPre(lingshixinxi, "lingshixinxi"));
        return R.ok().put("data", lingshixinxiService.selectListView(ew));
    }

    /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(LingshixinxiEntity lingshixinxi) {
        EntityWrapper<LingshixinxiEntity> ew = new EntityWrapper<LingshixinxiEntity>();
        ew.allEq(MPUtil.allEQMapPre(lingshixinxi, "lingshixinxi"));
        LingshixinxiDTO lingshixinxiView = lingshixinxiService.selectView(ew);
        return R.ok("查询零食信息成功").put("data", lingshixinxiView);
    }

    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        LingshixinxiEntity lingshixinxi = lingshixinxiService.selectById(id);
        if (null == lingshixinxi.getClickNumber()) {
            lingshixinxi.setClickNumber(0);
        }
        lingshixinxi.setClickNumber(lingshixinxi.getClickNumber() + 1);
        lingshixinxi.setClicktime(new Date());
        lingshixinxiService.updateById(lingshixinxi);
        lingshixinxi = lingshixinxiService.selectView(new EntityWrapper<LingshixinxiEntity>().eq("id", id));
        return R.ok().put("data", lingshixinxi);
    }

    /**
     * 前端详情
     */
    @IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id) {
        LingshixinxiEntity lingshixinxi = lingshixinxiService.selectById(id);
        if (null == lingshixinxi.getClickNumber()) {
            lingshixinxi.setClickNumber(0);
        }
        lingshixinxi.setClickNumber(lingshixinxi.getClickNumber() + 1);
        lingshixinxi.setClicktime(new Date());
        lingshixinxiService.updateById(lingshixinxi);
        lingshixinxi = lingshixinxiService.selectView(new EntityWrapper<LingshixinxiEntity>().eq("id", id));
        return R.ok().put("data", lingshixinxi);
    }


    /**
     * 赞或踩
     */
    @RequestMapping("/thumbsup/{id}")
    public R vote(@PathVariable("id") String id, String type) {
        LingshixinxiEntity lingshixinxi = lingshixinxiService.selectById(id);
        if (type.equals("1")) {
            lingshixinxi.setThumbsupNumber(lingshixinxi.getThumbsupNumber() + 1);
        } else {
            lingshixinxi.setCrazilyNumber(lingshixinxi.getCrazilyNumber() + 1);
        }
        lingshixinxiService.updateById(lingshixinxi);
        return R.ok("投票成功");
    }

    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody LingshixinxiEntity lingshixinxi, HttpServletRequest request) {
        //ValidatorUtils.validateEntity(lingshixinxi);
        lingshixinxiService.insert(lingshixinxi);
        return R.ok();
    }

    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody LingshixinxiEntity lingshixinxi, HttpServletRequest request) {
        //ValidatorUtils.validateEntity(lingshixinxi);
        lingshixinxiService.insert(lingshixinxi);
        return R.ok();
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody LingshixinxiEntity lingshixinxi, HttpServletRequest request) {
        //ValidatorUtils.validateEntity(lingshixinxi);
        lingshixinxiService.updateById(lingshixinxi);//全部更新
        return R.ok();
    }

    /**
     * 审核
     */
    @RequestMapping("/shBatch")
    @Transactional
    public R update(@RequestBody Long[] ids, @RequestParam String sfsh, @RequestParam String shhf) {
        List<LingshixinxiEntity> list = new ArrayList<LingshixinxiEntity>();
        for (Long id : ids) {
            LingshixinxiEntity lingshixinxi = lingshixinxiService.selectById(id);
            lingshixinxi.setSfsh(sfsh);
            lingshixinxi.setShhf(shhf);
            list.add(lingshixinxi);
        }
        lingshixinxiService.updateBatchById(list);
        return R.ok();
    }


    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids) {
        lingshixinxiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }


    /**
     * 前端智能排序
     */
    @IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params, LingshixinxiEntity lingshixinxi, HttpServletRequest request, String pre) {
        EntityWrapper<LingshixinxiEntity> ew = new EntityWrapper<LingshixinxiEntity>();
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
        params.put("sort", "click_number");
        params.put("order", "desc");
        PageUtils page = lingshixinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, lingshixinxi), params), params));
        return R.ok().put("data", page);
    }

    /**
     * 按用户购买推荐
     */
    @RequestMapping("/autoSort2")
    public R autoSort2(@RequestParam Map<String, Object> params, LingshixinxiEntity lingshixinxi, HttpServletRequest request) {
        String userId = request.getSession().getAttribute("userId").toString();
        String goodtypeColumn = "lingshifenlei";
        List<OrdersEntity> orders = ordersService.selectList(new EntityWrapper<OrdersEntity>().eq("userid", userId).eq("tablename", "lingshixinxi").orderBy("addtime", false));
        List<String> goodtypes = new ArrayList<String>();
        Integer limit = params.get("limit") == null ? 10 : Integer.parseInt(params.get("limit").toString());
        List<LingshixinxiEntity> lingshixinxiList = new ArrayList<LingshixinxiEntity>();
        //去重
        List<OrdersEntity> ordersDist = new ArrayList<OrdersEntity>();
        for (OrdersEntity o1 : orders) {
            boolean addFlag = true;
            for (OrdersEntity o2 : ordersDist) {
                if (o1.getGoodid() == o2.getGoodid() || o1.getGoodtype().equals(o2.getGoodtype())) {
                    addFlag = false;
                    break;
                }
            }
            if (addFlag) ordersDist.add(o1);
        }
        if (ordersDist != null && ordersDist.size() > 0) {
            for (OrdersEntity o : ordersDist) {
                lingshixinxiList.addAll(lingshixinxiService.selectList(new EntityWrapper<LingshixinxiEntity>().eq(goodtypeColumn, o.getGoodtype())));
            }
        }
        EntityWrapper<LingshixinxiEntity> ew = new EntityWrapper<LingshixinxiEntity>();
        params.put("sort", "id");
        params.put("order", "desc");
        PageUtils page = lingshixinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, lingshixinxi), params), params));
        List<LingshixinxiEntity> pageList = (List<LingshixinxiEntity>) page.getList();
        if (lingshixinxiList.size() < limit) {
            int toAddNum = (limit - lingshixinxiList.size()) <= pageList.size() ? (limit - lingshixinxiList.size()) : pageList.size();
            for (LingshixinxiEntity o1 : pageList) {
                boolean addFlag = true;
                for (LingshixinxiEntity o2 : lingshixinxiList) {
                    if (o1.getId().intValue() == o2.getId().intValue()) {
                        addFlag = false;
                        break;
                    }
                }
                if (addFlag) {
                    lingshixinxiList.add(o1);
                    if (--toAddNum == 0) break;
                }
            }
        } else if (lingshixinxiList.size() > limit) {
            lingshixinxiList = lingshixinxiList.subList(0, limit);
        }
        page.setList(lingshixinxiList);
        return R.ok().put("data", page);
    }


}
