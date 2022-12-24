package com.aloneatwar.laboratory.controller;

import com.aloneatwar.laboratory.entity.Laboratory;
import com.aloneatwar.laboratory.entity.LaboratoryStudent;
import com.aloneatwar.laboratory.entity.WeeklyReports;
import com.aloneatwar.laboratory.service.ILaboratoryService;
import com.aloneatwar.laboratory.service.ILaboratoryStudentService;
import com.aloneatwar.laboratory.service.IStudentService;
import com.aloneatwar.laboratory.service.IWeeklyReportsService;
import com.aloneatwar.laboratory.util.JWTUtil;
import com.aloneatwar.laboratory.vo.response.WeelyReportInfo;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.servlet.http.HttpServletRequest;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @Autowired
    private IWeeklyReportsService weeklyReportsService;

    //  根据token返回学生参加实验室信息
    @GetMapping("/findLaboratory")
    public Result<List<Laboratory>> findLaboratory(@RequestHeader("student-token")String token){
        DecodedJWT jwt = JWTUtil.getJWT(token);
        String number = jwt.getClaim("number").asString();
        List<Laboratory> list = laboratoryService.getLabsByStuNumber(number);
        return Result.ok(list);
    }

    //  新增或修改周报
    @PostMapping("/saveOrUpdateWeeklyReport")
    public Result<WeeklyReports> saveOrUpdateWeeklyReport(@RequestBody WeeklyReports weeklyReports){
        boolean flag = weeklyReportsService.saveOrUpdate(weeklyReports);
        if (flag){
            return Result.ok("新增或修改周报成功");
        }
        return Result.error("新增或修改周报失败");
    }

    //  查询学生历史周报按时间排序
    @GetMapping("/getHistoryWeeklyReportsByNumber")
    public Result<IPage<WeeklyReports>> getHistoryWeeklyReports(String number,
                                                               @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                                               @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                                               HttpServletRequest req){
        QueryWrapper<WeeklyReports> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("number",number).orderByAsc("create_time");
        Page<WeeklyReports> page = new Page<WeeklyReports>(pageNo, pageSize);
        IPage<WeeklyReports> pageList = weeklyReportsService.page(page, queryWrapper);
        return Result.OK(pageList);
    }

    @GetMapping ("/haveSendWeeklyReport")
    private boolean haveSendWeeklyReport(@RequestParam(name = "number") String number){
        List<WeelyReportInfo> list = weeklyReportsService.findLaboratoryReport(number);
        if ()
    }


}
