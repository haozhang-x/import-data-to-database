package cn.cloudx.importdata.entity.item;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@SequenceGenerator(name = "invtrans", sequenceName = "invtransseq", allocationSize = 1)
public class Invtrans {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "invtrans")
    private Integer invtransid;


    /**
     * 项目编号
     */
    private String itemnum;

    /**
     * 库存区域
     */
    private String storeloc;




    private Date transdate = new Date();
    /*
     *
     */
    private String transtype = "插入项目";
    private Integer quantity = 0;
    private Integer curbal = 0;
    private Integer physcnt = 0;
    private Integer oldcost = 0;
    private Integer newcost = 0;
    private String enterby = "MAXADMIN";
    private String memo;
    private String binnum;
    private String lotnum;
    private String gldebitacct;
    private String glcreditacct;
    private Integer financialperiod = 365;
    private Integer linecost = 0;
    private String exchangerate2;
    private Integer linecost2 = 0;
    private String matrectransid;
    private String sendersysid;
    private String sourcesysid;
    private String ownersysid;
    private String externalrefid;
    private String orgid = "CNE";

    /**
     * 地点
     */

    private String siteid;
    private String itemsetid = "ITEMSET";
    private String conditioncode;
    private Integer condrate = 0;
    private String matusetransid;
    private Integer consignment=0;
    private String consinvoicenum;
    private String consvendor;

}
