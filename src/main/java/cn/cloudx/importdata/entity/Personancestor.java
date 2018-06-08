package cn.cloudx.importdata.entity;


import lombok.Data;

import javax.persistence.*;

/**
 * @author zhang
 */
@Data
@Entity
@SequenceGenerator(name = "PERSONANCESTORSEQ", sequenceName = "PERSONANCESTORSEQ", allocationSize = 1)
public class Personancestor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "PERSONANCESTORSEQ")
    private Integer personancestorid;
    private String ancestor;
    private String personid;
    private Integer hierarchylevels = 0;

}
