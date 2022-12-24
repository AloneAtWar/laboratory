package com.aloneatwar.laboratory.mapper;

import com.aloneatwar.laboratory.vo.response.WeelyReportInfo;
import org.apache.ibatis.annotations.Mapper;
import com.aloneatwar.laboratory.entity.WeeklyReports;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @Description: 周报表
 * @Author: jeecg-boot
 * @Date:   2022-12-23
 * @Version: V1.0
 */
@Mapper
public interface WeeklyReportsMapper extends BaseMapper<WeeklyReports> {
    List<WeelyReportInfo> findLaboratoryReport(@Param("id") String id, @Param("beginTime") Date beginTime,@Param("endTime") Date endTime);
    Integer haveSendLastWeeklyRepost(@Param("id") String id, @Param("number") String number,@Param("beginTime") Date beginTime,@Param("endTime") Date endTime);
}
