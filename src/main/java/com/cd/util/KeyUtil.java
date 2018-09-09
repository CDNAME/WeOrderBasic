package com.cd.util;

import java.util.Random;

/**
 * Created by ChenDeng
 * 2018-08-23 21:50
 */
public class KeyUtil {

    /**
     * 生成唯一的主键
     * 格式：时间+随机数
     * @return
     */
    public static synchronized String getUniqueKey() {
        Random random = new Random();

        //生成6位随机数
        Integer number = random.nextInt(900000)+ 100000;
        
        return System.currentTimeMillis() + String.valueOf(number); 
    }
}
