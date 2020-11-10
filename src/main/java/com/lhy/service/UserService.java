package com.lhy.service;

import com.lhy.pojo.User;
import org.springframework.stereotype.Service;

import javax.print.DocFlavor;
@Service
public interface UserService {

    User checkUser(String username , String password);
}
