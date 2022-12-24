package com.aloneatwar.laboratory.service.impl;

import com.aloneatwar.laboratory.entity.Laboratory;
import com.aloneatwar.laboratory.entity.LaboratoryStudent;

import com.aloneatwar.laboratory.mapper.LaboratoryMapper;
import com.aloneatwar.laboratory.mapper.LaboratoryStudentMapper;
import com.aloneatwar.laboratory.service.ILaboratoryService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Collection;

/**
 * @Description: 实验室表
 * @Author: jeecg-boot
 * @Date:   2022-12-23
 * @Version: V1.0
 */
@Service
public class LaboratoryServiceImpl extends ServiceImpl<LaboratoryMapper, Laboratory> implements ILaboratoryService {

	@Autowired
	private LaboratoryMapper laboratoryMapper;
	@Autowired
	private LaboratoryStudentMapper laboratoryStudentMapper;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveMain(Laboratory laboratory, List<LaboratoryStudent> laboratoryStudentList) {
		laboratoryMapper.insert(laboratory);
		if(laboratoryStudentList!=null && laboratoryStudentList.size()>0) {
			for(LaboratoryStudent entity:laboratoryStudentList) {
				//外键设置
				entity.setLaboratoryId(laboratory.getId());
				laboratoryStudentMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateMain(Laboratory laboratory,List<LaboratoryStudent> laboratoryStudentList) {
		laboratoryMapper.updateById(laboratory);
		
		//1.先删除子表数据
		laboratoryStudentMapper.deleteByMainId(laboratory.getId());
		
		//2.子表数据重新插入
		if(laboratoryStudentList!=null && laboratoryStudentList.size()>0) {
			for(LaboratoryStudent entity:laboratoryStudentList) {
				//外键设置
				entity.setLaboratoryId(laboratory.getId());
				laboratoryStudentMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delMain(String id) {
		laboratoryStudentMapper.deleteByMainId(id);
		laboratoryMapper.deleteById(id);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			laboratoryStudentMapper.deleteByMainId(id.toString());
			laboratoryMapper.deleteById(id);
		}
	}
	
}
