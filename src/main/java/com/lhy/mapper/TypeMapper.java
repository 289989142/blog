package com.lhy.mapper;

import com.lhy.pojo.Type;
import com.lhy.pojo.TypeQuery;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TypeMapper {

    int saveType(Type type);

    Type getType(Long id);

    List<Type> getAllType();

    List<Type> getAllTypeAndBlog();

    List<TypeQuery>getNumType(Integer size);

    List<TypeQuery>getNumType1();

    Type getTypeByName(String name);

    int updateType(Type type);

    void deleteType(Long id);
}
