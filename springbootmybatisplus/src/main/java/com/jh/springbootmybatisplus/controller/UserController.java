package com.jh.springbootmybatisplus.controller;

import com.jh.springbootmybatisplus.entity.User;
import com.jh.springbootmybatisplus.entity.login.UserLogin;
import com.jh.springbootmybatisplus.service.IUserservice;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.io.ResolverUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Validated
@Api(tags = "1.1",description = "用户管理" ,value = "用户管理")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserservice userserviceimpl;

//    private static final Logger LOGGER = LoggerFactory.getLogger(ResolverUtil.Test.class);

    @ApiOperation("用户登录")
    @RequestMapping(value = {"/login-action"})
    public void login(@RequestBody @Valid UserLogin logins){
        User user = userserviceimpl.userlogin(logins);
    }
    /**
     *新增一个用户
     * @param user
     * @return
     */
    @ApiOperation("新增用户")
    @PostMapping
    public String inserUser(@RequestBody @Valid User user,BindingResult result) {
        userserviceimpl.insertUser(user);
        return "新增成功";
    }

    /**
     *通过用户id查询用户信息
     * @param id
     * @return
     */
    @ApiOperation("通过用户id查询用户信息")
    @GetMapping("/selectuser")
    public User getUser(@NotBlank @RequestParam(required = true) String id){
        return  userserviceimpl.getUser(id);
    }

    /**
     * 通过用户id删除一个用户
     * @param id
     * @return
     */
    @ApiOperation("通过用户id删除一个用户")
    @DeleteMapping("/deleteuser")
    public String deleteUser(@RequestParam @NotBlank String id){
        userserviceimpl.deleteUser(id);
        return "删除成功";
    }

    /**
     * 更新一个用户
     * @param user
     * @return
     */
    @ApiOperation("更新一个用户")
    @PutMapping
    public String updateUser(@RequestBody @Valid User user, BindingResult result){
        userserviceimpl.update(user);
        return "修改成功";
    }

    /**
     * 查询所有用户信息
     * @return
     */
    @ApiOperation("查询所有用户信息")
    @GetMapping("/count")
    public List<User> selectUser(){
        return userserviceimpl.selectUser();

    }

    @ApiOperation("根据用户名获取用户密码")
    @GetMapping("/getpassword")
    public String getUserPassWord(@NotBlank @RequestParam String userName){
        return userserviceimpl.getUserPassWord(userName);
    }
}
