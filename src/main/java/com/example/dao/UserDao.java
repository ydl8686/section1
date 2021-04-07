package com.example.dao;

import com.example.domain.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User,Integer> ,UserDaoCustom{


    @Cacheable(value = "mine")
    User findUserByUserName(String userName) ;

}
