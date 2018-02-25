package com.horizon.common.spring;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.horizon.common.constants.ConstantsInfo;

/**
 * 
 * <P>
 * FileName: SecurityHandlerInterceptor.java
 * 
 * @author Administrator
 *         <P>
 *         CreateTime: Nov 8, 2011 9:37:04 AM
 *         <P>
 *         Description: 进行系统安全校验
 * 
 *         <P>
 *         Version:v1.0
 *         <P>
 *         History:
 * @param
 */
public class SecurityHandlerInterceptor extends HandlerInterceptorAdapter {

    /**
     * 
     */
    private static Logger log = Logger.getLogger(SecurityHandlerInterceptor.class);
    /**
     * 
     * <P>
     * Function: preHandle
     * <P>
     * Description:
     * <P>
     * Others:
     * 
     * @author: Administrator
     * @CreateTime: Nov 8, 2011 9:38:24 AM
     * @param request
     * @param response
     * @param handler
     * @return boolean
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
            Object handler) throws Exception {
    	
        // 就简单判断了，如果要详细控制可以用spring security
//        String url = request.getRequestURI();
//        HttpSession session = request.getSession(false);
//        if (url.endsWith("login") || url.endsWith("chick_captcha")
//                || url.endsWith("serch_captcha_config")) {
//            return true;
//        }
//        if (session != null
//                && session.getAttribute(ConstantsInfo.USER_TOKEN) != null) {
//            return true;
//        } 
        log.error("========can not find SESSION ===========");
        // 判断session 中是否有token，有token 通过token获取机构信息
//        if (!request.isRequestedSessionIdValid()
//                || (null != session && ConstantsInfo.USER_SESSION_INVALIDATE_IS_TRUE.equals(session
//                        .getAttribute(ConstantsInfo.USER_SESSION_INVALIDATE_STATUS)))) {
//            request.getSession(true).setAttribute(ConstantsInfo.USER_SESSION_INVALIDATE_STATUS,
//            		ConstantsInfo.USER_SESSION_INVALIDATE_IS_TRUE);
//            response.sendRedirect(request.getContextPath() + "/system/sysuser/relogin");
//            return false;
//        }
//        response.sendRedirect(request.getContextPath() + "/index.jsp");
        return true;
    }

}
