package com.example.dao;

import com.example.domain.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class UserDaoImpl implements UserDaoCustom{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public int getMatchCount(String userName, String password) {
        return entityManager.createQuery(" SELECT u FROM User u WHERE u.userName =?1 and u.password=?2")
                .setParameter(1,userName)
                .setParameter(2,password)
                .getResultList().size();
    }
    @Override
    public void updateLoginInfo(User user) {
        String sqlStr = " UPDATE User u SET u.lastVisit="+user.getLastVisit()+" ,u.lastIp="+user.getLastIp()+" WHERE u.userId ="+user.getUserId();
        entityManager.createQuery(sqlStr).executeUpdate();
    }




}
