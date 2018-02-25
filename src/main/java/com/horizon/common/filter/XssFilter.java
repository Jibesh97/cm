package com.horizon.common.filter;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class XssFilter implements Filter {

    @Override
    public void destroy() {
        // to nothing
    }

     @Override  
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        XssHttpServletRequestWraper xssRequest = new XssHttpServletRequestWraper((HttpServletRequest) request);
        chain.doFilter(xssRequest, response);
    }  

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        // do nothing
    }

}