package cn.cloudx.importdata.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import java.util.Date;

@Entity
@Data
@SequenceGenerator(name = "invbalances", sequenceName = "Invbalancesseq", allocationSize = 1)
public class Invbalances {

    private String itemnum;
    private String location;
    private String binnum;
    private String lotnum;
    private String curbal;
    private String physcnt;
    private Date physcntdate;
    private String reconciled;
    private String sourcesysid;
    private String ownersysid;
    private String externalrefid;
    private String sendersysid;
    private String orgid = "CNE";
    private String siteid;
    private String itemsetid="ITEMSET";
    private String conditioncode;
    private String invbalancesid;
    private String stagingbin;
    private String stagedcurbal;


}
