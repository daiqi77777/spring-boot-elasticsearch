package com.itstyle.es.log.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itstyle.es.log.service.LogService;
@RestController
public class LogRestController {
	@Autowired
	private LogService logService;
	 /**
	  * 首页
	  */
    @RequestMapping(value = "/index")
    public String  index() {
        return "index";
    }
}
