package com.aloneatwar.laboratory.vo.request;

import io.swagger.annotations.ApiModelProperty;

public class TeacherLoginModel {
    @ApiModelProperty(value = "账号")
    private String name;
    @ApiModelProperty(value = "密码")
    private String password;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



}
