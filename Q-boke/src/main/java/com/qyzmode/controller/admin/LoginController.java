package com.qyzmode.controller.admin;


import com.qyzmode.prjo.User;
import com.qyzmode.service.UserService;
import com.qyzmode.util.CaptchaCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")//全局请求地址
public class LoginController {

    @Autowired
    private UserService userService;
    private String captcha_code;

    //登录
    @PostMapping("/login")
    public String login(@RequestParam String username, String password,String code,
                              HttpSession session, RedirectAttributes redirectAttributes){

        User user=userService.checkUser(username,password);
        if(user!=null && !CaptchaCodeUtil.verifyCode(captcha_code, code).equals("failed"))
        {
            user.setPassword(null);
            session.setAttribute("user",user);
            return "admin/index";
        }
        else if(CaptchaCodeUtil.verifyCode(captcha_code, code).equals("failed")){

            redirectAttributes.addFlashAttribute("message","验证码错误");
            return "redirect:/admin";
        }
        else {
            redirectAttributes.addFlashAttribute("message","用户名或密码错误");
            return "redirect:/admin";
        }

    }


    @GetMapping("/logout")
    public  String logout(HttpSession session)
    {
        session.removeAttribute("user");
        return "redirect:/admin";
    }

    @RequestMapping("/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response){

        try {
            //生成一个四位数的验证码
            captcha_code= new CaptchaCodeUtil().randomStr(4);
           CaptchaCodeUtil captchaCodeUtil=new CaptchaCodeUtil(120, 38, 4, 10, captcha_code);
            captchaCodeUtil.write(response.getOutputStream());
        }catch (Exception e)
        {

        }

    }
}
