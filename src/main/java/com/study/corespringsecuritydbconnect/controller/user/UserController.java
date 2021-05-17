package com.study.corespringsecuritydbconnect.controller.user;


import com.study.corespringsecuritydbconnect.domain.dto.AccountDto;
import com.study.corespringsecuritydbconnect.domain.entity.Account;
import com.study.corespringsecuritydbconnect.repository.RoleRepository;
import com.study.corespringsecuritydbconnect.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping(value = "/users")
    public String createUser() throws Exception {

        return "user/login/register";
    }

    @PostMapping(value = "/users")
    public String createUser(AccountDto accountDto) throws Exception {

        ModelMapper modelMapper = new ModelMapper();
        Account account = modelMapper.map(accountDto, Account.class);
        account.setPassword(passwordEncoder.encode(accountDto.getPassword()));

        userService.createUser(account);

        return "redirect:/";
    }

    /**
     * Filter 기반 url 방식은
     * FilterSecurityInterceptor 가 가로채서 인증을 진행하고,
     * <p>
     * AOP 기반 방식은
     * 프록시가 MethodSecurityInterceptor 를 호출해서 인증을 진행한다.
     * <p>
     * Map 객체를 사용하거나, AccessDecisionManager 에 권한을 전달해주는것은 같다.
     */
    @GetMapping(value = "/mypage")
    public String myPage(@AuthenticationPrincipal Account account, Authentication authentication, Principal principal) throws Exception {
        return "user/mypage";
    }

    @GetMapping("/order")
    public String order() {
        userService.order();
        return "user/mypage";
    }
}
