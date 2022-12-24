package com.aloneatwar.laboratory.service;

import com.aloneatwar.laboratory.entity.LaboratoryStudent;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 实验室学生表
 * @Author: jeecg-boot
 * @Date:   2022-12-23
 * @Version: V1.0
 */

public interface ILaboratoryStudentService extends IService<LaboratoryStudent> {

	/**
	 * 通过主表id查询子表数据
	 *
	 * @param mainId 主表id
	 * @return List<LaboratoryStudent>
	 */
	public List<LaboratoryStudent> selectByMainId(String mainId);
}
