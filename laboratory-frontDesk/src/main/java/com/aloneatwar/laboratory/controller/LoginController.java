package com.aloneatwar.laboratory.controller;

import com.alibaba.fastjson.JSONObject;
import com.aloneatwar.laboratory.entity.Student;
import com.aloneatwar.laboratory.entity.Teacher;
import com.aloneatwar.laboratory.service.IStudentService;
import com.aloneatwar.laboratory.util.JWTUtil;
import com.aloneatwar.laboratory.vo.request.StudentLogin;
import com.aloneatwar.laboratory.vo.request.TeacherLoginModel;
import com.aloneatwar.laboratory.service.ITeacherService;
import com.aloneatwar.laboratory.vo.response.LoginStudent;
import com.aloneatwar.laboratory.vo.response.LoginTeacher;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;

import org.jeecg.common.system.util.JwtUtil;
import org.jeecg.common.util.PasswordUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;


@RestController
@RequestMapping("/sys")
@Api(tags="用户登录")
@Slf4j
public class LoginController {
    @Autowired
    private ITeacherService teacherService;
    @Autowired
    private IStudentService studentService;




    @ApiOperation("老师登录接口")
    @RequestMapping(value = "/teacherLogin", method = RequestMethod.POST)
    public Result<JSONObject> login(@RequestBody TeacherLoginModel sysLoginModel){
        Result<JSONObject> result = new Result<JSONObject>();
        String name = sysLoginModel.getName();
        String password = sysLoginModel.getPassword();

        QueryWrapper<Teacher> teacherQueryWrapper = new QueryWrapper<>();
        teacherQueryWrapper=teacherQueryWrapper.eq("name",name);
        Teacher teacher = teacherService.getOne(teacherQueryWrapper,false);
        if(teacher==null){
            result.error500("用户名或密码错误");
            return result;
        }

        String userpassword = PasswordUtil.encrypt(password, Teacher.key, Teacher.salt);
        String syspassword = teacher.getPassword();
        if (!syspassword.equals(userpassword)) {
            result.error500("用户名或密码错误");
            return result;
        }
        LoginTeacher loginTeacher = new LoginTeacher();
        BeanUtils.copyProperties(teacher,loginTeacher);
        String token = JWTUtil.TeacherSign(loginTeacher,Teacher.salt);
        JSONObject obj = new JSONObject(new LinkedHashMap<>());
        obj.put("token",token);
        obj.put("teacher",loginTeacher);
        result.setResult(obj);
        return result;
    }

    @ApiOperation("学生登录接口")
    @RequestMapping(value = "/studentLogin", method = RequestMethod.POST)
    public Result<JSONObject> login(@RequestBody StudentLogin sysLoginModel){
        Result<JSONObject> result = new Result<JSONObject>();
        String number = sysLoginModel.getNumber();
        String password = sysLoginModel.getPassword();

        QueryWrapper<Student> studentQueryWrapper = new QueryWrapper<>();
        studentQueryWrapper=studentQueryWrapper.eq("number",number);
        Student student = studentService.getOne(studentQueryWrapper, false);
        if(student==null){
            result.error500("学号或密码错误");
            return result;
        }

        String userpassword = PasswordUtil.encrypt(password, Student.key, Student.salt);
        String syspassword = student.getPassword();
        if (!syspassword.equals(userpassword)) {
            result.error500("学号或密码错误");
            return result;
        }
        LoginStudent loginStudent = new LoginStudent();
        BeanUtils.copyProperties(student,loginStudent);
        String token = JWTUtil.StudentSign(loginStudent,Student.salt);
        JSONObject obj = new JSONObject(new LinkedHashMap<>());
        obj.put("token",token);
        obj.put("student",loginStudent);

        result.setResult(obj);
        return result;
    }



}
