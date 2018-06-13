package cn.cloudx.importdata.entity;


import lombok.Data;

import javax.persistence.Entity;
import java.util.Date;

@Entity
@Data
public class Invtrans {

  private String itemnum;
  private String storeloc;
  private Date transdate;
  private String transtype;
  private String quantity;
  private String curbal;
  private String physcnt;
  private String oldcost;
  private String newcost;
  private String enterby;
  private String memo;
  private String binnum;
  private String lotnum;
  private String gldebitacct;
  private String glcreditacct;
  private String financialperiod;
  private String linecost;
  private String exchangerate2;
  private String linecost2;
  private String invtransid;
  private String matrectransid;
  private String sendersysid;
  private String sourcesysid;
  private String ownersysid;
  private String externalrefid;
  private String orgid;
  private String siteid;
  private String itemsetid;
  private String conditioncode;
  private String condrate;
  private String matusetransid;
  private String consignment;
  private String consinvoicenum;
  private String consvendor;

}
