package com.atguigu.eduservice.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author Miracle Luna
 * @Date 2022/4/3 15:06
 * @Version 1.0
 */
@Data
public class TeacherQuery {

    @ApiModelProperty(value = "教师名称")
    private String name;

    @ApiModelProperty(value = "教师级别")
    private Integer level;

    @ApiModelProperty(value = "开始时间")
    private String begin;

    @ApiModelProperty(value = "结束时间")
    private String end;
}
