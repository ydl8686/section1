package com.example.dao;

import com.example.domain.LoginLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginLogDao extends JpaRepository<LoginLog,Integer> {

    @Override
    LoginLog save(LoginLog loginLog) ;

}