package dev.willsnow.community.mapper;

import dev.willsnow.community.model.User;
import org.apache.ibatis.annotations.*;

/**
 * @author will
 */

@Mapper
public interface UserMapper {

    @Insert("insert into user (name, account_id, token, gmt_create, gmt_modified, avatar_url) values (#{name}, #{accountId}, #{token}, #{gmtCreate}, #{gmtModified}, #{avatarUrl})")
    void insert(User user);

    @Select("select * from user where token = #{token} limit 1")
    User findByToken(@Param("token") String token);
    // 参数是类就不用加 @Parm 注解，如上面的User, 自动调用 getter 方法

    @Select("select * from user where id = #{id} limit 1")
    User findById(@Param("id") Integer id);

    @Select("select * from user where account_id = #{accountId} limit 1")
    User findByAccountId(@Param("accountId") String accountId);

    @Update("update user set name = #{name}, token = #{token}, gmt_modified = #{gmtModified}, avatar_url = #{avatarUrl} where id=#{id}")
    void update(User user);
}
