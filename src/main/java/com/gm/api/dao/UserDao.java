package com.gm.api.dao;

import com.gm.api.vo.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


/**
 * Created by xxhh on 2017/6/15.
 */
@Mapper
@Component
public interface UserDao {
    String TABLE_NAME = "user_info";
    String ALL_FIELDS =
            "user_id,nick_name,name,gender,age,mobile_no,email,id_code,user_token";

    @Select({"SELECT ", ALL_FIELDS, " FROM ", TABLE_NAME, " WHERE user_id = #{userId}"})
    UserInfo queryByUserId(@Param("userId") String userId);

    @Insert({
            "insert into ",
            TABLE_NAME,
            "(user_id,nick_name,name,gender,age,mobile_no,email,id_code,user_token)",
            " values (#{userId},#{nickName},#{name},#{gender},#{age},#{mobileNo},#{email},#{idCode},#{userToken})",
            " is_delete =0"})
    int insertUserInfo(UserInfo param);


    @Update({
            "Update set user_token=#{userToken}  ",
                  " FROM ", TABLE_NAME, " WHERE user_id=#{userId} and is_delete=0"})
    int updateUserToken(UserInfo param);

}
