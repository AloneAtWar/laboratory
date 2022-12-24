package com.aloneatwar.laboratory.vo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class StudentLogin {
    @ApiModelProperty(value = "学号")
    private String number;
    @ApiModelProperty(value = "密码")
    private String password;


}
