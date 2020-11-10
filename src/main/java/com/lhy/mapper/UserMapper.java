package com.lhy.mapper;

import com.lhy.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

//这个注解表示了这是一个mybatis的mapper类
@Mapper
@Repository
public interface UserMapper {

    User findByUsernameAndPassword(@Param("username") String username,@Param("password") String password);

}
