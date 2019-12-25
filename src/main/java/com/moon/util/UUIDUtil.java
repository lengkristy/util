/**
 * projectName: util
 * fileName: UUIDUtil.java
 * packageName: com.moon.util
 * date: 2019-11-12 11:49
 * copyright(c) 2017-2020 同方赛威讯信息技术公司
 */
package com.moon.util;

import java.util.UUID;

/**
 * @version: V1.0
 * @author: 代浩然
 * @className: UUIDUtil
 * @packageName: com.moon.util
 * @description: uuid工具类
 * @data: 2019-11-12 11:49
 **/
public class UUIDUtil {

    public static String create32UUID(){
        return UUID.randomUUID().toString().replace("-", "");// 把-替换为空
    }
}