package cn.cloudx.importdata.entity.item;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author zhang
 */
@Entity
@Data
@SequenceGenerator(name = "inventory", allocationSize = 1, sequenceName = "inventoryseq")
public class Inventory {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inventory")
    private Integer inventoryid;
    private String itemsetid = "ITEMSET";
    private String manufacturer;
    private String modelnum;

    /**
     * 订购单位
     */
    private String orderunit;



    private String shrinkageacc;
    private String sstock;
    private String vendor;
    private String abctype;
    private String binnum;
    private String catalogcode;
    private String controlacc;
    private String glaccount;
    private String invcostadjacc;
    /**
     * 发放单位
     */
    private String issueunit;
    private Date lastissuedate=new Date();
    private String orgid = "CNE";

    /**
     * 地点
     */
    private String siteid;
    private String sourcesysid;
    private String ownersysid;
    private String externalrefid;
    private String category = "库存";
    private Integer ccf = 0;
    private Integer deliverytime = 10;
    private Integer issue1Yrago = 0;
    private Integer issue2Yrago = 0;
    private Integer issue3Yrago = 0;
    private Integer issueytd = 0;

    /**
     * 项目编号
     */
    private String itemnum;

    /**
     * 库房
     */
    private String location;
    private Integer maxlevel = 0;
    private Integer minlevel = -1;
    private Integer orderqty = 1;
    private String sendersysid;


    private String storeloc;
    private String storelocsiteid;
    private Integer internal = 0;
    private String status = "活动";
    private Date statusdate = new Date();
    private String dfltstagebin;
    private String receipttolerance;
    private Integer reorder = 1;
    private String costtype = "标准";
    private Integer hardresissue = 0;
    private Integer consignment = 0;
    private String invgentype;
    private String frequency;
    private String frequnit;
    private Date nextinvoicedate;
    private String consvendor;
    private String sAssetmodel;
    private Integer isstore = 0;
    private String storeremark;
    private Integer issale = 0;
    private String saleremark;
    private String sCost;
    private Integer islease = 0;
    private String leaseremark;
    private String classinfo;
    private Integer iseamsync = 0;
    private Integer leasecost;
    private String sPicturenum;
    private String unitcost2;
    private String sBrand;
    private String material;
    private String sModelnum;
    private String description;
    private String professional;
    private String abctypeDes;
    private String professionDes;

}
