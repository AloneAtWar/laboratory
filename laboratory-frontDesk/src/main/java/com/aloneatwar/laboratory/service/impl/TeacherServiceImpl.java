package com.aloneatwar.laboratory.service.impl;

import com.aloneatwar.laboratory.entity.Teacher;

import com.aloneatwar.laboratory.mapper.TeacherMapper;
import com.aloneatwar.laboratory.service.ITeacherService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 老师表
 * @Author: jeecg-boot
 * @Date:   2022-12-23
 * @Version: V1.0
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements ITeacherService {

}
