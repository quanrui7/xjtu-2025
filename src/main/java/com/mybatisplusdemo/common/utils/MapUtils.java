package com.mybatisplusdemo.common.utils;

import java.util.HashMap;



public class MapUtils extends HashMap<String, Object> {

    @Override
    public MapUtils put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
