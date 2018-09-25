package com.me.ljheee.tree.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ParamsInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        final HttpServletRequest request = httpServletRequest;
        request.getRequestURL();// http://localhost:8080/CarsiLogCenter_new/idpstat.jsp
        request.getRequestURI();// CarsiLogCenter_new/idpstat.jsp
        request.getContextPath();//CarsiLogCenter_new
        request.getServletPath();//idpstat.jsp

        String queryString = request.getQueryString();// action=idp.sptopn

        String method =  request.getMethod();

        System.out.println("method="+method+queryString);
        return true;// 只有返回true才会继续向下执行，返回false取消当前请求
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
