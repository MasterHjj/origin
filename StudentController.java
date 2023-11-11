package com.group15.controller;

import com.group15.pojo.*;
import com.group15.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;


@Slf4j
@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;
    /*
     * 功能：根据id删除学生
     * 请求类型 DELETE
     * 请求路径 /students/{id}
     * 前端参数格式 路径参数
     * */
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Integer id){
        log.info("根据id删除学生: {}", id);
        studentService.delete(id);
        return Result.success();
    }

    /*
     * 功能：新增学生
     * 请求类型 POST
     * 请求路径 /students
     * 前端参数格式 application/json
     * */
    @PostMapping()
    public Result add(@RequestBody Student student){
        log.info("新增学生: {}", student);
        studentService.add(student);
        return Result.success();
    }

    /*
     * 功能：条件分页查询
     * 请求类型 GET
     * 请求路径 /students/page = ? & pageSize = ? & name = ?
     * 前端参数格式 queryString
     * */
    @GetMapping()
    public Result page(@RequestParam(defaultValue = "1")Integer page,
                       @RequestParam(defaultValue = "10")Integer pageSize,
                       @RequestParam(required = false, defaultValue = "")String name,
                       @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startTime,
                       @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endTime){
        log.info("分页查询: {}, {}, {}, {}, {}", page, pageSize, name, startTime, endTime);
        PageBean pageBean = studentService.page(page,pageSize,name,startTime,endTime);
        return Result.success(pageBean);
    }

    /*
     * 功能：根据id查询学生
     * 请求类型 GET
     * 请求路径 /students/{id}
     * 前端参数格式 路径参数
     * */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        log.info("根据id查询学生：{}",id);
        Student student = studentService.getById(id);
        return Result.success(student);
    }

    /*
     * 功能： 更新学生信息
     * 请求类型 PUT
     * 请求路径 /students
     * 前端请求参数 application/json
     * */
    @PutMapping
    public Result update(@RequestBody Student student){
        log.info("更新学生信息：{}", student);
        studentService.update(student);
        return Result.success();
    }

}
