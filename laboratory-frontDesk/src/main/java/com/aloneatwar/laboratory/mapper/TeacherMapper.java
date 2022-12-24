package com.aloneatwar.laboratory.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.aloneatwar.laboratory.entity.Teacher;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 老师表
 * @Author: jeecg-boot
 * @Date:   2022-12-23
 * @Version: V1.0
 */
@Mapper
public interface TeacherMapper extends BaseMapper<Teacher> {

}
