package cn.cloudx.importdata.entity.item;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author zhang
 */
@Entity
@Data
@SequenceGenerator(name = "invbalances", sequenceName = "Invbalancesseq", allocationSize = 1)
public class Invbalances {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "invbalances")
    private Integer invbalancesid;
    private String itemnum;
    private String location;
    private String binnum;
    private String lotnum;
    private Float curbal = 2f;
    private Float physcnt = 2f;
    private Date physcntdate = new Date();
    private Integer reconciled = 1;
    private String sourcesysid;
    private String ownersysid;
    private String externalrefid;
    private String sendersysid;
    private String orgid = "CNE";
    private String siteid;
    private String itemsetid = "ITEMSET";
    private String conditioncode;
    private Integer stagingbin=0;
    private Integer stagedcurbal=0;


}
