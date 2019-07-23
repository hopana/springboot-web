package com.hp.springboot.service;

import com.hp.springboot.base.BaseServiceTest;
import com.hp.springboot.model.Role;
import com.hp.springboot.model.Sms;
import com.hp.springboot.model.User;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Date;

public class UserSericeTest extends BaseServiceTest {
    @Resource
    private UserService userService;
    @Resource
    private SmsService smsService;

    @Test
    public void testAddSms() {
        Sms sms = new Sms();
        sms.setMobile("18928491987");
        sms.setContent("欢迎大刘注册！");
        sms.setCreateTime(new Date());
        smsService.saveSmsNoTransaction(sms);
    }

    /***********************单方法调用：test()->fun() ***********************/

    @Test
    public void testAddUser() {
        User user = User.builder().name("mock").age(30).idCard("422130198909013539").gender(1).mobile("18928491987").address("深圳").createTime(new Date()).updateTime(new Date()).build();
        userService.saveUserNoTransaction(user);
    }

    /***********************跨类调用：test()->A.fun1()->B.fun2()***********************/

    @Test
    public void testTransaction1() {
        User user = User.builder().name("David").age(30).idCard("422130198909013539").gender(1).mobile("18928491987").address("深圳").createTime(new Date()).updateTime(new Date()).build();
        // 前者有事务但是异常被捕获，后者有事务
        userService.saveUserAndSmsWithTransactionAndTryCatch(user);
    }

    @Test
    public void testTransaction2() {
        User user = User.builder().name("Lucy").age(30).idCard("422130198909013539").gender(2).mobile("18928491987").address("深圳").createTime(new Date()).updateTime(new Date()).build();
        // 前者有事务，后者有事务
        userService.saveUserAndSmsWithTransaction(user);
    }
    @Test
    public void testTransaction3() {
        User user = User.builder().name("Mary").age(30).idCard("422130198909013539").gender(2).mobile("18928491987").address("深圳").createTime(new Date()).updateTime(new Date()).build();
        // 前者有事务，后者无事务
        userService.saveUserAndSmsWithTransaction2(user);
    }

    @Test
    public void testTransaction4() {
        User user = User.builder().name("Jerry").age(30).idCard("422130198909013539").gender(1).mobile("18928491987").address("深圳").createTime(new Date()).updateTime(new Date()).build();
        // 前者无事务，后者有事务
        userService.saveUserAndSmsNoTransaction(user);
    }

    /***********************同类调用：test()->A.fun1()->A.fun2()***********************/

    @Test
    public void testTransaction5() {
        User user = User.builder().name("Andy").age(30).idCard("422130198909013539").gender(1).mobile("18928491987").address("深圳").createTime(new Date()).updateTime(new Date()).build();
        Role role = Role.builder().name("Teacher").code("T1001").parentCode("A1000").desc("教师").createTime(new Date()).updateTime(new Date()).build();
        // 二者都没有事务
        userService.saveUserAndRoleNoTransaction1(user, role);
    }

    @Test
    public void testTransaction6() {
        User user = User.builder().name("Scott").age(30).idCard("422130198909013539").gender(1).mobile("18928491987").address("深圳").createTime(new Date()).updateTime(new Date()).build();
        Role role = Role.builder().name("Chief").code("C1001").parentCode("C1000").desc("主任").createTime(new Date()).updateTime(new Date()).build();
        // 前者无事务，后者有事务
        userService.saveUserAndRoleNoTransaction2(user, role);
    }

    @Test
    public void testTransaction7() {
        User user = User.builder().name("Edward").age(30).idCard("422130198909013539").gender(1).mobile("18928491987").address("深圳").createTime(new Date()).updateTime(new Date()).build();
        Role role = Role.builder().name("Student").code("S1001").parentCode("S1000").desc("学生").createTime(new Date()).updateTime(new Date()).build();
        // 前者有事务，后者无事务
        userService.saveUserAndRoleWithTransaction1(user, role);
    }

    @Test
    public void testTransaction8() {
        User user = User.builder().name("Henry").age(30).idCard("422130198909013539").gender(1).mobile("18928491987").address("深圳").createTime(new Date()).updateTime(new Date()).build();
        Role role = Role.builder().name("Doctor").code("D1001").parentCode("D1000").desc("校医").createTime(new Date()).updateTime(new Date()).build();
        // 前者有事务，后者有事务
        userService.saveUserAndRoleWithTransaction2(user, role);
    }

    @Test
    public void testTransaction9() {
        User user = User.builder().name("Donald").age(30).idCard("422130198909013539").gender(1).mobile("18928491987").address("深圳").createTime(new Date()).updateTime(new Date()).build();
        Role role = Role.builder().name("Worker").code("W1001").parentCode("W1000").desc("工作人员").createTime(new Date()).updateTime(new Date()).build();
        // 前者有事务但是异常被捕获，后者有事务
        userService.saveUserAndRoleWithTransactionAndTryCatch(user, role);
    }

}
