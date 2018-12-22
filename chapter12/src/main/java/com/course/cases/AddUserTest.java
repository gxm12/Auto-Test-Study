package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.AddUserCase;
import com.course.model.User;
import com.course.utils.DatabaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import sun.plugin2.main.server.LiveConnectSupport;

import javax.jws.soap.SOAPBinding;
import java.io.IOException;



public class AddUserTest {
    //依赖组loginTrue，lohinTrue执行成功的话才会执行addUser
    @Test(dependsOnGroups = "loginTrue",description = "添加用户接口测试")
    public void addUser() throws IOException {
        SqlSession session = DatabaseUtil.getSqlSession();
        AddUserCase addUserCase = session.selectOne("addUserCase",1);
        System.out.println(addUserCase.toString());
        System.out.println(TestConfig.addUserUrl);

        //发请求，获取结果
//        String result = getResult(addUserCase);
//
//        //验证返回结果
//        User user = session.selectOne("addUser",addUserCase);
//
//        System.out.println(user.toString());
//        Assert.assertEquals(addUserCase.getExpected(),result);







    }

//    private String getResult(AddUserCase addUserCase) throws IOException {
//        HttpPost post = new HttpPost(TestConfig.addUserUrl);
//        JSONObject param = new JSONObject();
//        param.put("userName",addUserCase.getUserName());
//        param.put("password",addUserCase.getPassword());
//        param.put("age",addUserCase.getAge());
//        param.put("sex",addUserCase.getSex());
//        param.put("permission",addUserCase.getPermission());
//        param.put("isDelete",addUserCase.getIsDelete());
//
//        //设置请求头信息
//        post.setHeader("content-type","application/json");
//        StringEntity entity = new StringEntity(param.toString(),"utf-8");
//        post.setEntity(entity);
//
//        //设置cookie信息
//        TestConfig.defaultHttpClient.setCookieStore(TestConfig.store);
//        String result;
//
//        //执行方法
//        HttpResponse response = TestConfig.defaultHttpClient.execute(post);
//        //将结果存放在result中
//        result = EntityUtils.toString(response.getEntity());
//
//
//        System.out.println(result);
//
//        return result;
//    }


}
