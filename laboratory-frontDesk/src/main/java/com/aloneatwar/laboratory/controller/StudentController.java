package com.aloneatwar.laboratory.controller;

import com.aloneatwar.laboratory.entity.Laboratory;
import com.aloneatwar.laboratory.entity.LaboratoryStudent;
import com.aloneatwar.laboratory.service.ILaboratoryService;
import com.aloneatwar.laboratory.service.ILaboratoryStudentService;
import com.aloneatwar.laboratory.service.IStudentService;
import com.aloneatwar.laboratory.util.JWTUtil;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.jeecg.common.api.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
/**
 * @Description: 学生操作
 * @Author: 吴蜀魏
 * @Date:   2022-12-24
 * @Version: V1.0
 */
@RestController
@RequestMapping("student")
public class StudentController {
    @Autowired
    private IStudentService studentService;
    @Autowired
    private ILaboratoryStudentService laboratoryStudentService;
    @Autowired
    private ILaboratoryService laboratoryService;

    @GetMapping("findLaboratory")
    public Result<List<Laboratory>>  findLaboratory(@RequestHeader("student-token")String token){
        DecodedJWT jwt = JWTUtil.getJWT(token);
        String number = jwt.getClaim("number").asString();
        List<Laboratory> list = laboratoryService.getLabsByStuNumber(number);
        return Result.ok(list);
    }

}
