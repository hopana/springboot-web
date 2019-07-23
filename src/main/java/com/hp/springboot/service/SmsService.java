package com.hp.springboot.service;

import com.hp.springboot.dao.SmsDao;
import com.hp.springboot.model.Sms;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 短信
 *
 * @author hupan
 * @since 2018-05-09 14:54
 */
@Slf4j
@Service
public class SmsService {
    @Resource
    private SmsDao smsDao;

    public void saveSms(Sms sms) {
        smsDao.saveSms(sms);
    }

    public void saveSmsNoTransaction(Sms sms) {
        smsDao.saveSms(sms);
        throw new RuntimeException("模拟抛出异常");
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveSmsWithTransaction(Sms sms) {
        smsDao.saveSms(sms);
        throw new RuntimeException("模拟抛出异常");
    }
}
