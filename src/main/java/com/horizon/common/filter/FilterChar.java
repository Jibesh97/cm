package com.horizon.common.filter;

 
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FilterChar implements Filter {
    private String config;

    public boolean filter(Object provider) {
        
        return false;
    }

    public void destroy() {
        this.config = null;
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        HttpServletRequest req;
        req = (HttpServletRequest) request;
        HttpServletResponse res;
        res = (HttpServletResponse) response;
        req.setCharacterEncoding(config);
        res.setCharacterEncoding(config);
        res.setHeader( "Cookie", "name=value; HttpOnly");
        chain.doFilter(req, res);

    }

    /**
     * 
     * Create date:2011-7-26
     * Method name:init
     * Description: 获得web.xml中初始化的参�?
     * @param config
     * @throws ServletException
     */
    public void init(FilterConfig config) throws ServletException {
        this.config = config.getInitParameter("chars");

    }

}
