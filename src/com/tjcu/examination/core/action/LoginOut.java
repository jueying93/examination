/**
 * 
 */
package com.tjcu.examination.core.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.tjcu.examination.core.constant.Constant;

/**
 * @author Administrator
 *
 */
public class LoginOut extends ActionSupport {
	
	public String loginOut(){
		ActionContext.getContext().getSession().remove(Constant.USER);
		return "index";
	}

}
