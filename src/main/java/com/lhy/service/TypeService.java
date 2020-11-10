package com.lhy.service;

import com.lhy.pojo.Type;
import com.lhy.pojo.TypeQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TypeService {


    int saveType(Type type);

    Type getType(Long id);

    List<Type>getAllType();

    List<Type>getAllTypeAndBlog();

    List<TypeQuery>getNumType(Integer size);

    List<TypeQuery>getNumType1();

    Type getTypeByName(String name);

    int updateType(Type type);

    void deleteType(Long id);



}
