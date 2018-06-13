package cn.cloudx.importdata.tools.item;

import cn.cloudx.importdata.constant.ExcelTypeConstant;
import cn.cloudx.importdata.entity.item.*;
import cn.cloudx.importdata.repository.item.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Optional;

import static cn.cloudx.importdata.tools.parse.ParseExcel.loadImport;

/**
 * 导入项目信息到数据库中
 *
 * @author zhanghao
 * @date 2018/06/13
 */
@Component
@Slf4j
public class ImportItemToDataBase {

    private final ItemOrgInfoRepository itemOrgInfoRepository;
    private final ItemStatusRepository itemStatusRepository;
    private final ItemRepository itemRepository;
    private final ItemStructRepository itemStructRepository;
    private final InvBalancesRepository invBalancesRepository;
    private final InventoryRepository inventoryRepository;
    private final InvTransRepository invTransRepository;
    private final InvCostRepository invCostRepository;
    private final InvStatusRepository invStatusRepository;

    @Autowired
    public ImportItemToDataBase(ItemOrgInfoRepository itemOrgInfoRepository, ItemStatusRepository itemStatusRepository, ItemRepository itemRepository, ItemStructRepository itemStructRepository, InvBalancesRepository invBalancesRepository, InventoryRepository inventoryRepository, InvTransRepository invTransRepository, InvCostRepository invCostRepository, InvStatusRepository invStatusRepository) {
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


    @Autowired
    EntityManager entityManager;


    /**
     * @param itemNum     物资编码
     * @param description 物资描述
     * @param sModelNum   规格型号
     * @param orderUnit   订购计量单位
     * @param issueUnit   发放计量单位
     * @param location    仓库
     * @param siteId      地点
     */

    private void importItem(String itemNum, String description, String sModelNum, String orderUnit, String issueUnit, String location, String siteId) {


        //设置项目
        Item item = new Item();
        item.setItemnum(itemNum);
        item.setDescription(description);
        item.setSModelnum(sModelNum);
        item.setOrderunit(orderUnit);
        item.setIssueunit(issueUnit);
        //保存项目
        Item save = itemRepository.save(item);
        log.info("item{},{}导入成功", save.getItemnum(), save.getDescription());


        //设置项目状态
        Itemstatus itemstatus = new Itemstatus();
        itemstatus.setItemnum(itemNum);
        itemstatus.setItemstatusid(Integer.parseInt(itemNum));
        //保存项目状态
        itemStatusRepository.saveAndFlush(itemstatus);

        //设置库存状态
        Optional<Invstatus> optionalInvstatus = invStatusRepository.findByItemnum(itemNum);
        if (!optionalInvstatus.isPresent()) {
            Invstatus invstatus = new Invstatus();
            invstatus.setItemnum(itemNum);
            invstatus.setSiteid(siteId);
            invstatus.setInvstatusid(Integer.parseInt(itemNum));
//            entityManager.clear();
//            entityManager.flush();
            //保存库存状态
            invStatusRepository.saveAndFlush(invstatus);
        }


        //设置项目组织信息
        Itemorginfo itemorginfo = new Itemorginfo();
        itemorginfo.setItemnum(itemNum);
        //保存项目组织信息
        itemOrgInfoRepository.save(itemorginfo);


        //设置库存cost
        Invcost invcost = new Invcost();
        invcost.setItemnum(itemNum);
        invcost.setSiteid(siteId);
        invCostRepository.save(invcost);


        /*
         *设置 ItemStruct
         */
        Itemstruct itemstruct = new Itemstruct();
        itemstruct.setItemnum(itemNum);
        itemstruct.setItemid(itemNum);
        itemStructRepository.save(itemstruct);


        /*
         * 设置invBalances
         */
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


    public void startImport() {
        try {
            File file = ResourceUtils.getFile("classpath:" + "public/物资台帐.xlsx");
            FileInputStream fileInputStream4 = new FileInputStream(file);
            String[][] excels = loadImport(fileInputStream4, ExcelTypeConstant.XLSX, 1, 10);

            if (!StringUtils.isEmpty(excels)) {
                for (String[] rows : excels) {
                    String itemNum = rows[0];
                    String description = rows[1];
                    String modelNum = rows[2];
                    String orderUnit = rows[3];
                    String issueUnit = rows[4];
                    String siteId = "CNE340322F01";
                    if (StringUtils.hasText(itemNum)) {
                        importItem(itemNum, description, modelNum, orderUnit, issueUnit, "W10001", siteId);
                    } else {
                        break;
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
