package com.tjcu.examination.home.action;

import com.opensymphony.xwork2.ActionSupport;



public class HomeAction extends ActionSupport {
	
	//跳转到纳税服务系统的首页
	public String frame(){
		return "frame";
	}
	//跳转到纳税服务系统首页-顶部
	public String top(){
		return "top";
	}
	//跳转到纳税服务系统首页-左边菜单
	public String left(){
		return "left";	
	}
}
