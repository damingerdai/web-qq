package org.aming.web.qq.service;

import org.springframework.security.core.userdetails.UserDetails;

@Deprecated
public interface SecurityContextService{

    UserDetails getCurrentUser();
}
