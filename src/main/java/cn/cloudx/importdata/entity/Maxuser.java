package cn.cloudx.importdata.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author zhang
 */
@Entity
@Data
@SequenceGenerator(name = "maxuserseq", sequenceName = "maxuserseq", allocationSize = 1)
public class Maxuser {

    private String userid;
    private String personid;
    private String status = "活动";
    private String type = "类型 1";
    private String defsite;
    private Integer querywithsite = 1;
    private String defstoreroom;

    private String storeroomsite;

    private String pwhintquestion;
    private String pwhintanswer;
    private Integer forceexpiration = 0;
    private Date pwexpiration;
    private Integer failedlogins = 0;
    private String databaseuserid;
    private String password = "500C7C9864165AD6E8A35E1EFC5279C2";
    private String loginid;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "maxuserseq")
    private Integer maxuserid;
    private String memo;
    private Integer sysuser = 0;
    private Integer inactivesites = 0;
    private Integer screenreader = 0;
    private String defaultrepfacsiteid;
    private String defaultrepfac;
    private Integer isconsultant = 0;
    private Integer sidenav = 0;
    private String ldappassword;
    private String xjpassword;
    private String xmm = "123456";

    private Date createdate;

}
