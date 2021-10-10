package com.velog.server.controller;

import com.velog.server.config.security.JwtTokenProvider;
import com.velog.server.domain.entity.User;
import com.velog.server.domain.repository.UserRepository;
import com.velog.server.dto.auth.LoginDTO;
import com.velog.server.dto.auth.SignupDTO;
import com.velog.server.model.response.CommonResult;
import com.velog.server.model.response.SingleResult;
import com.velog.server.service.AuthService;
import com.velog.server.service.ResponseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@Api(tags = {"1. User auth"})
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;
    @Autowired
    private ResponseService responseService;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private UserRepository userRepository;

    @ApiOperation(value = "회원가입", notes = "회원가입을 요청한다.")
    @PostMapping("/signup")
    public CommonResult ResponseSignup(@ApiParam(value = "회원가입 폼 객체", required = true) @RequestBody SignupDTO requestData) {
        System.out.println(Collections.singletonList("APPLE"));
        userRepository.save(User.builder()
                .email(requestData.getEmail())
                .password(requestData.getPassword())
                .salt("salt")
                .roles(Collections.singletonList("ROLE_USER"))
                .build());
        return responseService.getSuccessResult();
    }

    @ApiOperation(value = "로그인", notes = "로그인을 요청한다.")
    @PostMapping("/login")
    public SingleResult<String> ResponseLogin(@ApiParam(value = "로그인 폼 객체", required = true) @RequestBody LoginDTO requestData) {
        User user = authService.login(requestData);
        return responseService.getSingleResult(jwtTokenProvider.createToken(user.getEmail(), user.getRoles()));
    }
}
