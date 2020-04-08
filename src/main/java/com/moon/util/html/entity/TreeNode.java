/**
 * projectName: util
 * fileName: TreeNode.java
 * packageName: com.moon.util.html.entity
 * date: 2020-03-16 10:52
 * copyright(c) 2017-2020 同方赛威讯信息技术公司
 */
package com.moon.util.html.entity;

import java.util.List;

/**
 * @version: V1.0
 * @author: 代浩然
 * @className: TreeNode
 * @packageName: com.moon.util.html.entity
 * @description: 树节点数据
 * @data: 2020-03-16 10:52
 **/
public class TreeNode {

    /**代码值*/
    private String value;

    /**
     * 标签名称
     */
    private String label;

    /**
     * 父节点
     */
    private List<TreeNode> children;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }
}