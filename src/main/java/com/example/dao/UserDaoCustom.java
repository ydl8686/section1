package com.example.dao;

import com.example.domain.User;

public interface UserDaoCustom {
    public void updateLoginInfo(User user);

    public int getMatchCount(String userName, String password) ;

}
