package com.aloneatwar.laboratory.controller;


import com.alibaba.fastjson.JSONObject;
import com.aloneatwar.laboratory.entity.WeeklyReports;
import com.aloneatwar.laboratory.service.IWeeklyReportsService;
import org.checkerframework.checker.units.qual.A;
import org.jeecg.common.api.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("weeklyReport")
public class WeeklyReportController {
    @Autowired
    private IWeeklyReportsService weeklyReportsService;
    @GetMapping("findLaboratoryReport")
    public Result<JSONObject>  findLaboratoryReport(String id){
        return null;
    }


    @GetMapping("findDetail")
    public Result<WeeklyReports>  findDetail(String id){
        WeeklyReports reports = weeklyReportsService.getById(id);
        return Result.OK(reports);
    }

}
