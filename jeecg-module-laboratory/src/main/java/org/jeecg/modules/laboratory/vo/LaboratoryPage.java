package org.jeecg.modules.laboratory.vo;

import java.util.List;

import com.aloneatwar.laboratory.entity.LaboratoryStudent;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: 实验室表
 * @Author: jeecg-boot
 * @Date:   2022-12-23
 * @Version: V1.0
 */
@Data
@ApiModel(value="laboratoryPage对象", description="实验室表")
public class LaboratoryPage {

	/**主键*/
	@ApiModelProperty(value = "主键")
    private java.lang.String id;
	/**图标*/
	@Excel(name = "图标", width = 15)
	@ApiModelProperty(value = "图标")
    private java.lang.String logoString;
	/**名称*/
	@Excel(name = "名称", width = 15)
	@ApiModelProperty(value = "名称")
    private java.lang.String name;
	/**描述*/
	@Excel(name = "描述", width = 15)
	@ApiModelProperty(value = "描述")
    private java.lang.String des;
	/**指导老师*/
	@Excel(name = "指导老师", width = 15)
	@ApiModelProperty(value = "指导老师")
    private java.lang.String teacher;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "创建日期")
    private java.util.Date createTime;
	
	@ExcelCollection(name="实验室学生表")
	@ApiModelProperty(value = "实验室学生表")
	private List<LaboratoryStudent> laboratoryStudentList;


	//  视图展示      怎么展示      -》 request response   request   发来的请求的模型的样子    response   返回响应的模型的样子
}
