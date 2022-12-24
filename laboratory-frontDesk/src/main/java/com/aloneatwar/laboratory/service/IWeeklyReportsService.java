package com.aloneatwar.laboratory.service;

import com.aloneatwar.laboratory.entity.WeeklyReports;
import com.aloneatwar.laboratory.vo.response.WeelyReportInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description: 周报表
 * @Author: jeecg-boot
 * @Date:   2022-12-23
 * @Version: V1.0
 */
//select a.id,a.publisher,student.name,a.create_time as studentName from weekly_reports a left join student on student.number=publisher
public interface IWeeklyReportsService extends IService<WeeklyReports> {
    public List<WeelyReportInfo> findLaboratoryReport(String id);
}
