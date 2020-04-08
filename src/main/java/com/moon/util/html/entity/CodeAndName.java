/**
 * projectName: util
 * fileName: CodeAndName.java
 * packageName: com.moon.util.html.entity
 * date: 2020-03-16 11:25
 * copyright(c) 2017-2020 同方赛威讯信息技术公司
 */
package com.moon.util.html.entity;

/**
 * @version: V1.0
 * @author: 代浩然
 * @className: CodeAndName
 * @packageName: com.moon.util.html.entity
 * @description: 代码名称
 * @data: 2020-03-16 11:25
 **/
public class CodeAndName {

    /**
     * 代码
     */
    private String code;

    /**
     * 父代码
     */
    private String parentCode;

    /**
     * 名称
     */
    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}