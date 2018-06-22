package cn.cloudx.importdata.service.impl;

import cn.cloudx.importdata.entity.user.*;
import cn.cloudx.importdata.repository.user.*;
import cn.cloudx.importdata.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * @author zhanghao
 * @date 2018/06/22
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
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
    public UserServiceImpl(OrgStructureRepository orgStructureRepository, EmailRepository emailRepository, GroupUserRepository groupUserRepository, PersonRepository personRepository, PhoneRepository phoneRepository, PersonAncestorRepository personAncestorRepository, MaxUserRepository maxUserRepository, DepartmentRepository departmentRepository, PostRepository postRepository, MaxGroupRepository maxGroupRepository) {
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

    @Override
    public void importUserToMaximo(String personId, String displayName, String department, String emailAddress, String phone, String siteId, String orgvalue) {
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

    public void importMaxGroup(String groupName, String descrption) {
        Maxgroup maxgroup = new Maxgroup();
        maxgroup.setGroupname(groupName);
        maxgroup.setDescription(descrption);
        Maxgroup save = maxGroupRepository.save(maxgroup);
        log.info("maxGroup{},{}新建成功", save.getGroupname(), save.getDescription());
    }


    @Override
    public void importDepartment(String superior, String depNum, String description) {
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

    @Override
    public void importPost(String description, String postNum, String depNum, String siteid) {
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
}
