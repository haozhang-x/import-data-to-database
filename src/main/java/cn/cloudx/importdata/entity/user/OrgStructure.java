package cn.cloudx.importdata.entity.user;


import lombok.Data;

import javax.persistence.*;

/**
 * @author zhang
 */
@Data
@Entity
@Table(name = "orgstructure", schema = "MAXIMO")
@SequenceGenerator(name = "mseq", sequenceName = "ORGSTRUCTUREIDSEQ", allocationSize = 1)
public class OrgStructure {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mseq")
    private Integer orgstructureid;
    private String description;
    private Integer hasld = 0;
    private Integer haschildren = 0;
    private Integer hasparent = 0;
    private String objectname = "ORGSTRUCTURE";
    /**
     *
     */
    private String value;
    private String type;
    private Integer parent = 0;
    private Integer isdefault = 0;
    private String kfs;
    private String sf;
    private String djs;
    private String xian;
    private String fdcjd;
    private String fdcwd;
    private String cftjd;
    private String cftwd;
    private String fdcazrl;
    private java.sql.Date jssj;
    private java.sql.Date yystgsj;
    private java.sql.Date czbyssj;
    private String fnzypggd;
    private String npjfs;
    private String npjfglmd;
    private String hbgd;
    private String jdwdfw;
    private String ssfgs;
    private String fdclbr;
    private String jdqhxx;
    private String wbrcd;
    private String wbrxz;
    private String ssfgslevel1;
    private Integer jdqhDy = 0;
    private Integer jdqhFs = 0;
    private Integer jdqhLb = 0;
    private Integer jdqhWs = 0;
    private Integer jdqhYw = 0;
    private String rzgqw;
    private String rzdqw;
    private String fxyg;
    private String hjwd;
    private String pjfs;
    private String swdl;
    private String zbgyc;
    private String fjfdl;
    private String highfxyg;
    private String highswdl;
    private String highzbhyc;
    private String lowfxyg;
    private String lowswdl;
    private String lowzbgyc;
    private String fcdx;
    private Integer active = 0;
    private String erpOrgId;
    private Integer erpEnabled = 0;
    private String seq;
    private Integer tongji = 1;
    private String inoc;
    private String jxjd;
    private String cParent;

}
