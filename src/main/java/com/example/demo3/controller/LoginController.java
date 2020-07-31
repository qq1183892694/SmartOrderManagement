package com.example.demo3.controller;

import com.example.demo3.entity.Users;
import com.example.demo3.service.IUsersService;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.omg.CORBA.OBJ_ADAPTER;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.security.Security;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/LoginController")
public class LoginController {
    @Autowired
    IUsersService usersService;

    @Autowired
    DefaultKaptcha defaultKaptcha;



    /**
     * 验证码生成并回显
     * @param httpServletRequest
     * @param httpServletResponse
     * @throws Exception
     */
    @RequestMapping("/getCode")
    public void defaultKaptcha(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception{
        byte[] captchaChallengeAsJpeg = null;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
            //生产验证码字符串并保存到session中
            String createText = defaultKaptcha.createText();
            httpServletRequest.getSession().setAttribute("verifyCode", createText);
            //使用生成的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
            BufferedImage challenge = defaultKaptcha.createImage(createText);
            ImageIO.write(challenge, "jpg", jpegOutputStream);
        } catch (IllegalArgumentException e) {
            httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        //定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
        httpServletResponse.setHeader("Cache-Control", "no-store");
        httpServletResponse.setHeader("Pragma", "no-cache");
        httpServletResponse.setDateHeader("Expires", 0);
        httpServletResponse.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream =
                httpServletResponse.getOutputStream();
        responseOutputStream.write(captchaChallengeAsJpeg);
        responseOutputStream.flush();
        responseOutputStream.close();
    }


    /**
     * 登录请求
     * @param value
     * @param session
     * @return
     */
    @PostMapping("/login")
    public @ResponseBody Map<String,Object> login(@RequestBody Map<String,String> value,HttpSession session)
    {
        Map<String, Object> rt=new HashMap<>();
        String verifycode=value.get("verifyCode");
        String verifyCode= (String) session.getAttribute("verifyCode");
        System.out.println(verifyCode);

        if(!verifycode.equalsIgnoreCase(verifyCode)) {
            rt.put("msg", "验证码错误");
            rt.put("success", false);
            return rt;
        }
        Users user=Users.builder().userPassword(value.get("password")).userLoginName(value.get("loginname")).build();
        System.out.println(user.getUserPassword()+" "+user.getUserLoginName());

        try {
            Subject subject = SecurityUtils.getSubject();
            AuthenticationToken token=new UsernamePasswordToken(user.getUserLoginName(),user.getUserPassword());
            subject.login(token);
            rt.put("path", "/UserController/users");//登录成功后跳转到的页面
            rt.put("success", true);
//            session.setAttribute("user",usersService.findUserByUserName(user.getUserLoginName()));
            session.setAttribute("user",user.getUserLoginName());
        } catch (UnknownAccountException e) {
            rt.put("msg", "用户名错误");
            rt.put("success", false);
        }catch (IncorrectCredentialsException e){
            rt.put("msg", "用户或密码错误");
            rt.put("success", false);
        }catch (Exception e)
        {
            rt.put("msg", "系统错误");
            rt.put("success", false);
        }
        return rt;
    }


//    @RequestMapping("/logout")
//    public  String logout(HttpSession session)
//    {
//        session.invalidate();
//        Subject subject = SecurityUtils.getSubject();
//        subject.logout();
//        return "redirect:/index.html";
//    }

}
