package org.aming.web.qq.service.impl;

import org.aming.web.qq.service.SecurityContextService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * @author aming
 * @version 2017-10-09
 */
@Deprecated
@Component
public class SecurityContextServiceImpl implements SecurityContextService{

    @Override
    public UserDetails getCurrentUser() {
        return (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
