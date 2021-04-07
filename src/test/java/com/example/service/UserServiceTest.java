package com.example.service;


import com.example.dao.LoginLogDao;
import com.example.dao.UserDao;
import com.example.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import java.util.Date;

import static org.junit.Assert.*;

@ContextConfiguration(locations = {"/applicationContext.xml"})
public class UserServiceTest extends AbstractJUnit4SpringContextTests {

//    @Autowired
//    private UserService userService;
    @InjectMocks  //设置被注入mock的对象，意思就是说，userService里面需要用到下面两个Mock出来的Dao
    private UserService userService=new UserService();

    @Mock  //设置mock创建出来的对象
    private UserDao userDao;

    @Mock
    private LoginLogDao loginLogDao;

    @Before  //测试之前会执行的代码，可以用来准备假数据用来测试
    public void prepareData(){
        MockitoAnnotations.initMocks(this);
        Mockito.when(userDao.getMatchCount("admin","123456")).thenReturn(1);
        Mockito.when(userDao.getMatchCount("admin","1111")).thenReturn(0);
        User user = new User(1,"admin");
        Mockito.when(userDao.findUserByUserName("admin")).thenReturn(user);
    }

    @Test
    public void hasMatchUser() {
        boolean b1 = userService.hasMatchUser("admin", "123456");
        boolean b2 = userService.hasMatchUser("admin", "1111");
        assertTrue(b1);
        assertFalse(b2);
    }


    @Test
    public void findUserByUserName() {
        User user = userService.findUserByUserName("admin");
        assertEquals(user.getUserName(), "admin");
    }

    @Test
    public void loginSuccess() {
        User user = userService.findUserByUserName("admin");
        user.setUserId(1);
        user.setUserName("admin");
        user.setLastIp("192.168.12.7");
        user.setLastVisit(new Date());
        userService.saveLog(user);
    }
}

