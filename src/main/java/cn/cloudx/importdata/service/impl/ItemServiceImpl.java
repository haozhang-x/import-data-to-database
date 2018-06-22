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

    @Override
    public void importItem(String itemNum, String description, String sModelNum, String orderUnit, String issueUnit, String location, String siteId, String itemType) {

        //设置项目
        Item item = new Item();
        item.setItemnum(itemNum);
        item.setDescription(description);
        item.setSModelnum(sModelNum);
        item.setOrderunit(orderUnit);
        item.setIssueunit(issueUnit);

        if (itemType != null) {
            item.setItemtype(itemType);
        }

        //保存项目
        Item save = itemRepository.save(item);
        log.info("item{},{}导入成功", save.getItemnum(), save.getDescription());


        //设置项目状态
        Itemstatus itemstatus = new Itemstatus();
        itemstatus.setItemnum(itemNum);
        itemstatus.setItemstatusid(itemStatusId++);
        //保存项目状态
        itemStatusRepository.save(itemstatus);


        //设置项目组织信息
        Itemorginfo itemorginfo = new Itemorginfo();
        itemorginfo.setItemnum(itemNum);
        //保存项目组织信息
        itemOrgInfoRepository.save(itemorginfo);




        /*
         *设置 ItemStruct
         */

        Itemstruct itemstruct = new Itemstruct();
        itemstruct.setItemnum(itemNum);
        itemstruct.setItemid(itemNum);
        itemStructRepository.save(itemstruct);


    }

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
        inventoryRepository.save(inventory);



        /*
         *设置invTrans
         */
        Invtrans invtrans = new Invtrans();
        invtrans.setItemnum(itemNum);
        invtrans.setStoreloc(location);
        invtrans.setSiteid(siteId);
        invTransRepository.save(invtrans);
    }

    @Override
    public void deleteItemAll() {
        itemOrgInfoRepository.deleteAllInBatch();
        itemStatusRepository.deleteAllInBatch();
        itemRepository.deleteAllInBatch();
        itemStructRepository.deleteAllInBatch();
    }

    @Override
    public void deleteInvAll() {
        invBalancesRepository.deleteAllInBatch();
        inventoryRepository.deleteAllInBatch();
        invTransRepository.deleteAllInBatch();
        invCostRepository.deleteAllInBatch();
        invStatusRepository.deleteAllInBatch();
    }
}
