package com.aloneatwar.laboratory.controller;

import com.aloneatwar.laboratory.entity.Laboratory;
import com.aloneatwar.laboratory.service.ILaboratoryService;
import com.aloneatwar.laboratory.service.ILaboratoryStudentService;
import com.aloneatwar.laboratory.service.impl.LaboratoryServiceImpl;
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

@RestController
@RequestMapping("teacher")
public class TeacherController {

    @Autowired
    private ILaboratoryService laboratoryService;

//    @Autowired
//    private ILaboratoryStudentService

    //寻找老师管理的实验室
    @GetMapping("/findLaboratory")
    public Result<List<Laboratory>> findLaboratory(@RequestHeader("teacher-token")String token){
        DecodedJWT jwt = JWTUtil.getJWT(token);
        String id = jwt.getClaim("id").asString();
        QueryWrapper<Laboratory> wrapper = new QueryWrapper<>();
        wrapper=wrapper.eq("teacher",id);
        List<Laboratory> list = laboratoryService.list(wrapper);
        return Result.OK(list);
    }


}
