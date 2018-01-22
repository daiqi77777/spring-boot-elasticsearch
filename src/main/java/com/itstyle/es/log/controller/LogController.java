package com.itstyle.es.log.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itstyle.es.log.entity.Pages;
import com.itstyle.es.log.entity.SysLogs;
import com.itstyle.es.log.service.LogService;
@Controller
@RequestMapping(value = "")
public class LogController {
   @Autowired
   private LogService logService;
	
   @RequestMapping(value="index",method=RequestMethod.GET)
   public String  index() {
		 return "log/index";
   }
   @RequestMapping(value="list",method=RequestMethod.POST)
   public @ResponseBody Pages<SysLogs>  list(Integer pageNumber,Integer pageSize,
		 Integer platFrom,String searchContent) {
	  return logService.searchLogPage(pageNumber, pageSize, platFrom, searchContent);
   }
}
