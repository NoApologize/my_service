package com.classic.controller;

import com.classic.service.BaseService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/base")
public class BaseController {

    @Autowired
    private BaseService baseService;

    @RequestMapping("/begin")
    public String begin() {
        return baseService.begin();
    }

    @GetMapping("/getList")
    public String getList() {
        return JSONArray.fromObject(baseService.getList()).toString();
    }


}
