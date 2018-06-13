package cn.cloudx.importdata.entity;


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
    private String invcostid;
    private String itemnum;
    private String location;
    private String conditioncode;
    private String condrate;
    private String itemsetid = "ITEMSET";
    private String siteid;
    private String stdcost;
    private String avgcost;
    private String lastcost;
    private String glaccount;
    private String controlacc;
    private String shrinkageacc;
    private String invcostadjacc;
    private String orgid = "CNE";


}
