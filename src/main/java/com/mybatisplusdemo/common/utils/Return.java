package com.mybatisplusdemo.common.utils;

import java.util.HashMap;
import java.util.Map;

//返回数据
public class Return extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    public Return() {
        put("code", 0);
    }

    public static Return error() {
        return error(500, "未知异常，请联系管理员");
    }

    public static Return error(String msg) {
        return error(500, msg);
    }

    public static Return error(int code, String msg) {
        Return r = new Return();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public static Return ok(String msg) {
        Return r = new Return();
        r.put("msg", msg);
        return r;
    }

    public static Return ok(Map<String, Object> map) {
        Return r = new Return();
        r.putAll(map);
        return r;
    }

    public static Return ok() {
        return new Return();
    }

    public Return put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
