package com.course.controller;


import com.course.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;


@RestController
@Api(value = "v1",description = "用户管理系统")
@RequestMapping("v1")
public class UserManger {
    @Autowired
    private SqlSessionTemplate template;

    @ApiOperation(value = "登陆接口",httpMethod = "POST")
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public boolean login(HttpServletResponse response, @RequestBody User user){

        int i = template.selectOne("login",user);
        Cookie cookie =new Cookie("login","true");
        response.addCookie(cookie);

        System.out.println("查询结果是"+i);
        if (i==1) {
            System.out.println("登陆的用户是"+user.getUserName());
            return true;
        }
        return false;
    }


    @ApiOperation(value = "添加用户接口",httpMethod = "POST")
    @RequestMapping(value = "/addUSer",method = RequestMethod.POST)
    public boolean addUser(HttpServletRequest request,@RequestBody User user){
        //验证cookies
        Boolean x = verIfyCookies(request);
        int result = 0;
        if (x != null){
            result = template.insert("addUser",user);

        }
        if (result > 0){
            System.out.println("添加的用户数量是"+result);
            return true;
        }
        return false;






    }
    @ApiOperation(value = "获取用户信息（列表）接口",httpMethod = "POST")
    @RequestMapping(value = "/getUserInfo",method = RequestMethod.POST)
    public List<User> getUserInfo(HttpServletRequest request, @RequestBody User user){

        //cookies信息验证
        Boolean x = verIfyCookies(request);
        if (x ==true){
            List<User> users = template.selectList("getUserInfo",user);
            System.out.println("获取到的用户数量是"+users.size());
            return users;
        }
        else {
            return null;

        }

    }
    @ApiOperation(value = "更新/删除用户信息接口",httpMethod = "POST")
    @RequestMapping(value = "/updateUserInfo",method = RequestMethod.POST)
    public int updateUserInfo(HttpServletRequest request,@RequestBody User user){
        Boolean x = verIfyCookies(request);
        int i =0;
        if (x == true){
            i = template.update("updateUserInfo",user);
            System.out.println("更新用户信息数量为"+i);

        }
        return i;

    }

    private boolean verIfyCookies(HttpServletRequest request) {
        Cookie[] cookies =request.getCookies();
        if (Objects.isNull(cookies)){
            System.out.println("cookie为空");
            return false;
        }
        for (Cookie cookie:cookies){
            if (cookie.getName().equals("login")
                    && cookie.getValue().equals("true")){
                System.out.println("cookie验证通过");
                return true;


            }
        }
        return false;

    }


}






