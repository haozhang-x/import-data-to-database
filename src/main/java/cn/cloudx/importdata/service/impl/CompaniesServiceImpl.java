package cn.cloudx.importdata.service.impl;

import cn.cloudx.importdata.entity.company.Companies;
import cn.cloudx.importdata.repository.company.CompaniesRepository;
import cn.cloudx.importdata.service.CompaniesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhanghao
 * @date 2018/06/27
 */
@Service
public class CompaniesServiceImpl implements CompaniesService {

    private final CompaniesRepository companiesRepository;

    @Autowired
    public CompaniesServiceImpl(CompaniesRepository companiesRepository) {
        this.companiesRepository = companiesRepository;
    }


    @Override
    public Companies addCompany(Companies companies) {
        return companiesRepository.save(companies);
    }
}
