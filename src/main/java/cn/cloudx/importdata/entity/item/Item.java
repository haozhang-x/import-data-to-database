package cn.cloudx.importdata.entity.item;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * 项目表
 *
 * @author zhang
 */
@Entity
@Data
@SequenceGenerator(name = "itemid", sequenceName = "itemseq", allocationSize = 1)
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "itemid")
    private Integer itemid;
    private String itemnum;
    private String description;
    private Integer rotating = 0;
    private String lottype="无批次";
    private Integer capitalized = 0;
    private Integer outside = 0;
    private Integer sparepartautoadd = 0;
    private String classstructureid;
    private Integer inspectionrequired = 0;
    private String sourcesysid;
    private String ownersysid;
    private String externalrefid;
    private String in24;
    private String in25;
    private String in26;
    private String in27;
    private String sendersysid;
    private String itemsetid="ITEMSET";
    /**
     * 订购单位
     */
    private String orderunit;
    /**
     * 发放单位
     */
    private String issueunit;
    private Integer conditionenabled = 0;
    private String groupname;
    private String metername;
    private String commoditygroup;
    private String commodity;
    private String itemtype = "项目";
    private Integer prorate = 0;

    private Integer iskit = 0;
    private String langcode = "ZH ";
    private Integer attachonissue = 0;
    private Integer hasld = 0;
    private String maxissue;
    private String status = "活动";
    private Date statusdate = new Date();
    private Integer hardresissue = 0;
    private Integer taxexempt = 0;
    private String receipttolerance;

    private Integer pluscisinhousecal = 0;
    private Integer pluscismte = 0;
    private String pluscismteclass;
    private Integer pluscsolution = 0;
    private Integer iscrew = 0;
    /**
     * 规格型号
     */
    private String sModelnum;


    /**
     * 品牌
     */
    private String sBrand;
    private String num;
    private String sManufac;

    /**
     * 图号
     */

    private String sPicturenum;
    private String prsumrderqty;

    private Integer islongterm = 0;
    private String unitcode;
    private String itemName;
    private String erpid;
    private String interfaceorgtype;
    private Integer iserpitem = 0;
    private String zxbz;
    private String olditemnum;

    /**
     * 材质
     */
    private String material;

    /**
     *
     */
    private String modelnum;
    private String tuhao;
    private String caizhi;
    private Integer js = 0;
    private String mark;
    private Integer changxie = 0;
    private Integer cIsdc = 0;
    private Integer inventoryItemId;
    private String uomCode;
    private String uomName;
    private String itemStatusCode;
    private String categoryCode;
    private String categoryDesc;
    private String stockEnabledFlag;
    private String purchasingEnabledFlag;
    private String unionFlag;
    private String catlogFlag;
    private String brandValue;
    private Date creationDate;
    private Date lastUpdateDate;
    private String executeValue;
    private String enabledFlag;
    private String wllxzzsm;
    private String lb;
    private String lbsm;
    private String dl;
    private String dlsm;
    private String zl;
    private String zlsm;
    private String xl;
    private String xlsm;
    private String mcxh;


}
