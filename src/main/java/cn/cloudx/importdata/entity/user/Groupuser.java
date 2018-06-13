package cn.cloudx.importdata.entity.user;


import lombok.Data;

import javax.persistence.*;

/**
 * @author zhang
 */
@Entity
@Data
@SequenceGenerator(name = "groupuserseq", sequenceName = "groupuserseq", allocationSize = 1)
public class Groupuser {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "groupuserseq")
    private Integer groupuserid;
    private String userid;
    private String groupname;


}
