/**
 * projectName: util
 * fileName: PathUtil.java
 * packageName: com.moon.util.io
 * date: 2020-03-02 15:23
 * copyright(c) 2017-2020 同方赛威讯信息技术公司
 */
package com.moon.util.io;

import com.moon.util.StringUtil;

import java.io.File;

/**
 * @version: V1.0
 * @author: 代浩然
 * @className: PathUtil
 * @packageName: com.moon.util.io
 * @description: 路径操作帮助类
 * @data: 2020-03-02 15:23
 **/
public class PathUtil {

    /**
     * 判断路径是否以分隔符结尾
     * @param path
     * @return
     */
    public static boolean jugeEndOfSeparator(String path){
        if (StringUtil.isEmpty(path)){
            return false;
        }
        if (path.endsWith("/") || path.endsWith("\\")){
            return true;
        }
        return false;
    }

    /**
     * 递归方式创建路径
     * @throws Exception
     */
    public static void createPath(String pathName)throws Exception{
        File filePath = new File(pathName);
        if (filePath.exists()){
            return;
        }
        String path = pathName;
        //获取上一级目录
        if (PathUtil.jugeEndOfSeparator(path)){
            path = path.substring(0,path.length() - 1);
        }
        int pos = -1;
        pos = path.lastIndexOf('/');
        if (pos == -1){
            pos = path.lastIndexOf('\\');
        }else{
            int p = path.lastIndexOf('\\'); //解决路径中即存在\\又存在/
            pos = pos > p ? pos : p;
        }
        if (pos == -1){
            throw new Exception("路径错误");
        }
        path = path.substring(0,pos);
        //判断上级路径存不存在，如果不存在，则创建
        File file = new File(path);
        if (!file.exists()){
            createPath(path);
        }
        file = new File(pathName);
        file.mkdir();
    }
}