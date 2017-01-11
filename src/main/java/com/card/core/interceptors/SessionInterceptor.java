package com.card.core.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import com.card.core.constants.SysConstants;
import com.card.core.utils.ValidatorUtils;

/**
 * 登录session拦截器
 * 项目未登录时无法访问项目资源路径
 * <br>
 * 
 * 项目名称：CreditCardCore<br>
 * 项目版本：V1.0 <br>
 * 类名称：SessionInterceptor <br>
 * 创建人：yuqy <br>
 * 创建时间：2016年12月23日 下午4:09:34 <br>
 * 修改人：yuqy <br>
 * 修改时间：2016年12月23日 下午4:09:34 <br>
 * 修改备注：<br>
 */
public class SessionInterceptor implements HandlerInterceptor{
	
	/**
	 *请求之前函数
	 *return true : 继续访问
	 *return false : 无法继续访问 
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//截取访问路径
		String uri = request.getRequestURI();
		uri = uri.substring(request.getContextPath().length());
		//获取登录session
		HttpSession session = request.getSession();
		//排除不需要拦截的路径
		if (SysConstants.NO_SESSION_URLS.contains(uri)) {
			return true;
		}
		//登录对象
		Object obj = session.getAttribute(SysConstants.SESSION_USER);
		//如果登录对象为空,跳转到登录页面
		if(ValidatorUtils.isEmpty(obj))
		{
			response.sendRedirect("/login");
			return false;
		}
		return true;
	}
}
