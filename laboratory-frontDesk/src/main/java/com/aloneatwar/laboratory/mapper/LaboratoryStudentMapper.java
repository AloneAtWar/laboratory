package com.aloneatwar.laboratory.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.aloneatwar.laboratory.entity.LaboratoryStudent;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: 实验室学生表
 * @Author: jeecg-boot
 * @Date:   2022-12-23
 * @Version: V1.0
 */
@Mapper
public interface LaboratoryStudentMapper extends BaseMapper<LaboratoryStudent> {

	/**
	 * 通过主表id删除子表数据
	 *
	 * @param mainId 主表id
	 * @return boolean
	 */
	public boolean deleteByMainId(@Param("mainId") String mainId);

  /**
   * 通过主表id查询子表数据
   *
   * @param mainId 主表id
   * @return List<LaboratoryStudent>
   */
	public List<LaboratoryStudent> selectByMainId(@Param("mainId") String mainId);
}
