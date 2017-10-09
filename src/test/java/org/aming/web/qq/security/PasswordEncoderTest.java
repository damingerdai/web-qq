package org.aming.web.qq.security;

import org.junit.Test;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

import java.net.ServerSocket;

/**
 * @author daming
 * @version 2017/10/1.
 */
public class PasswordEncoderTest {

    @Test
    public void testStandardPasswordEncoder(){
        PasswordEncoder passwordEncoder = new StandardPasswordEncoder("web_qq");
        String password1 = passwordEncoder.encode("12345");
        String password2 = passwordEncoder.encode("mingguobin4");
        System.out.println("password1:"+password1);
        System.out.println("password2:"+password2);
        System.out.println(passwordEncoder.matches("mingguobin4",password1));
    }
}
