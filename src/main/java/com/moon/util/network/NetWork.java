/**
 * projectName: util
 * fileName: NetWork.java
 * packageName: com.moon.util.network
 * date: 2020-03-13 16:01
 * copyright(c) 2017-2020 同方赛威讯信息技术公司
 */
package com.moon.util.network;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 * @version: V1.0
 * @author: 代浩然
 * @className: NetWork
 * @packageName: com.moon.util.network
 * @description: 网络工具类
 * @data: 2020-03-13 16:01
 **/
public class NetWork {

    /**
     * 获取本机IP
     * @return
     */
    public static String getHostIp() {
        try {
            Enumeration allNetInterfaces = NetworkInterface.getNetworkInterfaces();

            while(allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = (NetworkInterface)allNetInterfaces.nextElement();
                Enumeration addresses = netInterface.getInetAddresses();

                while(addresses.hasMoreElements()) {
                    InetAddress ip = (InetAddress)addresses.nextElement();
                    if (ip != null && ip instanceof Inet4Address && !ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1) {
                        return ip.getHostAddress();
                    }
                }
            }
        } catch (Exception var5) {
            var5.printStackTrace();
        }

        return "127.0.0.1";
    }
}