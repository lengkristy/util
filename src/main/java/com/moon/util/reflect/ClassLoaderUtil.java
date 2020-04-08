/**
 * projectName: util
 * fileName: ClassLoaderUtil.java
 * packageName: com.moon.util.reflect
 * date: 2020-04-08 15:17
 * copyright(c) 2017-2020 同方赛威讯信息技术公司
 */
package com.moon.util.reflect;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @version: V1.0
 * @author: 代浩然
 * @className: ClassLoaderUtil
 * @packageName: com.moon.util.reflect
 * @description: 类加载工具
 * @data: 2020-04-08 15:17
 **/
public class ClassLoaderUtil {

    /**
     * 从jar包文件中加载所有的类
     * @param path
     * @return
     */
    public static List<Class> loadClassFromJarFile(String path)throws Exception{
        List<Class> clsArray = new ArrayList<>();
        try{
            JarFile jar = new JarFile(path);
            Enumeration e = jar.entries();
            while (e.hasMoreElements()) {
                JarEntry entry = (JarEntry) e.nextElement();
                if (entry.getName().indexOf("META-INF") < 0) {
                    String sName = entry.getName();//类名
                    String substr[] = sName.split("/");
                    String pName = ""; //包名
                    for (int i = 0; i < substr.length - 1; i++) {
                        if (i > 0)
                            pName = pName + "/" + substr[i];
                        else
                            pName = substr[i];
                    }
                    if (sName.indexOf(".class") < 0) {
                        sName = sName.substring(0, sName.length() - 1);
                    } else {
                        //通过URLClassLoader.loadClass方法得到具体某个类
                        URL url = new URL("file:" + path);
                        URLClassLoader myClassLoader = new URLClassLoader(new URL[]{url}, Thread.currentThread().getContextClassLoader());
                        String ppName = sName.replace("/", ".").replace(".class", "");
                        Class cls = myClassLoader.loadClass(ppName);
                        //
                        clsArray.add(cls);
                    }
                }
            }
        }catch (Exception ex){
            throw ex;
        }
        return clsArray;
    }

    public static void main(String[] argvs){
        try {
            loadClassFromJarFile("E:\\idea_project\\xsznht\\plugins\\general-data-exchange-plugin.jar");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}