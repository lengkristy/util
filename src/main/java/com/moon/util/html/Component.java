/**
 * projectName: util
 * fileName: Component.java
 * packageName: com.moon.util.html
 * date: 2020-03-16 10:51
 * copyright(c) 2017-2020 同方赛威讯信息技术公司
 */
package com.moon.util.html;

import com.moon.util.StringUtil;
import com.moon.util.html.entity.CodeAndName;
import com.moon.util.html.entity.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @version: V1.0
 * @author: 代浩然
 * @className: Component
 * @packageName: com.moon.util.html
 * @description: 生成组件数据
 * @data: 2020-03-16 10:51
 **/
public class Component {

    /**
     * 通过数据库查询出来的codeAndName集合生成前端所需要的树的对象
     * @param rootCode 根节点的代码，可为null，如果为null，根节点默认查询父节点为空的节点
     * @param codeAndNames 代码名称集合
     * @return
     */
    public static List<TreeNode> generateTreeNode(final String rootCode, List<CodeAndName> codeAndNames){
        List<TreeNode> treeNodes = new ArrayList<>();
        //判断传入的根节点是否为null
        List<CodeAndName> parentNodeList = null;
        if (StringUtil.isEmpty(rootCode)){
            //查询父节点为null的节点
            parentNodeList = codeAndNames.stream().filter(item -> StringUtil.isEmpty(item.getParentCode())).collect(Collectors.toList());
        }else{
            parentNodeList = codeAndNames.stream().filter(item -> item.getParentCode().equals(rootCode)).collect(Collectors.toList());
        }
        //创建父节点
        for (CodeAndName codeAndName:parentNodeList) {
            TreeNode pTreeNode = new TreeNode();
            treeNodes.add(pTreeNode);
            pTreeNode.setLabel(codeAndName.getName());
            pTreeNode.setValue(codeAndName.getCode());
            //寻找该节点的子节点
            List<CodeAndName> chirldrenNode = codeAndNames.stream().filter(item -> item.getParentCode().equals(codeAndName.getCode())).collect(Collectors.toList());
            if (chirldrenNode != null && chirldrenNode.size() > 0){
                pTreeNode.setChildren(new ArrayList<>());
                for (CodeAndName childCodeAndName:chirldrenNode) {
                    //首先将子节点添加到父节点集合中
                    TreeNode childNode = new TreeNode();
                    childNode.setValue(childCodeAndName.getCode());
                    childNode.setLabel(childCodeAndName.getName());
                    pTreeNode.getChildren().add(childNode);

                    //判断该节点是否有子节点，则递归调用
                    List<CodeAndName> parentNode = codeAndNames.stream().filter(item -> item.getParentCode().equals(childCodeAndName.getCode())).collect(Collectors.toList());
                    if (parentNode != null && parentNode.size() > 0){
                        childNode.setChildren(new ArrayList<>());
                        childNode.getChildren().addAll(generateTreeNode(childCodeAndName.getCode(),codeAndNames));
                    }
                }
            }
        }
        return treeNodes;
    }

}