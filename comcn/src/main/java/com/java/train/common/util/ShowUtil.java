package com.java.train.common.util;

import cn.hutool.core.util.IdUtil;

/**
 * 封装雪花算法，64bits
 */
public class ShowUtil {


    private static long dataCenterID=1;  // 数据中心

    private static long wordedId=1;     //  机器标识

    public  static  long getSnowflakeNextId(){
        return IdUtil.getSnowflake(wordedId,dataCenterID).nextId();
    }
    public  static  String getSnowflakeNextIdStr(){
        return IdUtil.getSnowflake(wordedId,dataCenterID).nextIdStr();
    }
}
