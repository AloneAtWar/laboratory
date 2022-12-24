package com.aloneatwar.laboratory.controller;

import com.aloneatwar.laboratory.entity.Laboratory;
import com.aloneatwar.laboratory.service.ILaboratoryStudentService;
import com.aloneatwar.laboratory.service.IStudentService;
import com.aloneatwar.laboratory.util.JWTUtil;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

public class StudentController {
    @Autowired
    private IStudentService studentService;

    @Autowired
    private ILaboratoryStudentService iLaboratoryStudentService;

    @GetMapping("findLaboratory")
    public Result<List<Laboratory>>  findLaboratory(@RequestHeader("student-token")String token){
        DecodedJWT jwt = JWTUtil.getJWT(token);
        String number = jwt.getClaim("number").asString();
//        iLaboratoryStudentService.list()
        // ....
        return null;
    }

}
