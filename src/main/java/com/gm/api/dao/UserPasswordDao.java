package com.gm.api.dao;

import com.gm.api.vo.UserInfo;
import com.gm.api.vo.UserPassword;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * Created by xxhh on 2018/5/27.
 */
@Mapper
@Component
public interface UserPasswordDao {
    String TABLE_NAME = "user_password";
    String ALL_FIELDS =
            "user_id,password";

    @Insert({
            "insert into ",
            TABLE_NAME,
            "(user_id,password)",
            " values (#{userId},#{password})",
            " is_delete =0"})
    int inserUserPassword(UserPassword param);


    @Select({"SELECT ", ALL_FIELDS, " FROM ", TABLE_NAME, " WHERE user_id = #{userId}"})
    UserPassword queryByUserId(@Param("userId") String userId);

}
