package com.example.web;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.domain.User;
import com.example.service.UserService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping(value = "/admin")
public class LoginController {

    private UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/login.html")
    public String loginPage() {
        return "login";
    }

    @RequestMapping(value = "/loginCheck.html",method = POST)
    public ModelAndView loginCheck(HttpServletRequest request, @Valid LoginInfo loginInfo, BindingResult errors) {
        System.out.println("hello");
        System.out.println(errors);
        System.out.println(loginInfo);
        if(errors.hasErrors()){

            List<ObjectError> myErrorList=errors.getAllErrors();
            StringBuilder sb=new StringBuilder();
            for (int i=0;i<myErrorList.size();i++){
                sb.append(myErrorList.get(i).getDefaultMessage()).append("\n");
            }
            return new ModelAndView("login","error",sb.toString());
        }
        boolean isValidUser =
                userService.hasMatchUser(loginInfo.getUserName(),
                        loginInfo.getPassword());
        if (!isValidUser) {
            return new ModelAndView("login", "error", "用户名或密码错误。");
        } else {
            User user = userService.findUserByUserName(loginInfo
                    .getUserName());
            user.setLastIp(request.getLocalAddr());
            user.setLastVisit(new Date());
            userService.saveLog(user);
            request.getSession().setAttribute("user", user);
            return new ModelAndView("main");
        }
    }
}
