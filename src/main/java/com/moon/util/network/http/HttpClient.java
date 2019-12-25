/**
 * projectName: util
 * fileName: HttpClient.java
 * packageName: com.moon.util.network.http
 * date: 2019-11-22 9:46
 * copyright(c) 2017-2020 同方赛威讯信息技术公司
 */
package com.moon.util.network.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moon.util.StringUtil;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

/**
 * @version: V1.0
 * @author: 代浩然
 * @className: HttpClient
 * @packageName: com.moon.util.network.http
 * @description: http帮助类
 * @data: 2019-11-22 9:46
 **/
public class HttpClient {
    /**
     * 执行GET请求
     * @param urlStr 请求的url
     * @param params 请求的参数
     * @param heads 请求头
     * @return
     */
    public static String doGet(String urlStr, Map<String,String> params,Map<String,String> heads){
        HttpURLConnection connection = null;
        InputStream is = null;
        BufferedReader br = null;
        InputStreamReader isr = null;
        String result = null;// 返回结果字符串
        try {
            //设置请求参数
            boolean hasParam = urlStr.indexOf('?') == -1 ? false : true;
            if (params != null && params.keySet().size() > 0){
                for (String key:params.keySet()) {
                    String val = params.get(key);
                    if (!hasParam){
                        urlStr += "?";
                        urlStr += key;
                        urlStr += "=";
                        urlStr += val;
                        hasParam = true;
                    }else{
                        urlStr += "&";
                        urlStr += key;
                        urlStr += "=";
                        urlStr += val;
                    }
                }
            }
            // 创建远程url连接对象
            URL url = new URL(urlStr);
            // 通过远程url连接对象打开一个连接，强转成httpURLConnection类
            connection = (HttpURLConnection) url.openConnection();
            // 设置连接方式：get
            connection.setRequestMethod("GET");
            //设置请求头
            if (heads != null && heads.keySet().size() > 0){
                for (String key:heads.keySet()) {
                    String val = heads.get(key);
                    connection.setRequestProperty(key,val);
                }
            }
            // 设置连接主机服务器的超时时间：15000毫秒
            connection.setConnectTimeout(15000);
            // 设置读取远程返回的数据时间：60000毫秒
            connection.setReadTimeout(60000);

            // 发送请求
            connection.connect();
            // 通过connection连接，获取输入流
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                is = connection.getInputStream();
                // 封装输入流is，并指定字符集
                isr = new InputStreamReader(is, "UTF-8");
                br = new BufferedReader(isr);
                // 存放数据
                StringBuffer sbf = new StringBuffer();
                String temp = null;
                while ((temp = br.readLine()) != null) {
                    sbf.append(temp);
                    sbf.append("\r\n");
                }
                result = sbf.toString();
            } else{
                throw new IOException("请求异常,异常代码：" + responseCode);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (isr != null){
                try {
                    isr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            connection.disconnect();// 关闭远程连接
        }
        return StringUtil.empty();
    }

    /**
     * 执行POST请求
     * @param urlStr 请求的url
     * @param params 请求的参数
     * @param heads 请求头
     * @return
     */
    public static String doPost(String urlStr, Map<String,String> params,Map<String,String> heads){
        HttpURLConnection connection = null;
        InputStream is = null;
        OutputStream os = null;
        BufferedReader br = null;
        InputStreamReader isr = null;
        String result = null;
        try {
            URL url = new URL(urlStr);
            // 通过远程url连接对象打开连接
            connection = (HttpURLConnection) url.openConnection();
            // 设置连接请求方式
            connection.setRequestMethod("POST");
            // 设置连接主机服务器超时时间：15000毫秒
            connection.setConnectTimeout(15000);
            // 设置读取主机服务器返回数据超时时间：60000毫秒
            connection.setReadTimeout(60000);

            // 默认值为：false，当向远程服务器传送数据/写数据时，需要设置为true
            connection.setDoOutput(true);
            // 默认值为：true，当前向远程服务读取数据时，设置为true，该参数可有可无
            connection.setDoInput(true);
            // 设置传入参数的格式:请求参数应该是 name1=value1&name2=value2 的形式。
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            //设置请求头
            if (heads != null && heads.keySet().size() > 0){
                for (String key:heads.keySet()) {
                    String val = heads.get(key);
                    connection.setRequestProperty(key,val);
                }
            }
            // 通过连接对象获取一个输出流
            os = connection.getOutputStream();
            // 通过输出流对象将参数写出去/传输出去,它是通过字节数组写出的
            StringBuilder sb = new StringBuilder();
            if (params != null && params.keySet().size() > 0){
                boolean first = true;
                for (String key : params.keySet()){
                    if (!first){
                        sb.append("&");
                        sb.append(key);
                        sb.append("=");
                        sb.append(params.get(key));
                    }else {
                        sb.append(key);
                        sb.append("=");
                        sb.append(params.get(key));
                        first = false;
                    }
                }
            }
            os.write(sb.toString().getBytes());
            // 通过连接对象获取一个输入流，向远程读取
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                is = connection.getInputStream();
                // 对输入流对象进行包装:charset根据工作项目组的要求来设置
                br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                StringBuffer sbf = new StringBuffer();
                String temp = null;
                // 循环遍历一行一行读取数据
                while ((temp = br.readLine()) != null) {
                    sbf.append(temp);
                    sbf.append("\r\n");
                }
                result = sbf.toString();
            }else{
                throw new IOException("请求异常,异常代码：" + responseCode);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (isr != null){
                try{
                    isr.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != os) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            // 断开与远程地址url的连接
            connection.disconnect();
        }
        return result;
    }

    /**
     * 执行POST请求
     * @param urlStr
     * @param param
     * @param heads
     * @return
     */
    public static String doPost(String urlStr,Object param,Map<String,String> heads)throws Exception{
        HttpURLConnection connection = null;
        InputStream is = null;
        OutputStream os = null;
        BufferedReader br = null;
        InputStreamReader isr = null;
        String result = null;
        try {
            URL url = new URL(urlStr);
            // 通过远程url连接对象打开连接
            connection = (HttpURLConnection) url.openConnection();
            // 设置连接请求方式
            connection.setRequestMethod("POST");
            // 设置连接主机服务器超时时间：15000毫秒
            connection.setConnectTimeout(15000);
            // 设置读取主机服务器返回数据超时时间：60000毫秒
            connection.setReadTimeout(60000);

            // 默认值为：false，当向远程服务器传送数据/写数据时，需要设置为true
            connection.setDoOutput(true);
            // 默认值为：true，当前向远程服务读取数据时，设置为true，该参数可有可无
            connection.setDoInput(true);
            // 设置传入参数的格式:请求参数应该是 name1=value1&name2=value2 的形式。
            connection.setRequestProperty("Content-Type", "application/json");
            //设置请求头
            if (heads != null && heads.keySet().size() > 0){
                for (String key:heads.keySet()) {
                    String val = heads.get(key);
                    connection.setRequestProperty(key,val);
                }
            }
            // 通过连接对象获取一个输出流
            os = connection.getOutputStream();
            // 通过输出流对象将参数写出去/传输出去,它是通过字节数组写出的
            if (param != null) {
                ObjectMapper mapper = new ObjectMapper();
                String resultJson = mapper.writeValueAsString(param);
                os.write(resultJson.getBytes("UTF-8"));
            }
            // 通过连接对象获取一个输入流，向远程读取
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                is = connection.getInputStream();
                // 对输入流对象进行包装:charset根据工作项目组的要求来设置
                br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

                StringBuffer sbf = new StringBuffer();
                String temp = null;
                // 循环遍历一行一行读取数据
                while ((temp = br.readLine()) != null) {
                    sbf.append(temp);
                    sbf.append("\r\n");
                }
                result = sbf.toString();
            }else{
                throw new IOException("请求异常,异常代码：" + responseCode + "请求的URL:" + urlStr);
            }
        } catch (MalformedURLException e) {
            throw e;
        } catch (IOException e) {
            throw e;
        } finally {
            // 关闭资源
            if (isr != null){
                try{
                    isr.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != os) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            // 断开与远程地址url的连接
            connection.disconnect();
        }
        return result;
    }

    public static String doPost(String urlStr,Object param,Map<String,String> heads,int timeout)throws Exception{
        HttpURLConnection connection = null;
        InputStream is = null;
        OutputStream os = null;
        BufferedReader br = null;
        InputStreamReader isr = null;
        String result = null;
        try {
            URL url = new URL(urlStr);
            // 通过远程url连接对象打开连接
            connection = (HttpURLConnection) url.openConnection();
            // 设置连接请求方式
            connection.setRequestMethod("POST");
            // 设置连接主机服务器超时时间：15000毫秒
            connection.setConnectTimeout(15000);
            // 设置读取主机服务器返回数据超时时间：60000毫秒
            connection.setReadTimeout(timeout);

            // 默认值为：false，当向远程服务器传送数据/写数据时，需要设置为true
            connection.setDoOutput(true);
            // 默认值为：true，当前向远程服务读取数据时，设置为true，该参数可有可无
            connection.setDoInput(true);
            // 设置传入参数的格式:请求参数应该是 name1=value1&name2=value2 的形式。
            connection.setRequestProperty("Content-Type", "application/json");
            //设置请求头
            if (heads != null && heads.keySet().size() > 0){
                for (String key:heads.keySet()) {
                    String val = heads.get(key);
                    connection.setRequestProperty(key,val);
                }
            }
            // 通过连接对象获取一个输出流
            os = connection.getOutputStream();
            // 通过输出流对象将参数写出去/传输出去,它是通过字节数组写出的
            if (param != null) {
                ObjectMapper mapper = new ObjectMapper();
                String resultJson = mapper.writeValueAsString(param);
                os.write(resultJson.getBytes("UTF-8"));
            }
            // 通过连接对象获取一个输入流，向远程读取
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                is = connection.getInputStream();
                // 对输入流对象进行包装:charset根据工作项目组的要求来设置
                br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

                StringBuffer sbf = new StringBuffer();
                String temp = null;
                // 循环遍历一行一行读取数据
                while ((temp = br.readLine()) != null) {
                    sbf.append(temp);
                    sbf.append("\r\n");
                }
                result = sbf.toString();
            }else{
                throw new IOException("请求异常,异常代码：" + responseCode + "请求的URL:" + urlStr);
            }
        } catch (MalformedURLException e) {
            throw e;
        } catch (IOException e) {
            throw e;
        } finally {
            // 关闭资源
            if (isr != null){
                try{
                    isr.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != os) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            // 断开与远程地址url的连接
            connection.disconnect();
        }
        return result;
    }
}