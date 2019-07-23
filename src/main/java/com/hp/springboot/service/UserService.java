package com.hp.springboot.service;

import com.hp.springboot.dao.UserDao;
import com.hp.springboot.model.Role;
import com.hp.springboot.model.Sms;
import com.hp.springboot.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 用户
 *
 * @author hupan
 * @since 2018-05-09 14:53
 */
@Slf4j
@Service
public class UserService {
    @Resource
    private UserDao userDao;
    @Resource
    private SmsService smsService;
    @Resource
    private RoleService roleService;

    public void saveUser(User user) {
        userDao.saveUser(user);
    }

    /***********************跨类调用***********************/

    public void saveUserNoTransaction(User user) {
        userDao.saveUser(user);
        throw new RuntimeException("模拟抛出异常");
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveSmsWithTransaction(User user) {
        Sms sms = new Sms();
        sms.setMobile(user.getMobile());
        sms.setContent("欢迎" + user.getName() + "注册！");
        sms.setCreateTime(new Date());
        smsService.saveSmsNoTransaction(sms);

        // 调用API网关服务发送短信
        // ......
        throw new RuntimeException("内部操作出现异常");
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveUserAndSmsWithTransaction(User user) {
        userDao.saveUser(user);

        Sms sms = new Sms();
        sms.setMobile(user.getMobile());
        sms.setContent("欢迎" + user.getName() + "注册！");
        sms.setCreateTime(new Date());
        smsService.saveSmsWithTransaction(sms);
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveUserAndSmsWithTransaction2(User user) {
        userDao.saveUser(user);

        Sms sms = new Sms();
        sms.setMobile(user.getMobile());
        sms.setContent("欢迎" + user.getName() + "注册！");
        sms.setCreateTime(new Date());
        smsService.saveSmsNoTransaction(sms);
    }

    public void saveUserAndSmsNoTransaction(User user) {
        userDao.saveUser(user);

        Sms sms = new Sms();
        sms.setMobile(user.getMobile());
        sms.setContent("欢迎" + user.getName() + "注册！");
        sms.setCreateTime(new Date());
        smsService.saveSmsWithTransaction(sms);
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveUserAndSmsWithTransactionAndTryCatch(User user) {
        userDao.saveUser(user);

        try {
            saveSmsWithTransaction(user);
        } catch (Exception e) {
            log.error("短信网关发生异常");
        }
    }

    /***********************同类调用***********************/

    /**
     * 二者都没有事务
     */
    public void saveUserAndRoleNoTransaction1(User user, Role role) {
        userDao.saveUser(user);
        saveRoleNoTransaction(role);
    }

    /**
     * 前者无事务，后者有事务
     */
    public void saveUserAndRoleNoTransaction2(User user, Role role) {
        userDao.saveUser(user);
        saveRoleWithTransaction(role);
    }

    /**
     * 前者有事务，后者无事务
     */
    @Transactional(rollbackFor = Exception.class)
    public void saveUserAndRoleWithTransaction1(User user, Role role) {
        userDao.saveUser(user);
        saveRoleNoTransaction(role);
    }

    /**
     * 前者有事务，后者有事务
     */
    @Transactional(rollbackFor = Exception.class)
    public void saveUserAndRoleWithTransaction2(User user, Role role) {
        userDao.saveUser(user);
        saveRoleWithTransaction(role);
    }

    /**
     * 前者有事务但是异常被捕获，后者有事务
     */
    @Transactional(rollbackFor = Exception.class)
    public void saveUserAndRoleWithTransactionAndTryCatch(User user, Role role) {
        userDao.saveUser(user);
        try {
            saveRoleWithTransaction(role);
        } finally {
            log.error("增加角色发生异常");
        }
    }

    public void saveRoleNoTransaction(Role role) {
        roleService.saveRole(role);
        throw new RuntimeException("模拟抛出异常");
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveRoleWithTransaction(Role role) {
        roleService.saveRole(role);
        throw new RuntimeException("模拟抛出异常");
    }

}
