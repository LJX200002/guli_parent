package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.pojo.EduTeacher;
import com.atguigu.eduservice.pojo.vo.TeacherQuery;
import com.atguigu.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-04-02
 */
@Api("教师管理")
@RestController
@RequestMapping("/edu-teacher")
public class EduTeacherController {

    @Autowired
    private EduTeacherService eduTeacherService;

    @ApiOperation(value = "所有讲师列表")
    @GetMapping("/findAll")
    public R findAllTeacher(){
        return R.ok().data("list",eduTeacherService.list(null));
    }


    //使用@DeleteMapping测试方法 1.swagger 2.postman
    @ApiOperation(value = "逻辑删除信息")
    @DeleteMapping("/delete/{id}")
    public R deleteTeacherById(@ApiParam(name="id", value = "教师ID",required = true) @PathVariable("id") String id){
        boolean b = eduTeacherService.removeById(id);
        if(b==true){
            return R.ok();
        }else{
            return R.error();
        }
    }

    @GetMapping("pageTeacher/{pn}/{limit}")
    public R pageListTeacher(@PathVariable("pn") int pn,@PathVariable("limit") int limit){
        Page<EduTeacher> page = new Page<>(pn,limit);
        IPage<EduTeacher> page1 = eduTeacherService.page(page, null);


        return R.ok().data("page",page1);
    }

    @ApiOperation(value = "条件查询加上分页")
    @PostMapping("pageTeacherCondition/{pn}/{limit}")
    public R pageTeacherCondition(@PathVariable("pn") int pn,
                                  @PathVariable("limit") int limit,
                                  @RequestBody(required = false) TeacherQuery teacherQuery){
        //条件
        QueryWrapper<EduTeacher> wrapper=new QueryWrapper<>();

        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        if (!StringUtils.isEmpty(name)){
            wrapper.like("name",name);
        }
        if (!StringUtils.isEmpty(level)){
            wrapper.eq("level",level);
        }
        if (!StringUtils.isEmpty(begin)){
            wrapper.ge("gmt_create",begin);
        }
        if(!StringUtils.isEmpty(end)){
            wrapper.le("gmt_create",end);
        }


        Page<EduTeacher> page=new Page<>(pn,limit);
        IPage<EduTeacher> page1 = eduTeacherService.page(page, wrapper);

        return R.ok().data("page",page1);
    }

    @PostMapping
    public R addTeacher(@RequestBody EduTeacher eduTeacher){
        System.out.println(eduTeacher);
        boolean save = eduTeacherService.save(eduTeacher);
        if (save==true){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @GetMapping("getTeacher/{id}")
    public R getTeacher(@PathVariable("id") String id){
        EduTeacher byId = eduTeacherService.getById(id);
        return R.ok().data("byId",byId);
    }

    @PostMapping("updateTeacher")
    public R updateTeacher(@RequestBody EduTeacher eduTeacher){
        boolean b = eduTeacherService.updateById(eduTeacher);
        if(b==true){
            return R.ok();
        }else{
            return R.error();
        }
    }

}

