package com.aloneatwar.laboratory.controller;

import com.alibaba.fastjson.JSONObject;
import com.aloneatwar.laboratory.entity.Teacher;
import com.aloneatwar.laboratory.vo.request.TeacherLoginModel;
import com.aloneatwar.laboratory.service.ITeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/sys")
@Api(tags="用户登录")
@Slf4j
public class LoginController {
    @Autowired
    private ITeacherService teacherService;
    @ApiOperation("老师登录接口")
    @RequestMapping(value = "/teacherLogin", method = RequestMethod.POST)
    public Result<JSONObject> login(@RequestBody TeacherLoginModel sysLoginModel){
        Result<JSONObject> result = new Result<JSONObject>();
        String name = sysLoginModel.getName();
        String password = sysLoginModel.getPassword();
        QueryWrapper<Teacher> teacherQueryWrapper = new QueryWrapper<>();
        teacherQueryWrapper=teacherQueryWrapper.eq("name",name);
        //  老师  学生
        //学生: 登录 参加了那些实验室，返回实验室列表 查看实验室详细信息 查看本周是否交过周报 提交周报 查看历史周报
        //老师：登录 录入学生(学号 学生名字） 对实验室的学生进行CRUD  看周报
        Teacher teacher = teacherService.getOne(teacherQueryWrapper,false);
        if(teacher==null){

        }
//        result.
        return result;
    }
}
