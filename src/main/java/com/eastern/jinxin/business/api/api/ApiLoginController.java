package com.eastern.jinxin.business.api.api;

import java.util.Map;

import com.eastern.jinxin.business.api.annotation.IgnoreAuth;
import com.eastern.jinxin.business.api.service.TokenService;
import com.eastern.jinxin.business.api.service.UserService;
import com.eastern.jinxin.sys.common.common.utils.R;
import com.eastern.jinxin.sys.common.common.validator.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * API登录授权
 *
 * @author looyoo
 * @email service@gmail.com
 * @date 2017-03-23 15:31
 */
@RestController
@RequestMapping("/api")
public class ApiLoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;

    /**
     * 登录
     */
    @IgnoreAuth
    @PostMapping("login")
    public R login(String mobile, String password){
        Assert.isBlank(mobile, "手机号不能为空");
        Assert.isBlank(password, "密码不能为空");

        //用户登录
        long userId = userService.login(mobile, password);

        //生成token
        Map<String, Object> map = tokenService.createToken(userId);

        return R.ok(map);
    }

}
