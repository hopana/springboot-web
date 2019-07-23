package com.hp.springboot.dao;

import com.hp.springboot.model.Role;
import com.hp.springboot.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 角色DAO
 *
 * @author hupan
 * @since 2018-05-09 11:30
 */
@Mapper
public interface RoleDao {

    @Select("select * from role where id = #{id}")
    Role getById(@Param("id") int id);

    @Insert("insert into role(name, code, parent_code, `desc`, create_time, update_time) values(#{name}, #{code}, #{parentCode}, #{desc}, #{createTime}, " + "#{updateTime})")
    int saveRole(Role user);
}
