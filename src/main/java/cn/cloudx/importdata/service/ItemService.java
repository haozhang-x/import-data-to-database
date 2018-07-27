package cn.cloudx.importdata.service;

import cn.cloudx.importdata.entity.item.Item;

/**
 * @author zhanghao
 * @date 2018/06/22
 */
public interface ItemService {

    /**
     * 导入物资项目
     *
     * @param itemNum          物资编码
     * @param description      物资描述
     * @param sModelNum        规格型号
     * @param orderUnit        订购计量单位
     * @param issueUnit        发放计量单位
     * @param location         仓库
     * @param siteId           地点
     * @param itemType         项目类别
     * @param classStructureId 分类编号
     */
    void importItem(String itemNum, String description, String sModelNum, String orderUnit, String issueUnit, String location, String siteId, String itemType, String classStructureId);

    /**
     * 导入库存项目
     *
     * @param itemNum     物资编码
     * @param description 物资描述
     * @param sModelNum   规格型号
     * @param orderUnit   订购计量单位
     * @param issueUnit   发放计量单位
     * @param location    仓库
     * @param siteId      地点
     * @param itemType    项目类别
     */
    void importInv(String itemNum, String description, String sModelNum, String orderUnit, String issueUnit, String location, String siteId, String itemType);


    /**
     * 删除所有的Item相关的信息
     */
    void deleteItemAll();


    /**
     * 删除所有的库存相关的信息
     */
    void deleteInvAll();


    /**
     * 查找一个物资编码
     *
     * @param itemNum itemNum
     * @return item
     */


    Item findOneItem(String itemNum);


    /**
     * 查找最大的ItemNum
     *
     * @param classStructureId classStructureId
     * @return Item
     */
    String findMaxItemNum(String classStructureId);


}

