package org.jeecg.modules.laboratory.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.aloneatwar.laboratory.entity.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 学生表
 * @Author: jeecg-boot
 * @Date:   2022-12-23
 * @Version: V1.0
 */
@Mapper
public interface StudentMapper extends BaseMapper<Student> {

}
