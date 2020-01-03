package com.classic.controller;

import com.classic.service.BaseService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/base")
public class BaseController {

    @Autowired
    private BaseService baseService;

    @ResponseBody
    @RequestMapping("/begin")
    public String begin() {
        System.out.printf("0000");
        return baseService.begin();
    }

    @ResponseBody
    @RequestMapping("/getList")
    public String getList() {

        return JSONArray.fromObject(baseService.getList()).toString();
    }


}
