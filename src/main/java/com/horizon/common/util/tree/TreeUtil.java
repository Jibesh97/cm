package com.horizon.common.util.tree;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.horizon.common.util.StringUtil;
/**
 * <P>
 * FileName: TreeUtil.java
 * 
 * @author peiyy
 *         <P>
 *         CreateTime: 2012-10-08
 *         <P>
 *         Description:
 *         <P>
 *         Version:v1.0
 *         <P>
 *         History:
 */
public class TreeUtil {
	/**
     * 私有构造函数，工具类不允许通过new的方式创建对象
     */
    private TreeUtil() {
    }

    /**
     * 
     * <P>
     * Function: buildTreeList
     * <P>
     * Description:
     * <P>
     * Others:
     * 
	 * @author: peiyy
	 * @CreateTime: 2012-10-08
     * @param treeNodeList
     * @return List
     */
    public static List<TreeNodeBean> buildTreeList(List<TreeNodeBean> treeNodeList) {

        return buildTreeList(treeNodeList, "_blank");
    }

    /**
     * 
     * <P>
     * Function: buildTreeList
     * <P>
     * Description:
     * <P>
     * Others:
     * 
	 * @author: peiyy
	 * @CreateTime: 2012-10-08
     * @param treeNodeList
     * @return List
     */
    public static List<TreeNodeBean> buildTreeList(List<TreeNodeBean> treeNodeList,
            String target) {
        return buildTreeList(treeNodeList, target, "defaultRel");
    }

    /**
     * 
     * <P>
     * Function: buildTreeList
     * <P>
     * Description:
     * <P>
     * Others:
     * 
	 * @author: peiyy
	 * @CreateTime: 2012-10-08
     * @param treeNodeList
     * @return List
     */
    public static List<TreeNodeBean> buildTreeList(List<TreeNodeBean> treeNodeList,
            String target, String rel) {
        List<TreeNodeBean> treeList = new ArrayList<TreeNodeBean>();
        Map<String, TreeNodeBean> tempNodeMap = new HashMap<String, TreeNodeBean>();
        String tempKey = "";
        for (TreeNodeBean nodeBean : treeNodeList) {
            tempKey = nodeBean.getId();
            nodeBean.setTarget(target);
            nodeBean.setRel(rel);
            tempNodeMap.put(tempKey, nodeBean);
        }
        buildChildNode(treeNodeList, tempNodeMap);
        for (TreeNodeBean nodeBean : treeNodeList) {
            tempKey = nodeBean.getId();
            treeList.add(tempNodeMap.get(tempKey));
        }
        return treeList;
    }

    
    /**
     * <P>Function: buildDefault
     * <P>Description:
     * <P>Others:
	 * @author: peiyy
	 * @CreateTime: 2012-10-08
     * @param treeNodeList
     * @param target
     * @param rel
     * @return  List<TreeNodeBean>
     */
    public static List<TreeNodeBean> buildDefault(List<TreeNodeBean> treeNodeList
            ) {
        List<TreeNodeBean> treeList = new ArrayList<TreeNodeBean>();
        Map<String, TreeNodeBean> tempNodeMap = new HashMap<String, TreeNodeBean>();
        String tempKey = "";
        for (TreeNodeBean nodeBean : treeNodeList) {
            tempKey = nodeBean.getId();
            tempNodeMap.put(tempKey, nodeBean);
        }
        buildChildNode(treeNodeList, tempNodeMap);
        for (TreeNodeBean nodeBean : treeNodeList) {
            tempKey = nodeBean.getId();
            treeList.add(tempNodeMap.get(tempKey));
        }
        return treeList;
    }
    
    /**
     * 
     * <P>
     * Function: buildChildNode
     * <P>
     * Description:
     * <P>
     * Others:
     * 
	 * @author: peiyy
	 * @CreateTime: 2012-10-08
     * @param treeNodeList
     * @param treeList
     * @param tempMap
     *            void
     */
    private static void buildChildNode(List<TreeNodeBean> treeNodeList,
            Map<String, TreeNodeBean> tempMap) {
        String tempKey;
        for (int i = 0; i < treeNodeList.size(); i++) {
        	TreeNodeBean nodeBean = treeNodeList.get(i);
            tempKey = nodeBean.getPId();
            if (StringUtil.isEmpty(tempKey)) {
                continue;
            }
            TreeNodeBean tempParent = tempMap.get(tempKey);
            if (null == tempParent) {
                continue;
            } else {
                if (tempParent.getChildren() == null) {
                    tempParent.setChildren(new ArrayList<TreeNodeBean>());
                }
                tempParent.getChildren().add(nodeBean);
                treeNodeList.remove(nodeBean);
                i--;
            }
        }
    }
    
    /**
     * 判断TREE是否是结算单位
     * 
     * @param treeNodeList
     * @param basePath
     */
	public static List<TreeNodeBean> buildDiyPicture(
			List<TreeNodeBean> treeNodeList,String basePath) {
		List<TreeNodeBean> treeList = new ArrayList<TreeNodeBean>();
		Map<String, TreeNodeBean> tempNodeMap = new HashMap<String, TreeNodeBean>();
		String tempKey = "";
		for (TreeNodeBean nodeBean : treeNodeList) {
			if ("1".equals(nodeBean.getIcon()) && "1".equals(nodeBean.getIsMember())) {
				nodeBean.setIcon(basePath+"css/zTreeStyle/img/diy/11.png");
			} else if ("1".equals(nodeBean.getIcon()) && "0".equals(nodeBean.getIsMember())) {
				nodeBean.setIcon(basePath+"css/zTreeStyle/img/diy/01.png");
			} else if ("0".equals(nodeBean.getIcon()) && "1".equals(nodeBean.getIsMember())) {
				nodeBean.setIcon(basePath+"css/zTreeStyle/img/diy/10.png");
			} else {
				nodeBean.setIcon(basePath+"css/zTreeStyle/img/diy/00.png");
			}
			tempKey = nodeBean.getId();
			tempNodeMap.put(tempKey, nodeBean);
		}
		buildChildNode(treeNodeList, tempNodeMap);
		for (TreeNodeBean nodeBean : treeNodeList) {
			tempKey = nodeBean.getId();
			treeList.add(tempNodeMap.get(tempKey));
		}
		return treeList;
	}
}
