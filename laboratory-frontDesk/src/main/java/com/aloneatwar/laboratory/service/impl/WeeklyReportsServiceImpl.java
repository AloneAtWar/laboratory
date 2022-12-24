package com.aloneatwar.laboratory.service.impl;

import com.aloneatwar.laboratory.entity.WeeklyReports;

import com.aloneatwar.laboratory.mapper.WeeklyReportsMapper;
import com.aloneatwar.laboratory.service.IWeeklyReportsService;
import com.aloneatwar.laboratory.util.WeekUtil;
import com.aloneatwar.laboratory.vo.response.WeelyReportInfo;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 周报表
 * @Author: jeecg-boot
 * @Date:   2022-12-23
 * @Version: V1.0
 */
@Service
public class WeeklyReportsServiceImpl extends ServiceImpl<WeeklyReportsMapper, WeeklyReports> implements IWeeklyReportsService {

    @Override
    public List<WeelyReportInfo> findLaboratoryReport(String id) {
        List<WeelyReportInfo> laboratoryReport = baseMapper.findLaboratoryReport(id, WeekUtil.getBeginDayOfWeek(), WeekUtil.getEndDayOfWeek());
        return laboratoryReport;
    }

    @Override
    public boolean haveSendLastWeeklyRepost(String id, String number) {
        Integer count = baseMapper.haveSendLastWeeklyRepost(id, number, WeekUtil.getBeginDayOfWeek(), WeekUtil.getEndDayOfWeek());
        if (count > 0){
            return true;
        }
        return false;
    }

}
