package org.jeecg.modules.laboratory.service.impl;

import com.aloneatwar.laboratory.entity.Student;
import org.jeecg.modules.laboratory.mapper.StudentMapper;
import org.jeecg.modules.laboratory.service.IStudentService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 学生表
 * @Author: jeecg-boot
 * @Date:   2022-12-23
 * @Version: V1.0
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {

}
