/**
 * projectName: util
 * fileName: FileUtil.java
 * packageName: com.moon.util.io
 * date: 2019-12-06 11:39
 * copyright(c) 2017-2020 同方赛威讯信息技术公司
 */
package com.moon.util.io;

import java.io.*;

/**
 * @version: V1.0
 * @author: 代浩然
 * @className: FileUtil
 * @packageName: com.moon.util.io
 * @description: 文件帮助类
 * @data: 2019-12-06 11:39
 **/
public class FileUtil {

    /**
     * 读取文件内容
     * @param filePathName
     * @return
     */
    public static String readFileContent(String filePathName){
        File file = new File(filePathName);
        BufferedReader reader = null;
        StringBuffer sbf = new StringBuffer();
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempStr;
            while ((tempStr = reader.readLine()) != null) {
                sbf.append(tempStr);
            }
            return sbf.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return sbf.toString();
    }
}