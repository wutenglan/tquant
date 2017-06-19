package com.goldskyer.tquant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class MainController {

	@RequestMapping("index.htm")
	public ModelAndView index()
	{
		ModelAndView mv=new ModelAndView("index");
		return mv ;
	}
}
