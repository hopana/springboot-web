package com.hp.springboot.dao;

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
public interface UserDao {

    @Select("select * from user where id = #{id}")
    User getById(@Param("id") int id);

    @Insert("insert into user(name, age, id_card, gender, mobile, address, create_time, update_time) values(#{name}, #{age}, #{idCard}, #{gender}, #{mobile}, #{address}, #{createTime}, #{updateTime})")
    int saveUser(User user);
}
