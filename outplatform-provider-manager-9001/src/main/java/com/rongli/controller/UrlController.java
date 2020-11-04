package com.rongli.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rongli.common.util.captcha.Captcha;
import com.rongli.common.util.captcha.GifCaptcha;
import com.rongli.entities.User;
import com.rongli.service.UserService;
import com.rongli.websocket.UserOnlineMonitor;

@Controller
public class UrlController {
	@Autowired
	private UserService userService;
	
	
	/**
	 * 获取验证码（Gif版本）
	 * @param response
	 */
	@RequestMapping(value="verifycode",method = RequestMethod.GET)
	public void getGifCode(HttpServletResponse response,HttpServletRequest request){
		try {
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			response.setContentType("image/gif");
			/**
			 * gif格式动画验证码
			 * 宽，高，位数。
			 */
			HttpSession session = request.getSession(true);

			Captcha captcha = new GifCaptcha(146,33,4);
			/* 输出 */
			captcha.out(response.getOutputStream());
			String vcodeText = captcha.text().toLowerCase();
			//存入Session
			session.setAttribute("verifycode",vcodeText);
			System.out.println("------"+session.getAttribute("verifycode"));

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("获取验证码异常："+e.getMessage());
		}
	}

	@RequestMapping(value="login",method= RequestMethod.GET)
	public String login() {
		return "login";
	}
	
	@RequestMapping(value="index",method= RequestMethod.GET)
	public String index(Model model) {
		Subject subject = SecurityUtils.getSubject();
		User user=(User) subject.getPrincipal();
		model.addAttribute("menulist", userService.getUserMenuList(user));//添加菜单信息
		return "index";
	}
	
	
	@RequestMapping(value="home" , method= {RequestMethod.POST,RequestMethod.GET})
	public Object index(String account,String password,String orgno,String verifycode,Model model,HttpSession session) {
		if(StringUtils.isEmpty(account)) {
			model.addAttribute("message", "账户不能为空");
			return login();
		}
		if(StringUtils.isEmpty(password)) {
			model.addAttribute("message", "密码不能为空");
			return login();
		}
//		String code= (String) session.getAttribute("verifycode");
//		if(StringUtils.isEmpty(code)) {
//			model.addAttribute("message", "请重新刷新页面");
//			return login();
//		}
//		if(!StringUtils.isEmpty(verifycode)) {
//			if(!StringUtil.compare(code.toLowerCase(), verifycode.toLowerCase())) {
//				model.addAttribute("message", "验证码错误");
//				return login();
//			}
//		}else {
//			model.addAttribute("message", "验证码不能为空");
//			return login();
//		}	
		UsernamePasswordToken usernamePasswordToken=new UsernamePasswordToken(account,password);	
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(usernamePasswordToken);   //完成登录
			User user=(User) subject.getPrincipal();
			session.setAttribute("user", user);
			UserOnlineMonitor.login(user.getUseraccount());
			
			return "redirect:index";
		}catch(UnknownAccountException uae){  
            System.out.println("对用户[" + account + "]进行登录验证..验证未通过,未知账户");  
            model.addAttribute("message", "未知账户");  
            return login();//返回登录页面
        }catch(IncorrectCredentialsException ice){  
        	 System.out.println("对用户[" + account + "]进行登录验证..验证未通过,错误的凭证");  
        	 model.addAttribute("message", "密码不正确");  
            return login();//返回登录页面
        }catch(LockedAccountException lae){  
        	 System.out.println("对用户[" + account + "]进行登录验证..验证未通过,账户已锁定");  
        	 model.addAttribute("message", "账户已锁定");
            return login();//返回登录页面
        }catch(ExcessiveAttemptsException eae){  
        	 System.out.println("对用户[" + account + "]进行登录验证..验证未通过,错误次数过多");  
        	 model.addAttribute("message", "用户名或密码错误次数过多");
            return login();//返回登录页面
        }catch(AuthenticationException ae) {
       	 	System.out.println("对用户[" + account + "]进行登录验证..验证未通过,无有效机构"); 
        	model.addAttribute("message", ae.getMessage());
			return login();
        }

	}

	
	@RequestMapping(value="logout",method= RequestMethod.GET)
	public String logout(HttpSession session,String message,Model model) {
	   session.removeAttribute("user");
	   Subject subject = SecurityUtils.getSubject();
	   subject.logout();
	   if(!StringUtils.isEmpty(message))
		   model.addAttribute("message",message);
	return login();
	}
	
	@RequestMapping(value="error/{path}",method= RequestMethod.GET)
	public String error(@PathVariable String path,String msg,Model model) {
		model.addAttribute("msg", msg);
		return "error/"+path;
	}
	
	@RequestMapping(value="url/{path}",method= RequestMethod.GET)
	public String url(@PathVariable("path") String path) {
		
		return path;
	}
	
	@RequestMapping(value="url/{path}/{path2}",method= RequestMethod.GET)
	public String url(@PathVariable("path") String path,@PathVariable("path2") String path2) {
		return path+"/"+path2;
	}
	
	@RequestMapping(value="url/{path}/{path2}/{path3}",method= RequestMethod.GET)
	public String url(@PathVariable("path") String path,@PathVariable("path2") String path2,@PathVariable("path3") String path3) {
		return path+"/"+path2+"/"+path3;
	}
	

}
