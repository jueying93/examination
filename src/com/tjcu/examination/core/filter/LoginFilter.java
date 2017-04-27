/**
 * 
 */
package com.tjcu.examination.core.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tjcu.examination.core.constant.Constant;



/**
 * @author Administrator
 *
 */
public class LoginFilter implements Filter {
	
	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain chain) throws IOException, ServletException {
		//在使用过滤器的时候，我们一般都要做这样的强转
		HttpServletRequest request = (HttpServletRequest)arg0;
		HttpServletResponse response = (HttpServletResponse)arg1;
		String uri = request.getRequestURI();
		//判断当前请求地址是否是登录url,
		if(!uri.contains("Home")){
			if(request.getSession().getAttribute(Constant.USER) != null){
				//已经登录 放行
				chain.doFilter(request, response);
			}
			else{		
				//没有登录，跳转到登录页面去
				response.sendRedirect(request.getContextPath()+"/index.jsp");
			}
		}else{
			//登录请求，直接放行。
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		

	}

	
	


	
}
