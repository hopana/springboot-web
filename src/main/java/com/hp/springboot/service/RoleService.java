package com.hp.springboot.service;

import com.hp.springboot.dao.RoleDao;
import com.hp.springboot.model.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 角色
 *
 * @author hupan
 * @since 2018-05-10 21:06
 */
@Slf4j
@Service
public class RoleService {
    @Resource
    private RoleDao roleDao;

    public void saveRole(Role role) {
        roleDao.saveRole(role);
    }
}
