package com.hp.springboot.dao;

import com.hp.springboot.model.Sms;
import com.hp.springboot.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 用户DAO
 *
 * @author hupan
 * @since 2018-05-09 11:30
 */
@Mapper
public interface SmsDao {

    @Select("select * from sms where id = #{id}")
    User getById(@Param("id") int id);

    @Insert("insert into sms(mobile, content, create_time) values(#{mobile}, #{content}, #{createTime})")
    int saveSms(Sms sms);
}
