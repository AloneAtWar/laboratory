package com.aloneatwar.laboratory.controller;

import com.alibaba.fastjson.JSONObject;
import com.aloneatwar.laboratory.entity.Laboratory;
import com.aloneatwar.laboratory.entity.LaboratoryStudent;
import com.aloneatwar.laboratory.entity.Student;
import com.aloneatwar.laboratory.service.ILaboratoryService;
import com.aloneatwar.laboratory.service.ILaboratoryStudentService;
import com.aloneatwar.laboratory.service.IStudentService;
import com.aloneatwar.laboratory.vo.response.BaseLaboratory;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("laboratory")
public class LaboratoryController {

    @Autowired
    private ILaboratoryService laboratoryService;
    @Autowired
    private ILaboratoryStudentService laboratoryStudentService;

    @Autowired
    private IStudentService studentService;

    @GetMapping("findDetail")
    public Result<BaseLaboratory> FindDetail(String id) {
        Result<BaseLaboratory> result = new Result<>();
        BaseLaboratory baseLaboratory = laboratoryService.getLabDetail(id);
        result.setResult(baseLaboratory);
        return result;
    }

    //寻找某个实验室的学生们
    @GetMapping("findStudent")
    public Result<List<LaboratoryStudent>> FindLaboratoryStudent(String id) {
        List<LaboratoryStudent> laboratoryStudents = laboratoryStudentService.selectByMainId(id);
        return Result.OK(laboratoryStudents);
    }

    //删除某个实验室的学生
    @DeleteMapping("deleteStudent")
    public Result<String> deleteStudent(String laboratoryStudentId) {
        laboratoryStudentService.removeById(laboratoryStudentId);
        return Result.OK("删除成功");
    }

    // 为实验室增加学生 若学生不存在则添加学生用户（密码与学号相同)
    @PostMapping("addStudent")
    public Result<String> addStudent(@RequestBody LaboratoryStudent student){
        QueryWrapper<Student> studentQueryWrapper = new QueryWrapper<Student>().eq("number", student.getStudentNumber());
        Student s = studentService.getOne(studentQueryWrapper, false);
        if(s==null){
            s=new Student();
            s.setName(student.getStudentName());
            s.setNumber(student.getStudentNumber());
            s.setPassword(PasswordUtil.encrypt(s.getNumber(),Student.key,Student.salt));
            studentService.save(s);
        }
        student.setCreateTime(new Date());
        laboratoryStudentService.save(student);
        return Result.OK("增加成功");
    }



}
