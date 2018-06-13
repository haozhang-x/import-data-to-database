package cn.cloudx.importdata;

import cn.cloudx.importdata.entity.item.*;
import cn.cloudx.importdata.repository.item.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 导入项目信息到数据库中
 *
 * @author zhanghao
 * @date 2018/06/13
 */
@Component
@Slf4j
public class ImportItemToDataBase {
    private final InvCostRepository invCostRepository;
    private final InvStatusRepository invStatusRepository;
    private final ItemOrgInfoRepository itemOrgInfoRepository;
    private final ItemStatusRepository itemStatusRepository;
    private final ItemRepository itemRepository;


    @Autowired
    public ImportItemToDataBase(InvCostRepository invCostRepository, InvStatusRepository invStatusRepository, ItemOrgInfoRepository itemOrgInfoRepository, ItemStatusRepository itemStatusRepository, ItemRepository itemRepository) {
        this.invCostRepository = invCostRepository;
        this.invStatusRepository = invStatusRepository;
        this.itemOrgInfoRepository = itemOrgInfoRepository;
        this.itemStatusRepository = itemStatusRepository;
        this.itemRepository = itemRepository;
    }


    /**
     * @param itemNum     物资编码
     * @param description 物资描述
     * @param sModelNum   规格型号
     * @param orderUnit   订购计量单位
     * @param issueUnit   发放计量单位
     */

    private void importItem(String itemNum, String description, String sModelNum, String orderUnit, String issueUnit) {


        //设置项目
        Item item = new Item();
        item.setItemnum(itemNum);
        item.setDescription(description);
        item.setSModelnum(sModelNum);
        item.setOrderunit(orderUnit);
        item.setIssueunit(issueUnit);
        //保存项目
        itemRepository.save(item);


        //设置项目状态
        Itemstatus itemstatus = new Itemstatus();
        itemstatus.setItemnum(itemNum);
        //保存项目状态
        itemStatusRepository.save(itemstatus);


        //设置库存状态
        Invstatus invstatus = new Invstatus();
        invstatus.setItemnum(itemNum);
        //保存库存状态
        invStatusRepository.save(invstatus);


        //设置项目组织信息
        Itemorginfo itemorginfo = new Itemorginfo();
        itemorginfo.setItemnum(itemNum);
        //保存项目组织信息
        itemOrgInfoRepository.save(itemorginfo);


        Invcost invcost = new Invcost();
        invcost.setItemnum(itemNum);
        invCostRepository.save(invcost);


    }


}
