package com.aloneatwar.laboratory.controller;


import com.aloneatwar.laboratory.entity.Student;
import com.aloneatwar.laboratory.entity.WeeklyReports;
import com.aloneatwar.laboratory.service.IWeeklyReportsService;
import com.aloneatwar.laboratory.vo.response.WeelyReportInfo;
import org.jeecg.common.api.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("weeklyReport")
public class WeeklyReportController {
    @Autowired
    private IWeeklyReportsService weeklyReportsService;
    @GetMapping("findLaboratoryReport")
    public Result<List<WeelyReportInfo>> findLaboratoryReport(String id){
        List<WeelyReportInfo> laboratoryReport = weeklyReportsService.findLaboratoryReport(id);
        return Result.OK(laboratoryReport);
    }

//    // 找一下未提交人的信息
//    @GetMapping("findUnhandReport")
//    public Result<Student> findUnhandReport(String id){
//
//    }


    @GetMapping("findDetail")
    public Result<WeeklyReports>  findDetail(String id){
        WeeklyReports reports = weeklyReportsService.getById(id);
        return Result.OK(reports);
    }

}
