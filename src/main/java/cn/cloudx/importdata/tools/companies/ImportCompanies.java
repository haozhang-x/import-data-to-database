package cn.cloudx.importdata.tools.companies;

import cn.cloudx.importdata.constant.ExcelTypeConstant;
import cn.cloudx.importdata.entity.company.Companies;
import cn.cloudx.importdata.entity.company.Compcontact;
import cn.cloudx.importdata.repository.company.CompcontactRespository;
import cn.cloudx.importdata.service.CompaniesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static cn.cloudx.importdata.tools.parse.ParseExcel.loadImport;

/**
 * @author zhanghao
 * @date 2018/06/27
 */
@Component
@Slf4j
public class ImportCompanies {
    private final CompaniesService companiesService;
    private final CompcontactRespository compcontactRespository;

    @Autowired
    public ImportCompanies(CompaniesService companiesService, CompcontactRespository compcontactRespository) {
        this.companiesService = companiesService;
        this.compcontactRespository = compcontactRespository;
    }

    public void importCompanies() {
        try {
            File file = ResourceUtils.getFile("classpath:" + "public/供应商.xls");
            FileInputStream fileInputStream = new FileInputStream(file);
            String[][] excels = loadImport(fileInputStream, ExcelTypeConstant.XLS, 3, 30);
            if (!StringUtils.isEmpty(excels)) {
                for (String[] rows : excels) {
                    //编号
                    String num = rows[0];
                    //名称
                    String name = rows[1];
                    //供货服务范围
                    String serviceScope = rows[2];
                    //业务范围
                    String businessScope = rows[3];
                    //类型
                    String type = rows[4];
                    //企业性质
                    String enterpriseNature = rows[5];
                    //企业注册号
                    String enterpriseRegistrationNumber = rows[6];
                    //税务登记号
                    String taxRegistrationNumber = rows[7];
                    //法人代表
                    String legalRepresentative = rows[8];
                    //注册地
                    String registeredPlace = rows[9];
                    //注册资金
                    String registeredCapital = rows[10];
                    //开户银行
                    String accountBank = rows[11];
                    //银行账号
                    String account = rows[12];
                    //省/直辖市
                    String provinceOrMunicipality = rows[13];
                    //市/县
                    String cityOrCounty = rows[14];
                    //公司详细地址
                    String companyDetailAddress = rows[15];
                    //联系人
                    String contact = rows[16];
                    //固定电话
                    String fixedTel = rows[17];
                    //移动电话
                    String mobile = rows[18];
                    //邮编
                    String post = rows[19];
                    //电子邮箱
                    String email = rows[20];
                    //传真
                    String fax = rows[21];

                    if (StringUtils.hasText(num)) {

                        if (StringUtils.hasText(contact)) {
                            Compcontact compcontact = new Compcontact();
                            compcontact.setCompany(num);
                            compcontact.setContact(contact);
                            compcontactRespository.save(compcontact);
                        }
                        Companies companies = new Companies();
                        companies.setCompany(num);
                        companies.setName(name);
                        companies.setGhfwfw(serviceScope);
                        companies.setType(type);
                        companies.setAddress1(provinceOrMunicipality);
                        companies.setYyfw(businessScope);
                        companies.setFrdb(legalRepresentative);
                        companies.setEmail(email);
                        companies.setFax(fax);
                        companies.setCellphone(mobile);
                        companies.setQyzch(enterpriseRegistrationNumber);
                        companies.setBankaccount(account);
                        companies.setPhone(fixedTel);
                        companies.setAddress1(provinceOrMunicipality);
                        companies.setAddress2(cityOrCounty);
                        companies.setAddress5(registeredPlace);
                        companies.setAddress3(companyDetailAddress);
                        companies.setQyxz(enterpriseNature);
                        companies.setRegistration1(taxRegistrationNumber);
                        companies.setBanknum(account);
                        companies.setKhyh(accountBank);
                        companies.setFax(fax);
                        companies.setEmail(email);
                        companies.setAddress4(post);
                        companies.setZczj(registeredCapital);
                        Companies result = companiesService.addCompany(companies);
                        log.info("供应商{},导入成功", result.getName());

                    } else {
                        break;
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
