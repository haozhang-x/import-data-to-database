package cn.cloudx.importdata.entity.item;


import lombok.Data;

import javax.persistence.*;

/**
 * @author zhang
 */
@Entity
@Data
@SequenceGenerator(name = "invcostid", sequenceName = "INVCOSTSEQ", allocationSize = 1)
public class Invcost {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "invcostid")
    private Integer invcostid;
    private String itemnum;
    private String location="W10001";
    private String conditioncode;
    private Integer condrate=100;
    private String itemsetid = "ITEMSET";
    private String siteid;
    private Integer stdcost=0;
    private Integer avgcost=0;
    private Integer lastcost=0;
    private String glaccount;
    private String controlacc;
    private String shrinkageacc;
    private String invcostadjacc;
    private String orgid = "CNE";


}
