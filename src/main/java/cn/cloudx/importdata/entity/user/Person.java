package cn.cloudx.importdata.entity.user;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author zhang
 */
@Entity
@Data
@SequenceGenerator(name = "personseq", sequenceName = "personseq", allocationSize = 1)
public class Person implements Serializable {

    private String personid;
    private String status = "活动";
    private String displayname;
    private String department;
    private String wfmailelection = "始终";
    private String transemailelection = "始终";
    private Date statusdate;
    private Integer acceptingwfmail = 1;
    private Integer loctoservreq = 1;


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "personseq")
    private Integer personuid;

    private String langcode = "ZH";
    private Integer hasld = 0;
    private String post;
    private Integer dhzxperson = 0;
    private Integer isbuyer = 0;
    private Integer issjyw = 0;
    private Integer istest = 0;
    private Integer jxjd = 0;
    private Integer ywzcb = 0;
    private String s_post;
    private Integer isauth = 0;
    private String locationorg = "CNE";
    private String locationsite;
    private String orgvalue;


}
