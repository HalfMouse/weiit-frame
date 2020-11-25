package com.weiit.core.filter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.weiit.core.utils.MD5Util;
import com.weiit.core.utils.ValidateUtil;

/**
 * 
 * @author ~~迷途~~
 * @date：2017-8-19 下午8:47:02
 * @version 1.0
 * @company http://www.wei-it.com
 */
public class ValidateFilter implements Filter{

	@Override
	public void destroy() {
		
	}
	
	/**
	 * 拦截参数并校验token，主要用于编辑、删除传参时对参数进行token校验，如果参数没有被篡改则放行。
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain fileter) throws IOException, ServletException {
		 
		String[] validate_ids=request.getParameterValues("validate_id");
		String[] validate_id_tokens=request.getParameterValues("validate_id_token");
		
		//如果是表单multipart/form-data提交
		//MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;  
		//if(request.getParameterValues("validate_id")==null){
			//validate_ids=multipartRequest.getParameterValues("validate_id");
			//validate_id_tokens=multipartRequest.getParameterValues("validate_id_token");
		//}
		boolean flag=true;
		if(validate_ids!=null && validate_ids.length>0){
			for (int i = 0; i < validate_ids.length; i++) {
				if(validate_ids[i]!=null && validate_ids[i]!=""){
					if(!validate_id_tokens[i].equals(
							ValidateUtil.getValidateToken(validate_ids[i]))){
						flag=false;
						break;
					}
				}
			}
		}
		if (!flag) {
			request.getRequestDispatcher("/center/401.jsp").forward(request, response);
			return;
		}
		fileter.doFilter(request, response);
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
