package org.jeecg.modules.laboratory.service;

import com.aloneatwar.laboratory.entity.LaboratoryStudent;
import com.aloneatwar.laboratory.entity.Laboratory;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 实验室表
 * @Author: jeecg-boot
 * @Date:   2022-12-23
 * @Version: V1.0
 */
public interface ILaboratoryService extends IService<Laboratory> {

	/**
	 * 添加一对多
	 *
	 * @param laboratory
	 * @param laboratoryStudentList
	 */
	public void saveMain(Laboratory laboratory,List<LaboratoryStudent> laboratoryStudentList) ;
	
	/**
	 * 修改一对多
	 *
	 * @param laboratory
	 * @param laboratoryStudentList
	 */
	public void updateMain(Laboratory laboratory,List<LaboratoryStudent> laboratoryStudentList);
	
	/**
	 * 删除一对多
	 *
	 * @param id
	 */
	public void delMain (String id);
	
	/**
	 * 批量删除一对多
	 *
	 * @param idList
	 */
	public void delBatchMain (Collection<? extends Serializable> idList);
	
}
