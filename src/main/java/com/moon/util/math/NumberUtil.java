/**
 * projectName: util
 * fileName: NumberUtil.java
 * packageName: com.moon.util.math
 * date: 2019-12-05 14:18
 * copyright(c) 2017-2020 同方赛威讯信息技术公司
 */
package com.moon.util.math;

/**
 * @version: V1.0
 * @author: 代浩然
 * @className: NumberUtil
 * @packageName: com.moon.util.math
 * @description: 数字转换类
 * @data: 2019-12-05 14:18
 **/
public class NumberUtil {

    /**
     * 将中文小写数字转成阿拉伯数字
     * 如：一转成1
     * @param chiniseNumber 中文数字
     * @return
     */
    public static int ChiniseToArabicNumber(char chiniseNumber)throws Exception{
        int number = -1;
        switch (chiniseNumber){
            case '〇':
                number = 0;
                break;
            case '一':
                number = 1;
                break;
            case '二':
                number = 2;
                break;
            case '三':
                number = 3;
                break;
            case '四':
                number = 4;
                break;
            case '五':
                number = 5;
                break;
            case '六':
                number = 6;
                break;
            case '七':
                number = 7;
                break;
            case '八':
                number = 8;
                break;
            case '九':
                number = 9;
                break;
            case '十':
                number = 10;
                break;
            default:
                throw new Exception("将中文数字[" + chiniseNumber + "]转成阿拉伯数字失败");
        }
        return  number;
    }
}