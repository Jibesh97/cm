package com.horizon.sm.user.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.send.sm.token.service.ITokenUserCenterService;
import com.send.sm.token.vo.UserCenterVO;
import com.horizon.common.util.SysContextUtil;
import com.horizon.sm.user.vo.TokenVO;

/**
 * <P>
 * FileName: SysUserController.java
 * 
 * @author lzd
 *         <P>
 *         CreateTime: 2017-05-11 上午10:46:50
 *         <P>
 *         Description:
 *         <P>
 *         Version:v1.0
 *         <P>
 *         History:
 */
@Controller
@RequestMapping("admin/tokenlogin")
public class SysUserController<T> {

	/**
	 * 用于输出log
	 */
	private static Logger log = Logger.getLogger(SysUserController.class);

	/**
	 * <P>
	 * Function: initLogin
	 * <P>
	 * Description:登陆页面
	 * <P>
	 * Others:
	 * 
	 * @author: lzd
	 * @CreateTime: 2017-05-15 上午11:38:43
	 * @param request 请求
	 * @param userName 用户名
	 * @param passWord 密码
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value = "/initLogin")
	@ResponseBody
	public String initLogin(HttpServletRequest request,String userName,String passWord) throws Exception {
				log.info("Start SysUserController --> initLogin()");        
				//清空session
		        request.getSession().removeAttribute("token");
		        //创建一个key
		        TokenVO uvo = (TokenVO)request.getSession().getAttribute("token");
		        //调用接口查询token并把token存入session
				ApplicationContext ctx = SysContextUtil.getSpringApplicationContext();
				final ITokenUserCenterService service = (ITokenUserCenterService) ctx.getBean( "tokenUserCenterService" );
				List<UserCenterVO> listB = service.getSysRoleByToken(userName,passWord);
				for (int i = 0; i < listB.size(); i++) {
				    //获取的session中的值给跳转之后的页面
					if(uvo == null){
						TokenVO vo = new TokenVO();
						vo.setToken(listB.get(i).getToken());
						//存入token
					    request.getSession().setAttribute("token",vo);
					}
				}
				return "success";
	} 
	/**
	 * <P>
	 * Function: initLogin
	 * <P>
	 * Description:用户中心接收token 通过token查询机构权限
	 * <P>
	 * Others:
	 * 
	 * @author: lzd
	 * @CreateTime: 2017-05-15 下午14:28:13
	 * @param request 请求
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value = "/initPage")
	public String initPage(HttpServletRequest request) throws Exception {
		        log.info("Start SysUserController --> initPage()");
		        //获取token
		        TokenVO uvo = (TokenVO)request.getSession().getAttribute("token");
		        //调用接口查询token并把token存入session
				ApplicationContext ctx = SysContextUtil.getSpringApplicationContext();
				final ITokenUserCenterService service = (ITokenUserCenterService) ctx.getBean( "tokenUserCenterService" );
				List<UserCenterVO> listT = service.getToken(uvo.getToken());
				for (int i = 0; i < listT.size(); i++) {
					//获取
					String sysOrg = listT.get(i).getProvince();
					//存值
					uvo.setSysOrg(sysOrg);
					//获取
					String provinceid = listT.get(i).getProvinceid();
					//存值
					uvo.setProvinceid(provinceid);
					//转换 json 串
					request.getSession().setAttribute("token", uvo);
				}
				return "admin/tokenlogin/adminLogin";
	} 
}
