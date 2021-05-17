package com.study.corespringsecuritydbconnect.security.voter;

import com.study.corespringsecuritydbconnect.service.SecurityResourceService;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import java.util.Collection;
import java.util.List;

public class IpAddressVoter implements AccessDecisionVoter<Object> {

    private final SecurityResourceService securityResourceService;

    public IpAddressVoter(SecurityResourceService securityResourceService) {
        this.securityResourceService = securityResourceService;
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    @Override
    public int vote(Authentication authentication, Object object, Collection<ConfigAttribute> attributes) {

        WebAuthenticationDetails details = (WebAuthenticationDetails) authentication.getDetails();
        String remoteAddress = details.getRemoteAddress();

        List<String> accessIpList = securityResourceService.getAccessIpList();

        int result = ACCESS_DENIED;

        //ip 허용하는지 체크
        for (String ipAddress : accessIpList) {
            if (remoteAddress.equals(ipAddress)) {
                //허가하면 안되고 ABSTAIN 해야 된다. - 이 이후에 권한을 더 검사하기 때문
                return ACCESS_ABSTAIN;
            }
        }

        if (result == ACCESS_DENIED) {
            throw new AccessDeniedException("Invalid IpAddress");
        }
        return result;
    }
}
