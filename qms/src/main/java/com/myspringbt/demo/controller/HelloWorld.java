package com.myspringbt.demo.controller;

import com.myspringbt.demo.service.CommonService;
import com.myspringbt.demo.util.SpringUtil;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Controller
@RestController
@RequestMapping("v1/")
@Api("HelloWorldController")
public class HelloWorld {
    @GetMapping("helloworld")
    public String hellows() {
        String dev = SpringUtil.getActiveProfile();
        CommonService commonService = SpringUtil.getBean(CommonService.class);
        List<Map<String, Object>> a = commonService.findAllMessage();
        Boolean c = SpringUtil.iswindow();
        Boolean d = SpringUtil.ifDeveLopmentMode();

        String name = dev + c + d + a.toString();
        return name;
    }


}
