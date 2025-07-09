package com.mybatisplusdemo.web.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.mybatisplusdemo.assistant.IgnoreAuth;
import com.mybatisplusdemo.model.domain.NewsEntity;
import com.mybatisplusdemo.model.dto.NewsDTO;
import com.mybatisplusdemo.service.NewsService;
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
@RequestMapping("/news")
public class NewsController {
    @Autowired
    private NewsService newsService;


    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public Return page(@RequestParam Map<String, Object> params, NewsEntity news,
                       HttpServletRequest request) {
        EntityWrapper<NewsEntity> ew = new EntityWrapper<NewsEntity>();


        PageUtils page = newsService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, news), params), params));
        return Return.ok().put("data", page);
    }


    /**
     * 前端列表
     */
    @IgnoreAuth
    @RequestMapping("/list")
    public Return list(@RequestParam Map<String, Object> params, NewsEntity news,
                       HttpServletRequest request) {
        EntityWrapper<NewsEntity> ew = new EntityWrapper<NewsEntity>();

        PageUtils page = newsService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, news), params), params));
        return Return.ok().put("data", page);
    }

    /**
     * 列表
     */
    @RequestMapping("/lists")
    public Return list(NewsEntity news) {
        EntityWrapper<NewsEntity> ew = new EntityWrapper<NewsEntity>();
        ew.allEq(MPUtil.allEQMapPre(news, "news"));
        return Return.ok().put("data", newsService.selectListView(ew));
    }

    /**
     * 查询
     */
    @RequestMapping("/query")
    public Return query(NewsEntity news) {
        EntityWrapper<NewsEntity> ew = new EntityWrapper<NewsEntity>();
        ew.allEq(MPUtil.allEQMapPre(news, "news"));
        NewsDTO newsView = newsService.selectView(ew);
        return Return.ok("查询新品资讯成功").put("data", newsView);
    }

    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public Return info(@PathVariable("id") Long id) {
        NewsEntity news = newsService.selectById(id);
        news = newsService.selectView(new EntityWrapper<NewsEntity>().eq("id", id));
        return Return.ok().put("data", news);
    }

    /**
     * 前端详情
     */
    @IgnoreAuth
    @RequestMapping("/detail/{id}")
    public Return detail(@PathVariable("id") Long id) {
        NewsEntity news = newsService.selectById(id);
        news = newsService.selectView(new EntityWrapper<NewsEntity>().eq("id", id));
        return Return.ok().put("data", news);
    }


    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public Return save(@RequestBody NewsEntity news, HttpServletRequest request) {
        //ValidatorUtils.validateEntity(news);
        newsService.insert(news);
        return Return.ok();
    }

    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public Return add(@RequestBody NewsEntity news, HttpServletRequest request) {
        //ValidatorUtils.validateEntity(news);
        newsService.insert(news);
        return Return.ok();
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public Return update(@RequestBody NewsEntity news, HttpServletRequest request) {
        //ValidatorUtils.validateEntity(news);
        newsService.updateById(news);//全部更新
        return Return.ok();
    }


    /**
     * 删除
     */
    @RequestMapping("/delete")
    public Return delete(@RequestBody Long[] ids) {
        newsService.deleteBatchIds(Arrays.asList(ids));
        return Return.ok();
    }


}
