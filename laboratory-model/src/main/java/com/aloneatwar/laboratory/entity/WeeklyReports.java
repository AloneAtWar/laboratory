package com.aloneatwar.laboratory.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecg.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 周报表
 * @Author: jeecg-boot
 * @Date:   2022-12-23
 * @Version: V1.0
 */
@Data
@TableName("weekly_reports")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="weekly_reports对象", description="周报表")
public class WeeklyReports implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private String id;
	/**创建人*/
	@Excel(name = "创建人", width = 15, dictTable = "student", dicText = "name", dicCode = "id")
	@Dict(dictTable = "student", dicText = "name", dicCode = "id")
    @ApiModelProperty(value = "创建人")
    private String publisher;
	/**实验室*/
	@Excel(name = "实验室", width = 15, dictTable = "laboratory", dicText = "name", dicCode = "id")
	@Dict(dictTable = "laboratory", dicText = "name", dicCode = "id")
    @ApiModelProperty(value = "实验室")
    private String laboratoryId;
	/**每周总结*/
	@Excel(name = "每周总结", width = 15)
    @ApiModelProperty(value = "每周总结")
    private String weeklySummary;
	/**会议纪要*/
	@Excel(name = "会议纪要", width = 15)
    @ApiModelProperty(value = "会议纪要")
    private String meetingMinutes;
	/**下周计划*/
	@Excel(name = "下周计划", width = 15)
    @ApiModelProperty(value = "下周计划")
    private String nextWeekPlan;
	/**创建时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
}
