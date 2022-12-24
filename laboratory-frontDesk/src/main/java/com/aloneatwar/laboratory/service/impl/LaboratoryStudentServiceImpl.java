package com.aloneatwar.laboratory.service.impl;

import com.aloneatwar.laboratory.entity.LaboratoryStudent;

import com.aloneatwar.laboratory.mapper.LaboratoryStudentMapper;
import com.aloneatwar.laboratory.service.ILaboratoryStudentService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 实验室学生表
 * @Author: jeecg-boot
 * @Date:   2022-12-23
 * @Version: V1.0
 */
@Service
public class LaboratoryStudentServiceImpl extends ServiceImpl<LaboratoryStudentMapper, LaboratoryStudent> implements ILaboratoryStudentService {
	
	@Autowired
	private LaboratoryStudentMapper laboratoryStudentMapper;
	
	@Override
	public List<LaboratoryStudent> selectByMainId(String mainId) {
		return laboratoryStudentMapper.selectByMainId(mainId);
	}
}
