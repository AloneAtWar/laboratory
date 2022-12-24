package com.aloneatwar.laboratory.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.api.vo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api("测试Controller")
public class PingController {

    @ApiOperation("测试")
    @GetMapping("/ping")
    public Result<String> ping(){
        return new Result<>().OK("pong");
    }
}
