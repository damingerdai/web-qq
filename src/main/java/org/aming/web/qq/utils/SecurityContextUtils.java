package org.aming.web.qq.utils;

import org.aming.web.qq.exceptions.WebQQException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

/**
 * @author aming
 * @version 2017-10-10
 */
public class SecurityContextUtils {

    /**
     * 获取当前登录的用户信息
     * 仅仅包含用户名和密码
     * @return
     */
    public static UserDetails getCurrentUser() {
        try{
            return Optional
                    .of(
                            (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()
                    ).get();
        } catch (NullPointerException ex){
            throw new WebQQException(5001,"没有发现当前用户",ex);
        } catch (Exception ex){
            throw new WebQQException();
        }
    }
}
