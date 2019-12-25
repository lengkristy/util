/**
 * projectName: util
 * fileName: StringUtil.java
 * packageName: com.moon.util
 * date: 2019-11-11 11:20
 * copyright(c) 2017-2020 同方赛威讯信息技术公司
 */
package com.moon.util;

/**
 * @version: V1.0
 * @author: 代浩然
 * @className: StringUtil
 * @packageName: com.moon.util
 * @description: 字符串工具类
 * @data: 2019-11-11 11:20
 **/
public class StringUtil {

    /**
     * @description: 判断字符串是否为空
     * @param str 源字符串
     * @return 如果为空返回true，如果不为空返回，false
     */
    public static boolean isEmpty(String str){
        if (str == null || "".equals(str)){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 字符串置空
     * @return
     */
    public static String empty(){
        return "";
    }

}