package cn.cloudx.importdata.service;

/**
 * @author zhanghao
 * @date 2018/06/22
 */
public interface UserService {

    /**
     * 导入maximo人员
     *
     * @param personId     personId
     * @param displayName  显示的名字
     * @param department   部门编号
     * @param emailAddress 电子邮箱地址
     * @param phone        电话信息
     * @param siteId       地点信息
     * @param orgvalue     上级信息
     */
    void importUserToMaximo(String personId, String displayName, String department, String emailAddress, String phone, String siteId, String orgvalue);

    /**
     * 导入部门
     *
     * @param superior    上级
     * @param depNum      部门编号
     * @param description 部门描述
     */

    void importDepartment(String superior, String depNum, String description);


    /**
     * 导入岗位
     *
     * @param description 岗位描述
     * @param postNum     岗位编号
     * @param depNum      部门编号
     * @param siteid      位置信息
     */

    void importPost(String description, String postNum, String depNum, String siteid);


}
