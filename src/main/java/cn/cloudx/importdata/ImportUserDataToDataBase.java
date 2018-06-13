package cn.cloudx.importdata;

import cn.cloudx.importdata.constant.ExcelTypeConstant;
import cn.cloudx.importdata.entity.user.*;
import cn.cloudx.importdata.repository.user.OrgStructureRepository;
import cn.cloudx.importdata.repository.user.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

/**
 * @author zhanghao
 * @date 2018/06/05
 */
@Component
@Slf4j
public class ImportUserDataToDataBase {
    private final OrgStructureRepository orgStructureRepository;
    private final EmailRepository emailRepository;
    private final GroupUserRepository groupUserRepository;
    private final PersonRepository personRepository;
    private final PhoneRepository phoneRepository;
    private final PersonAncestorRepository personAncestorRepository;
    private final MaxUserRepository maxUserRepository;
    private final DepartmentRepository departmentRepository;
    private final PostRepository postRepository;
    private final MaxGroupRepository maxGroupRepository;


    @Autowired
    public ImportUserDataToDataBase(OrgStructureRepository orgStructureRepository, EmailRepository emailRepository, GroupUserRepository groupUserRepository, PersonRepository personRepository, PhoneRepository phoneRepository,
                                    PersonAncestorRepository personAncestorRepository,
                                    MaxUserRepository maxUserRepository,
                                    DepartmentRepository departmentRepository
            , PostRepository postRepository, MaxGroupRepository maxGroupRepository) {
        this.orgStructureRepository = orgStructureRepository;
        this.emailRepository = emailRepository;
        this.groupUserRepository = groupUserRepository;
        this.personRepository = personRepository;
        this.phoneRepository = phoneRepository;
        this.personAncestorRepository = personAncestorRepository;
        this.maxUserRepository = maxUserRepository;
        this.departmentRepository = departmentRepository;
        this.postRepository = postRepository;
        this.maxGroupRepository = maxGroupRepository;
    }


    /**
     * 读取excel转为workBook
     *
     * @param file excel文件流
     * @param type excelType
     * @return Workbook
     */
    private static Workbook readExcel(InputStream file, String type) throws IOException {
        if (ExcelTypeConstant.XLS.equals(type)) {
            POIFSFileSystem fss = new POIFSFileSystem(file);
            return new HSSFWorkbook(fss);
        }
        if (ExcelTypeConstant.XLSX.equals(type)) {
            return new XSSFWorkbook(file);
        }

        return null;
    }


    /**
     * 导入组织结构信息
     */
    private void importDataToOrgStructure() throws IOException {
        //获取导入的文件对象
        File file = ResourceUtils.getFile("classpath:" + "public/org-structure.xlsx");
        FileInputStream fileInputStream = new FileInputStream(file);
        String[][] excels = loadImport(fileInputStream, "xlsx", 1, 3);
        if (excels != null) {
            for (String[] rows : excels) {
                OrgStructure orgStructure = new OrgStructure();
                String type = rows[0];
                String description = rows[1];
                String value = rows[2];
                if (StringUtils.hasText(type) && StringUtils.hasText(description) && StringUtils.hasText(value)) {
                    orgStructure.setDescription(description);
                    orgStructure.setValue(value);
                    orgStructure.setType(type);
                    OrgStructure save = orgStructureRepository.save(orgStructure);
                    log.info("{},{},{},导入成功", save.getType(), save.getValue(), save.getDescription());
                }

            }
        }

    }


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
    private void importUserToMaximo(String personId, String displayName,
                                    String department, String emailAddress,
                                    String phone, String siteId, String orgvalue) {
        Maxuser maxuser = new Maxuser();
        maxuser.setUserid(personId);
        maxuser.setPersonid(personId);
        maxuser.setLoginid(personId);
        maxuser.setCreatedate(new Date());
        if (siteId != null) {
            maxuser.setStoreroomsite(siteId);
            maxuser.setDefsite(siteId);
        }
        maxUserRepository.save(maxuser);


        Person person = new Person();
        person.setPersonid(personId);
        person.setDisplayname(displayName);
        person.setOrgvalue(orgvalue);
        if (siteId != null) {
            person.setLocationsite(siteId);
        }
        if (department != null) {
            person.setDepartment(department);
        }
        person.setStatusdate(new Date());
        log.info("person={}", person);
        Person save = personRepository.save(person);
        log.info("用户,{},{},导入成功", save.getPersonid(), save.getDisplayname());


        if (StringUtils.hasText(emailAddress)) {
            Email email = new Email();
            email.setPersonid(personId);
            email.setEmailaddress(emailAddress);
            emailRepository.save(email);
        }

        if (StringUtils.hasText(phone)) {
            Phone myPhone = new Phone();
            myPhone.setPersonid(personId);
            myPhone.setPhonenum(phone);
            phoneRepository.save(myPhone);
        }

        {
            Personancestor personAncestor = new Personancestor();
            personAncestor.setPersonid(personId);
            personAncestor.setAncestor(personId);
            personAncestorRepository.save(personAncestor);
        }


        {
            Groupuser groupUser = new Groupuser();
            groupUser.setUserid(personId);
            groupUser.setGroupname("MAXDEFLTREG");
            groupUserRepository.save(groupUser);
        }

        {
            Groupuser groupUser = new Groupuser();
            groupUser.setUserid(personId);
            groupUser.setGroupname("MAXEVERYONE");
            groupUserRepository.save(groupUser);
        }


    }


    /**
     * 导入部门
     *
     * @param superior    上级
     * @param depNum      部门编号
     * @param description 部门描述
     */
    @SuppressWarnings("all")
    private void importDepartment(String superior, String depNum, String description) {
        Department department = new Department();
        department.setCreatedate(new Date());
        department.setChangedate(new Date());
        department.setStatusdate(new Date());
        department.setDepnum(depNum);
        department.setDescription(description);
        department.setOrgvalue(superior);
        Department result = departmentRepository.save(department);
        log.info("部门{},{}导入成功", result.getDepnum(), result.getDescription());
    }


    /**
     * 导入岗位
     *
     * @param description 岗位描述
     * @param postNum     岗位编号
     * @param depNum      部门编号
     * @param siteid      位置信息
     */
    @SuppressWarnings("all")
    private void importPost(String description, String postNum, String depNum, String siteid) {
        Post post = new Post();
        post.setDescription(description);
        post.setCreatedate(new Date());
        post.setChangedate(new Date());
        post.setOrgid("XXXX");
        post.setStatusdate(new Date());
        post.setDepnum(depNum);
        post.setPostnum(postNum);
        post.setSiteid(siteid);
        Post result = postRepository.save(post);
        log.info("岗位{},{}导入成功", result.getDepnum(), result.getDescription());
    }


    /**
     * 更新用户信息
     * 当用户不存在时会新建用户
     */
    @SuppressWarnings("all")
    public void updateUserInfo() throws IOException {
        File file = ResourceUtils.getFile("classpath:" + "public/集团人员信息.xlsx");
        FileInputStream fileInputStream = new FileInputStream(file);
        String[][] excels = loadImport(fileInputStream, ExcelTypeConstant.XLSX, 1, 10);
        if (!StringUtils.isEmpty(excels)) {
            for (String[] rows : excels) {
                if (StringUtils.hasText(rows[0])) {
                    String personId = rows[0].toUpperCase();
                    String post = rows[5];
                    String displayName = rows[1];
                    String department = rows[3];
                    String orgvalue = "xxxx";
                    String emailAddress = rows[6];
                    String phone = rows[7];
                    String siteId = null;

                    Optional<Person> optionalPerson = personRepository.findByPersonid(personId);
                    if (optionalPerson.isPresent()) {
                        Person person = optionalPerson.get();
                        person.setPost(post);
                        person.setDepartment(department);
                        Person save = personRepository.save(person);
                        log.info("用户{},{}的信息已更新", save.getPersonid(), save.getDisplayname());
                    } else {
                        importUserToMaximo(personId, displayName, department, emailAddress, phone, siteId, orgvalue);
                    }
                }

            }
        }
    }


    /**
     * 开始导入部门信息
     */
    @SuppressWarnings("all")
    public void startDepartmentImport() throws IOException {
        File file = ResourceUtils.getFile("classpath:" + "public/集团部门.xlsx");
        FileInputStream fileInputStream = new FileInputStream(file);
        String[][] excels = loadImport(fileInputStream, ExcelTypeConstant.XLSX, 1, 10);
        if (!StringUtils.isEmpty(excels)) {
            for (String[] rows : excels) {
                if (StringUtils.hasText(rows[0])) {
                    String depNum = rows[1];
                    String description = rows[0];
                    importDepartment("xxxx", depNum, description);
                }

            }
        }

    }

    /**
     *
     */
    @SuppressWarnings("all")
    public void startPostImport() throws IOException {
        File file = ResourceUtils.getFile("classpath:" + "public/集团岗位信息.xlsx");
        FileInputStream fileInputStream = new FileInputStream(file);
        String[][] excels = loadImport(fileInputStream, ExcelTypeConstant.XLSX, 1, 10);
        if (!StringUtils.isEmpty(excels)) {
            for (String[] rows : excels) {
                if (StringUtils.hasText(rows[0])) {
                    String depNum = rows[1];
                    String description = rows[2];
                    String postNum = rows[3];
                    importPost(description, postNum, depNum, null);
                }

            }
        }

    }


    public void importMaxGroup(String groupName, String descrption) {
        Maxgroup maxgroup = new Maxgroup();
        maxgroup.setGroupname(groupName);
        maxgroup.setDescription(descrption);
        Maxgroup save = maxGroupRepository.save(maxgroup);
        log.info("maxGroup{},{}新建成功", save.getGroupname(), save.getDescription());
    }


    /**
     * Please Use  updateUserInfo Instead
     */
    @Deprecated
    @SuppressWarnings("all")
    public void startUserImport() throws IOException {
        //分公司导入
        File file = ResourceUtils.getFile("classpath:" + "public/分公司人员信息.xlsx");
        FileInputStream fileInputStream = new FileInputStream(file);
        String[][] excels = loadImport(fileInputStream, "xlsx", 1, 10);
        if (!StringUtils.isEmpty(excels)) {
            for (String[] rows : excels) {
                if (StringUtils.hasText(rows[0])) {
                    String personId = rows[0].toUpperCase();
                    String displayName = rows[1];
                    String department = rows[5];
                    String orgvalue = rows[3];
                    String emailAddress = rows[8];
                    String phone = rows[9];
                    String siteId = null;
                    Optional<Person> optionalPerson = personRepository.findByPersonid(personId);
                    if (optionalPerson.isPresent()) {
                        continue;
                    }
                    importUserToMaximo(personId, displayName, department, emailAddress, phone, siteId, orgvalue);
                }

            }
        }


        //项目公司导入
        File file2 = ResourceUtils.getFile("classpath:" + "public/项目公司人员信息.xlsx");
        FileInputStream fileInputStream2 = new FileInputStream(file2);
        String[][] excels2 = loadImport(fileInputStream2, "xlsx", 1, 10);

        if (!StringUtils.isEmpty(excels2)) {
            for (String[] rows : excels2) {
                if (StringUtils.hasText(rows[0])) {
                    String personId = rows[0].toUpperCase();
                    String displayName = rows[1];
                    String department = null;
                    String emailAddress = rows[6];
                    String phone = rows[7];
                    String siteId = null;
                    String orgvalue = null;
                    Optional<Person> optionalPerson = personRepository.findByPersonid(personId);
                    if (optionalPerson.isPresent()) {
                        continue;
                    }
                    importUserToMaximo(personId, displayName, department, emailAddress, phone, siteId, orgvalue);
                }
            }

        }


        ///集团人员信息导入
        //集团人员导入
        File file3 = ResourceUtils.getFile("classpath:" + "public/集团人员信息及岗位信息.xlsx");
        FileInputStream fileInputStream3 = new FileInputStream(file3);
        String[][] excels3 = loadImport(fileInputStream3, ExcelTypeConstant.XLSX, 1, 10);

        if (!StringUtils.isEmpty(excels3)) {
            for (String[] rows : excels3) {
                String personId = rows[0].toUpperCase();
                String displayName = rows[3];
                String department = rows[1];
                String emailAddress = rows[1];
                String phone = rows[0];
                Optional<Person> optionalPerson = personRepository.findById(personId);
                if (optionalPerson.isPresent()) {
                    continue;
                }
                importUserToMaximo(personId, displayName, department, emailAddress, phone, null, null);
            }
        }


        //运维公司导入
        File file4 = ResourceUtils.getFile("classpath:" + "public/运维公司人员信息.xlsx");
        FileInputStream fileInputStream4 = new FileInputStream(file4);
        String[][] excels4 = loadImport(fileInputStream4, ExcelTypeConstant.XLSX, 1, 10);

        if (!StringUtils.isEmpty(excels4)) {
            for (String[] rows : excels4) {
                if (StringUtils.hasText(rows[0])) {
                    String personId = rows[0].toUpperCase();
                    String displayName = rows[1];
                    String department = null;
                    String emailAddress = rows[7];
                    String phone = rows[8];
                    String siteId = rows[2];
                    if ("xxxx".equals(siteId)) {
                        siteId = null;
                    }
                    Optional<Person> optionalPerson = personRepository.findByPersonid(personId);
                    if (optionalPerson.isPresent()) {
                        continue;
                    }
                    importUserToMaximo(personId, displayName, department, emailAddress, phone, siteId, null);
                }


            }
        }

    }


    public void startImportMaxGroup() throws IOException {
        File file = ResourceUtils.getFile("classpath:" + "public/运维公司人员信息.xlsx");
        FileInputStream fileInputStream4 = new FileInputStream(file);
        String[][] excel = loadImport(fileInputStream4, ExcelTypeConstant.XLSX, 1, 10);
        if (!StringUtils.isEmpty(excel)) {
            for (String[] rows : excel) {
                if (StringUtils.hasText(rows[0])) {
                    String description = rows[0];
                    String groupName = rows[1];
                    importMaxGroup(groupName, description);
                }


            }
        }

    }


    /**
     * @param file            excel输入流
     * @param type            excel type 有xls和xlsx
     * @param readStartRow    从第几行开始读取
     * @param iTotalOfColumns 读取多少列的数据
     * @return 解析后的二维String数组 方便进行处理
     */


    @SuppressWarnings("all")
    private static String[][] loadImport(InputStream file, String type, int readStartRow, int iTotalOfColumns) throws IOException {
        //*****************读取execl表格数据*****************
        Workbook wb = readExcel(file, type);
        if (wb == null) {
            return null;
        }
        int iTotalOfSheetRows = 0;
        //遍历所有sheet，并将需要解析的sheet 索引添加到列表对象中。
        ArrayList<Integer> sheetIndexArrys = new ArrayList<>();
        for (int sheetIndex = 0; sheetIndex < 1; sheetIndex++) {
            Sheet sheet = wb.getSheetAt(sheetIndex);
            //当sheet中最大行不足3行，不再对此sheet进行解析
            if (sheet.getLastRowNum() > 1 && !wb.isSheetHidden(sheetIndex)) {
                iTotalOfSheetRows += (sheet.getLastRowNum() + 1 - readStartRow);
                sheetIndexArrys.add(sheetIndex);
            }
        }
        //将excel解析的所有数据，保存在字符串数组对象中。
        //1、初始化数组
        String[][] excelStr = new String[iTotalOfSheetRows][iTotalOfColumns];
        int rowIndex = 0;
        //2、解析excel，当sheet 整行数据为空时，停止对该sheet解析。
        for (Integer sheetIndex : sheetIndexArrys) {
            Sheet sheet = wb.getSheetAt(sheetIndex);
            //行数不到3 或 sheet隐藏的 跳过
            if (sheet.getLastRowNum() <= 1 || wb.isSheetHidden(sheetIndex)) {
                continue;
            }
            int iTotalOfRows = sheet.getLastRowNum() + 1 - readStartRow;
            //循环行
            for (int i = 0; i < iTotalOfRows; i++, rowIndex++) {
                Row row = sheet.getRow(readStartRow + i);
                if (row == null) {
                    break;
                }
                //行隐藏跳过
                if (row.getZeroHeight()) {
                    continue;
                }
                //循环列
                for (int j = 0; j < iTotalOfColumns; j++) {
                    // 实例单元格对象
                    Cell cell = row.getCell(j);
                    //将所有数据列解析成字符串，对数字类型（数组、时间）按指定格式解析
                    if (cell != null) {
                        // 设置为字符串类型
                        cell.setCellType(CellType.STRING);
                        excelStr[rowIndex][j] = cell.getStringCellValue();
                    }
                }
            }
        }
        return excelStr;

    }

}
