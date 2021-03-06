/**
 * FileName:     UserMapperController.java
 * @Description: TODO
 * All rights Reserved, Designed By ALIBABA.COM  
 * Copyright:    Copyright(C) 1998-2015
 * Company       ALIBABA
 * @author:    asua
 * @version    V1.0 
 * Createdate:         2017年7月16日 下午3:14:33
 *
 * Modification  History:
 * Date         Author        Version        Discription
 * -----------------------------------------------------------------------------------
 * 2017年7月16日       CQCN         1.0             1.0
 * Why & What is modified: <修改原因描述>
 */
 
package com.ecut.demo.web;

import com.ecut.demo.entity.User;
import com.ecut.demo.service.UserService;
import com.github.pagehelper.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @title:
 * @description:
 * @author songshuiyang
 * @date 2017年7月16日
 */
@Controller
@RequestMapping("/jdbc")
public class UserMapperController {
	
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@Resource
	private UserService userService;
	
	
	@RequestMapping(value="/list",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView list(){
		ModelAndView modelAndView=new ModelAndView();
		List<User> list=userService.queryAll();
		modelAndView.setViewName("helloWord");
		modelAndView.addObject("list",list);
		return modelAndView;
	}
	@RequestMapping(value="/add",method={RequestMethod.GET,RequestMethod.POST})
	public void list(@ModelAttribute("user") User user,HttpServletResponse response) throws IOException{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter printWriter=response.getWriter();
		printWriter.append("你好"+user.getName());
		logger.info("--------------->>"+user.toString());
	}

    @RequestMapping(value = "/query", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> query(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize, User user) {
        Map<String, Object> map = new HashMap<>();
        List<User> userList = new ArrayList<>();
        Page<User> users = userService.findPageList(pageNum, pageSize, user);

        map.put("records", users);
        map.put("success", true);
        map.put("totol", users.getTotal());
        map.put("status", 0);
        return map;
    }
}
