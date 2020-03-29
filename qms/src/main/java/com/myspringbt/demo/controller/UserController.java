package com.myspringbt.demo.controller;

import com.myspringbt.demo.model.JsonResult;
import com.myspringbt.demo.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Controller
@RestController
@RequestMapping("v1/User")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/getAllUser")
    public JsonResult getAllUser() {
        List<Map<String, Object>> result = userService.getAllUser();
        JsonResult j = new JsonResult();
        j.setData(result);
        return j;
    }
    @GetMapping("/searchname")
    public JsonResult searchname(@RequestParam(name = "name",required = true)String name) {
        List<Map<String, Object>> result = userService.searchname(name);
        JsonResult j = new JsonResult();
        j.setData(result);
        return j;
    }

    @GetMapping("/sendemail")
    public JsonResult sendEmail(String emamil){
        userService.sendEmail(emamil);
        return  new JsonResult();
    }
}
