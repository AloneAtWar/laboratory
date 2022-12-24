package com.aloneatwar.laboratory.controller;

import com.alibaba.fastjson.JSONObject;
import com.aloneatwar.laboratory.entity.Laboratory;
import com.aloneatwar.laboratory.service.ILaboratoryService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.jeecg.common.api.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("laboratory")
public class LaboratoryController {

    @Autowired
    private ILaboratoryService laboratoryService;
    @GetMapping("findDetail")
    public Result<Laboratory> FindDetail(String id){
        Result<Laboratory> result = new Result<>();
        Laboratory laboratory = laboratoryService.getById(id);
        result.setResult(laboratory);
        return result;
    }

}
