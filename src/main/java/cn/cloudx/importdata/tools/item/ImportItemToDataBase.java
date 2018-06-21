package cn.cloudx.importdata.tools.item;

import cn.cloudx.importdata.constant.ExcelTypeConstant;
import cn.cloudx.importdata.constant.ItemTypeConstant;
import cn.cloudx.importdata.entity.item.*;
import cn.cloudx.importdata.repository.item.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
@Service
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
    private EntityManager entityManager;


    /**
     * @param itemNum     物资编码
     * @param description 物资描述
     * @param sModelNum   规格型号
     * @param orderUnit   订购计量单位
     * @param issueUnit   发放计量单位
     * @param location    仓库
     * @param siteId      地点
     */

    private int itemStatus = 1000;

    private void importItem(String itemNum, String description, String sModelNum, String orderUnit, String issueUnit, String location, String siteId, String ItemType) {


        //设置项目
        Item item = new Item();
        item.setItemnum(itemNum);
        item.setDescription(description);
        item.setSModelnum(sModelNum);
        item.setOrderunit(orderUnit);
        item.setIssueunit(issueUnit);

        if (ItemType != null) {
            item.setItemtype(ItemType);
        }

        //保存项目
        Item save = itemRepository.save(item);
        log.info("item{},{}导入成功", save.getItemnum(), save.getDescription());


        //设置项目状态
        Itemstatus itemstatus = new Itemstatus();
        itemstatus.setItemnum(itemNum);
        itemstatus.setItemstatusid(itemStatus++);
        //保存项目状态
        itemStatusRepository.save(itemstatus);


        //设置库存状态
        Optional<Invstatus> optionalInvstatus = invStatusRepository.findByItemnum(itemNum);
        if (!optionalInvstatus.isPresent()) {
            Invstatus invstatus = new Invstatus();
            invstatus.setItemnum(itemNum);
            invstatus.setSiteid(siteId);
            //保存库存状态
            invStatusRepository.save(invstatus);
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

        entityManager.clear();

    }


    /**
     * 项目导入
     */


    public void startItemImport() {
        try {
            File file = ResourceUtils.getFile("classpath:" + "public/五河项目备品备件.xlsx");
            FileInputStream fileInputStream = new FileInputStream(file);
            String[][] excels = loadImport(fileInputStream, ExcelTypeConstant.XLSX, 1, 10);
            if (!StringUtils.isEmpty(excels)) {
                for (String[] rows : excels) {
                    String itemNum = rows[0];
                    String description = rows[1];
                    String modelNum = rows[2];
                    String orderUnit = rows[3];
                    String issueUnit = rows[4];
                    String siteId = "CNE340322F01";
                    if (StringUtils.hasText(itemNum)) {
                        importItem(itemNum, description, modelNum, orderUnit, issueUnit, "W10001", siteId, null);
                    } else {
                        break;
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 工器具导入
     */

    public void startToolsItemImport() {
        try {
            File file = ResourceUtils.getFile("classpath:" + "public/五河安全工器具.xlsx");
            FileInputStream fileInputStream = new FileInputStream(file);
            String[][] excels = loadImport(fileInputStream, ExcelTypeConstant.XLSX, 1, 10);
            if (!StringUtils.isEmpty(excels)) {
                for (String[] rows : excels) {
                    String itemNum = rows[0];
                    String description = rows[1];
                    String modelNum = rows[2];
                    String orderUnit = rows[3];
                    String issueUnit = rows[4];
                    String siteId = "CNE340322F01";
                    if (StringUtils.hasText(itemNum)) {
                        importItem(itemNum, description, modelNum, orderUnit, issueUnit, "W10001", siteId, ItemTypeConstant.TOOL);
                    } else {
                        break;
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 删除所有的物资数据
     */

    public void deleteAll() {
        itemOrgInfoRepository.deleteAllInBatch();
        itemStatusRepository.deleteAllInBatch();
        itemRepository.deleteAllInBatch();
        itemStructRepository.deleteAllInBatch();
        invBalancesRepository.deleteAllInBatch();
        inventoryRepository.deleteAllInBatch();
        invTransRepository.deleteAllInBatch();
        invCostRepository.deleteAllInBatch();
        invStatusRepository.deleteAllInBatch();
    }


}
