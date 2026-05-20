package com.sky.mapper;


import com.sky.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginMapper {
    /**
     * 根据用户名查询用户信息
     * @param username 用户名
     * @return 用户实体
     */
   User selectUserByUsername(String username);

    /**
     * 修改新密码
     * @param user
     * @return
     */
   User updateUserPassword(User user);
}
