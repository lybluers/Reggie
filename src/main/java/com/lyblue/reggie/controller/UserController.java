package com.lyblue.reggie.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lyblue.reggie.common.R;
import com.lyblue.reggie.entity.User;
import com.lyblue.reggie.service.UserService;
import com.lyblue.reggie.utils.ValidateCodeUtils;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 发送手机验证码
     * @param user
     * @return
     */
    @PostMapping("/sendMsg")
    public R<String> sendMsg(@RequestBody User user, HttpSession session){
        // 获取手机号
        String phone = user.getPhone();
        if(StringUtils.isNotEmpty(phone)){
            // 生成随机4位验证码
            String code = ValidateCodeUtils.generateValidateCode(4).toString();

            log.info("code={}",code);

            // 调用阿里云提供的短信服务API完成短信发送
          //   SMSUtils.sendMessage("瑞吉外卖","",phone,code);
            // 将生成的验证码保存到Session
            session.setAttribute(phone,code);

            // 将生成的验证码缓存到redis当中，并且设置有效期为五分钟
            redisTemplate.opsForValue().set(phone,code,5, TimeUnit.MINUTES);

            return R.success("手机验证码发送成功");
        }

        return R.error("短信发送失败");
    }

    @PostMapping("/login")
    public R<User> login(@RequestBody Map map, HttpSession session){

        // 获取手机号
        String phone = map.get("phone").toString();
        // 获取验证码
        String code = map.get("code").toString();

        // 从Session中获取保存的验证码
        // Object codeInSession = session.getAttribute(phone);

        // 从Redis获取保存的验证码
        Object codeInSession = redisTemplate.opsForValue().get(phone);

        // 进行验证码的比对（Session和页面的验证码）
        if(codeInSession != null && codeInSession.equals(code)){

            // 如果能够对比成功，说明登录成功
            LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();

            // 判断当前手机号是否为新用户，如果是新用户自动完成注册
            queryWrapper.eq(User::getPhone,phone);
            User user = userService.getOne(queryWrapper);

            if (user == null) {

                user = new User();
                user.setPhone(phone);
                user.setStatus(1);

                userService.save(user);
            }
            session.setAttribute("user",user.getId());

            // 如果用户登录成功，删除redis缓存中的验证码
            redisTemplate.delete(phone);
            return R.success(user);
        }

        return R.error("登录失败");
    }

}