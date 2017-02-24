package com.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.service.IMyTestService;

@Controller
@RequestMapping("/test")
public class MyTestController {
	//使用@Resource注释从Spring容器中获取相应的对象
    @Resource(name = "myTestImpl")
    private IMyTestService myTestService;

    /* 注册用户 */
    @RequestMapping(value = "/mytest.do",
            method = RequestMethod.GET,
            produces = "application/json; encoding=UTF-8;charset=UTF-8")
    @ResponseBody
    public Object myTest(@RequestParam String data) {
        Object result= myTestService.selectSort(1);
        return result;
    }
}
