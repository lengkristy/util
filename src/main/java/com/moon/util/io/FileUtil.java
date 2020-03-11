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

    /**
     * 通过覆盖的方式写文件内容
     * @param content 内容
     * @param filePathName 文件路径名称
     * @return 返回实际写的字符个数
     */
    public static void writeFileContentByCover(String content,String filePathName)throws Exception{
        PrintStream stream = null;
        try {
            stream = new PrintStream(filePathName);//写入的文件path
            stream.print(content);//写入的字符串
        } catch (FileNotFoundException e) {
            throw new Exception("写文件失败",e);
        }finally {
            if (stream != null){
                stream.close();
            }
        }
    }

    /**
     * 递归方式创建文件
     * @throws Exception
     */
    public static void createFile(String filePathName)throws Exception{
        //获取目录
        String filePath = "";
        int pos = -1;
        pos = filePathName.lastIndexOf('/');
        if (pos == -1){
            pos = filePathName.lastIndexOf('\\');
        }else{
            int p = filePathName.lastIndexOf('\\'); //解决路径中即存在\\又存在/
            pos = pos > p ? pos : p;
        }
        if (pos == -1){
            throw new Exception("路径错误");
        }
        filePath = filePathName.substring(0,pos);
        PathUtil.createPath(filePath);
        File file = new File(filePathName);
        if (!file.exists()){
            file.createNewFile();
        }
    }


}