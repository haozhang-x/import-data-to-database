package cn.cloudx.importdata.service.impl;

import cn.cloudx.importdata.entity.item.*;
import cn.cloudx.importdata.repository.item.*;
import cn.cloudx.importdata.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 项目服务的实现类
 *
 * @author zhanghao
 * @date 2018/06/22
 */
@Service
@Slf4j
public class ItemServiceImpl implements ItemService {


    private final ItemOrgInfoRepository itemOrgInfoRepository;
    private final ItemStatusRepository itemStatusRepository;
    private final ItemRepository itemRepository;
    private final ItemStructRepository itemStructRepository;
    private final InvBalancesRepository invBalancesRepository;
    private final InventoryRepository inventoryRepository;
    private final InvTransRepository invTransRepository;
    private final InvCostRepository invCostRepository;
    private final InvStatusRepository invStatusRepository;
    private Integer itemStatusId = 0;

    @Autowired
    public ItemServiceImpl(ItemOrgInfoRepository itemOrgInfoRepository, ItemStatusRepository itemStatusRepository, ItemRepository itemRepository, ItemStructRepository itemStructRepository, InvBalancesRepository invBalancesRepository, InventoryRepository inventoryRepository, InvTransRepository invTransRepository, InvCostRepository invCostRepository, InvStatusRepository invStatusRepository) {
        this.itemOrgInfoRepository = itemOrgInfoRepository;
        this.itemStatusRepository = itemStatusRepository;
        this.itemRepository = itemRepository;
        this.itemStructRepository = itemStructRepository;
        this.invBalancesRepository = invBalancesRepository;
        this.inventoryRepository = inventoryRepository;
        this.invTransRepository = invTransRepository;
        this.invCostRepository = invCostRepository;
        this.invStatusRepository = invStatusRepository;
    }


    /**
     * 导入项目信息
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
    @Override
    public void importItem(String itemNum, String description, String sModelNum, String orderUnit, String issueUnit, String location, String siteId, String itemType, String classStructureId) {

        //设置项目
        Item item = new Item();
        //设置物资编码
        item.setItemnum(itemNum);
        //设置描述
        item.setDescription(description);
        //设置规格型号
        item.setSModelnum(sModelNum);
        //设置订购单位
        item.setOrderunit(orderUnit);
        //设置发放单位
        item.setIssueunit(issueUnit);
        //设置分类编号
        item.setClassstructureid(classStructureId);
        //如果为空的话，设置ItemType为默认值
        if (itemType != null) {
            item.setItemtype(itemType);
        }

        log.info("item info{},{}", item.getItemnum(), item.getDescription());

        //保存项目
        Item save = itemRepository.save(item);
        //输出导入成功的语句
        log.info("item{},{}导入成功", save.getItemnum(), save.getDescription());


        //设置项目状态
        Itemstatus itemstatus = new Itemstatus();
        //设置物资编码
        itemstatus.setItemnum(itemNum);
//        //设置id
//        itemstatus.setItemstatusid(itemStatusId++);
        //保存项目状态
        itemStatusRepository.save(itemstatus);


        //设置项目组织信息
        Itemorginfo itemorginfo = new Itemorginfo();
        //设置物资编码信息
        itemorginfo.setItemnum(itemNum);
        //保存项目组织信息
        itemOrgInfoRepository.save(itemorginfo);


        //设置 ItemStruct
        Itemstruct itemstruct = new Itemstruct();
        //设置物资编码
        itemstruct.setItemnum(itemNum);
        //设置id
        itemstruct.setItemid(itemNum);
        itemStructRepository.save(itemstruct);


    }


    /**
     * 导入库存信息
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

    @Override
    public void importInv(String itemNum, String description, String sModelNum, String orderUnit, String issueUnit, String location, String siteId, String itemType) {

        //设置库存状态
        Invstatus invstatus = new Invstatus();
        invstatus.setItemnum(itemNum);
        invstatus.setSiteid(siteId);
        //保存库存状态
        invStatusRepository.save(invstatus);


        //设置库存cost
        Invcost invcost = new Invcost();
        invcost.setItemnum(itemNum);
        invcost.setSiteid(siteId);
        invcost.setLocation(location);
        invCostRepository.save(invcost);


        //设置invBalances
        Invbalances invbalances = new Invbalances();
        invbalances.setItemnum(itemNum);
        invbalances.setLocation(location);
        invbalances.setSiteid(siteId);

        invBalancesRepository.save(invbalances);



        /*
         * 设置库存
         */
        Inventory inventory = new Inventory();
        inventory.setItemnum(itemNum);
        inventory.setOrderunit(orderUnit);
        inventory.setIssueunit(issueUnit);
        inventory.setSiteid(siteId);
        inventory.setLocation(location);
        Inventory save = inventoryRepository.save(inventory);
        log.info("ITEM={}已导入库存", save.getItemnum());





        /*
         *设置invTrans
         */
        Invtrans invtrans = new Invtrans();
        invtrans.setItemnum(itemNum);
        invtrans.setStoreloc(location);
        invtrans.setSiteid(siteId);
        invTransRepository.save(invtrans);
    }


    /**
     * 删除所有的项目
     */

    @Override
    public void deleteItemAll() {
        itemOrgInfoRepository.deleteAllInBatch();
        itemStatusRepository.deleteAllInBatch();
        itemRepository.deleteAllInBatch();
        itemStructRepository.deleteAllInBatch();
    }


    /**
     * 删除所有的库存信息
     */
    @Override
    public void deleteInvAll() {
        invBalancesRepository.deleteAllInBatch();
        inventoryRepository.deleteAllInBatch();
        invTransRepository.deleteAllInBatch();
        invCostRepository.deleteAllInBatch();
        invStatusRepository.deleteAllInBatch();
    }

    @Override
    public Item findOneItem(String itemNum) {
        return itemRepository.findByItemnum(itemNum);
    }

    @Override
    public String findMaxItemNum(String classStructureId) {
        return itemRepository.findMaxItemNum(classStructureId);
    }


}
