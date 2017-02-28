package com.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.service.IAdminService;

@Controller
@RequestMapping("/test")
public class MyTestController {
	//使用@Resource注释从Spring容器中获取相应的对象
    @Resource
    private IAdminService adminService;

    /* 注册用户 */
    @RequestMapping(value = "/mytest.do",
            method = RequestMethod.GET,
            produces = "application/json; encoding=UTF-8;charset=UTF-8")
    @ResponseBody
    public Object myTest(@RequestParam String data) {
        Object result= adminService.SelectAdmin(1);
        return result;
    }
    org.apache.commons.net.ftp.FTPClient ftpClient = new org.apache.commons.net.ftp.FTPClient();
}
