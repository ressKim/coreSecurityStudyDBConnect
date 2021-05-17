package com.study.corespringsecuritydbconnect.aopSecurity;

import com.study.corespringsecuritydbconnect.domain.dto.AccountDto;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class AopSecurityController {

    //securityConfig 에서 밑에 어노테이션을 설정 해 줘야 동작을 한다.
    //@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)

    @GetMapping("/preAuthorize")
    @PreAuthorize("hasRole('ROLE_USER') and #account.username == principal.username")
    public String preAuthorize(AccountDto account, Model model, Principal principal){
        model.addAttribute("method", "Success @PreAuthorize");
        return "aop/method";
    }
}
