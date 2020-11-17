package com.guli.teacher.entity.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 条件查询的对象
 */
@Data
public class TeacherQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "教师名称,模糊查询")
    private String name;

    private String lever;

    private Integer level;

    @ApiModelProperty(value = "创建时间", example = "2019-01-01 8:00:00")
    private Date gmtCreate;

    @ApiModelProperty(value = "更新时间", example = "2019-01-01 8:00:00")
    private Date gmtModified;

}