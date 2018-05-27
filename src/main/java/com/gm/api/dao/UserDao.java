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
            "user_id,nick_name";

    @Select({"SELECT ", ALL_FIELDS, " FROM ", TABLE_NAME})
    UserInfo query();

}
