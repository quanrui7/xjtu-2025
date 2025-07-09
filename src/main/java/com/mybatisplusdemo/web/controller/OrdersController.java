package com.mybatisplusdemo.web.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.mybatisplusdemo.assistant.IgnoreAuth;
import com.mybatisplusdemo.model.domain.OrdersEntity;
import com.mybatisplusdemo.model.dto.OrdersDTO;
import com.mybatisplusdemo.service.OrdersService;
import com.mybatisplusdemo.common.utils.MPUtil;
import com.mybatisplusdemo.common.utils.PageUtils;
import com.mybatisplusdemo.common.utils.Return;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;


@RestController
@RequestMapping("/orders")
public class OrdersController {
    @Autowired
    private OrdersService ordersService;


    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public Return page(@RequestParam Map<String, Object> params, OrdersEntity orders,
                       HttpServletRequest request) {
        String tableName = request.getSession().getAttribute("tableName").toString();
        if (tableName.equals("shangjia")) {
            orders.setShangjiazhanghao((String) request.getSession().getAttribute("username"));
            if (orders.getUserid() != null) {
                orders.setUserid(null);
            }
        } else {
            if (!request.getSession().getAttribute("role").toString().equals("管理员")) {
                orders.setUserid((Long) request.getSession().getAttribute("userId"));
            }
        }
        EntityWrapper<OrdersEntity> ew = new EntityWrapper<OrdersEntity>();


        PageUtils page = ordersService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, orders), params), params));
        return Return.ok().put("data", page);
    }


    /**
     * 前端列表
     */
    @IgnoreAuth
    @RequestMapping("/list")
    public Return list(@RequestParam Map<String, Object> params, OrdersEntity orders,
                       HttpServletRequest request) {
        EntityWrapper<OrdersEntity> ew = new EntityWrapper<OrdersEntity>();

        PageUtils page = ordersService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, orders), params), params));
        return Return.ok().put("data", page);
    }

    /**
     * 列表
     */
    @RequestMapping("/lists")
    public Return list(OrdersEntity orders) {
        EntityWrapper<OrdersEntity> ew = new EntityWrapper<OrdersEntity>();
        ew.allEq(MPUtil.allEQMapPre(orders, "orders"));
        return Return.ok().put("data", ordersService.selectListView(ew));
    }

    /**
     * 查询
     */
    @RequestMapping("/query")
    public Return query(OrdersEntity orders) {
        EntityWrapper<OrdersEntity> ew = new EntityWrapper<OrdersEntity>();
        ew.allEq(MPUtil.allEQMapPre(orders, "orders"));
        OrdersDTO ordersView = ordersService.selectView(ew);
        return Return.ok("查询商品订单成功").put("data", ordersView);
    }

    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public Return info(@PathVariable("id") Long id) {
        OrdersEntity orders = ordersService.selectById(id);
        orders = ordersService.selectView(new EntityWrapper<OrdersEntity>().eq("id", id));
        return Return.ok().put("data", orders);
    }

    /**
     * 前端详情
     */
    @IgnoreAuth
    @RequestMapping("/detail/{id}")
    public Return detail(@PathVariable("id") Long id) {
        OrdersEntity orders = ordersService.selectById(id);
        orders = ordersService.selectView(new EntityWrapper<OrdersEntity>().eq("id", id));
        return Return.ok().put("data", orders);
    }


    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public Return save(@RequestBody OrdersEntity orders, HttpServletRequest request) {
        //ValidatorUtils.validateEntity(orders);
        orders.setUserid((Long) request.getSession().getAttribute("userId"));
        ordersService.insert(orders);
        return Return.ok();
    }

    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public Return add(@RequestBody OrdersEntity orders, HttpServletRequest request) {
        //ValidatorUtils.validateEntity(orders);
        ordersService.insert(orders);
        return Return.ok();
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public Return update(@RequestBody OrdersEntity orders, HttpServletRequest request) {
        //ValidatorUtils.validateEntity(orders);
        ordersService.updateById(orders);//全部更新
        return Return.ok();
    }


    /**
     * 删除
     */
    @RequestMapping("/delete")
    public Return delete(@RequestBody Long[] ids) {
        ordersService.deleteBatchIds(Arrays.asList(ids));
        return Return.ok();
    }


    /**
     * （按值统计）
     */
    @RequestMapping("/value/{xColumnName}/{yColumnName}")
    public Return value(@PathVariable("yColumnName") String yColumnName, @PathVariable("xColumnName") String xColumnName, HttpServletRequest request) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("xColumn", MPUtil.camelToSnake(xColumnName));
        params.put("yColumn", MPUtil.camelToSnake(yColumnName));
        EntityWrapper<OrdersEntity> ew = new EntityWrapper<OrdersEntity>();
        String tableName = request.getSession().getAttribute("tableName").toString();
        if (tableName.equals("shangjia")) {
            ew.eq("shangjiazhanghao", (String) request.getSession().getAttribute("username"));
        }
        ew.in("status", new String[]{"已支付", "已发货", "已完成"}).ne("type", 2);
        List<Map<String, Object>> result = MPUtil.snakeListToCamel(ordersService.selectValue(params, ew));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (Map<String, Object> m : result) {
            for (String k : m.keySet()) {
                if (m.get(k) instanceof Date) {
                    m.put(k, sdf.format((Date) m.get(k)));
                }
            }
        }
        return Return.ok().put("data", result);
    }

    /**
     * （按值统计(多)）
     */
    @RequestMapping("/valueMul/{xColumnName}")
    public Return valueMul(@PathVariable("xColumnName") String xColumnName, @RequestParam String yColumnNameMul, HttpServletRequest request) {
        String[] yColumnNames = MPUtil.camelToSnake(yColumnNameMul).split(",");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("xColumn", MPUtil.camelToSnake(xColumnName));
        List<List<Map<String, Object>>> result2 = new ArrayList<List<Map<String, Object>>>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        EntityWrapper<OrdersEntity> ew = new EntityWrapper<OrdersEntity>();
        String tableName = request.getSession().getAttribute("tableName").toString();
        if (tableName.equals("shangjia")) {
            ew.eq("shangjiazhanghao", (String) request.getSession().getAttribute("username"));
        }
        for (int i = 0; i < yColumnNames.length; i++) {
            params.put("yColumn", yColumnNames[i]);
            List<Map<String, Object>> result = MPUtil.snakeListToCamel(ordersService.selectValue(params, ew));
            for (Map<String, Object> m : result) {
                for (String k : m.keySet()) {
                    if (m.get(k) instanceof Date) {
                        m.put(k, sdf.format((Date) m.get(k)));
                    }
                }
            }
            result2.add(result);
        }
        return Return.ok().put("data", result2);
    }

    /**
     * （按值统计）时间统计类型
     */
    @RequestMapping("/value/{xColumnName}/{yColumnName}/{timeStatType}")
    public Return valueDay(@PathVariable("yColumnName") String yColumnName, @PathVariable("xColumnName") String xColumnName, @PathVariable("timeStatType") String timeStatType, HttpServletRequest request) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("xColumn", MPUtil.camelToSnake(xColumnName));
        params.put("yColumn", MPUtil.camelToSnake(yColumnName));
        params.put("timeStatType", timeStatType);
        EntityWrapper<OrdersEntity> ew = new EntityWrapper<OrdersEntity>();
        String tableName = request.getSession().getAttribute("tableName").toString();
        if (tableName.equals("shangjia")) {
            ew.eq("shangjiazhanghao", (String) request.getSession().getAttribute("username"));
        }
        ew.in("status", new String[]{"已支付", "已发货", "已完成"}).ne("type", 2);
        List<Map<String, Object>> result = MPUtil.snakeListToCamel(ordersService.selectTimeStatValue(params, ew));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (Map<String, Object> m : result) {
            for (String k : m.keySet()) {
                if (m.get(k) instanceof Date) {
                    m.put(k, sdf.format((Date) m.get(k)));
                }
            }
        }
        return Return.ok().put("data", result);
    }

    /**
     * （按值统计）时间统计类型(多)
     */
    @RequestMapping("/valueMul/{xColumnName}/{timeStatType}")
    public Return valueMulDay(@PathVariable("xColumnName") String xColumnName, @PathVariable("timeStatType") String timeStatType, @RequestParam String yColumnNameMul, HttpServletRequest request) {
        String[] yColumnNames = MPUtil.camelToSnake(yColumnNameMul).split(",");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("xColumn", xColumnName);
        params.put("timeStatType", timeStatType);
        List<List<Map<String, Object>>> result2 = new ArrayList<List<Map<String, Object>>>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        EntityWrapper<OrdersEntity> ew = new EntityWrapper<OrdersEntity>();
        String tableName = request.getSession().getAttribute("tableName").toString();
        if (tableName.equals("shangjia")) {
            ew.eq("shangjiazhanghao", (String) request.getSession().getAttribute("username"));
        }
        for (int i = 0; i < yColumnNames.length; i++) {
            params.put("yColumn", yColumnNames[i]);
            List<Map<String, Object>> result = MPUtil.snakeListToCamel(ordersService.selectTimeStatValue(params, ew));
            for (Map<String, Object> m : result) {
                for (String k : m.keySet()) {
                    if (m.get(k) instanceof Date) {
                        m.put(k, sdf.format((Date) m.get(k)));
                    }
                }
            }
            result2.add(result);
        }
        return Return.ok().put("data", result2);
    }

    /**
     * 分组统计
     */
    @RequestMapping("/group/{columnName}")
    public Return group(@PathVariable("columnName") String columnName, HttpServletRequest request) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("column", MPUtil.camelToSnake(columnName));
        EntityWrapper<OrdersEntity> ew = new EntityWrapper<OrdersEntity>();
        String tableName = request.getSession().getAttribute("tableName").toString();
        if (tableName.equals("shangjia")) {
            ew.eq("shangjiazhanghao", (String) request.getSession().getAttribute("username"));
        }
        ew.in("status", new String[]{"已支付", "已发货", "已完成"}).ne("type", 2);
        List<Map<String, Object>> result = MPUtil.snakeListToCamel(ordersService.selectGroup(params, ew));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (Map<String, Object> m : result) {
            for (String k : m.keySet()) {
                if (m.get(k) instanceof Date) {
                    m.put(k, sdf.format((Date) m.get(k)));
                }
            }
        }
        return Return.ok().put("data", result);
    }


    /**
     * 总数量
     */
    @RequestMapping("/count")
    public Return count(@RequestParam Map<String, Object> params, OrdersEntity orders, HttpServletRequest request) {
        String tableName = request.getSession().getAttribute("tableName").toString();
        if (tableName.equals("shangjia")) {
            orders.setShangjiazhanghao((String) request.getSession().getAttribute("username"));
        }
        EntityWrapper<OrdersEntity> ew = new EntityWrapper<OrdersEntity>();
        int count = ordersService.selectCount(MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, orders), params), params));
        return Return.ok().put("data", count);
    }


}
