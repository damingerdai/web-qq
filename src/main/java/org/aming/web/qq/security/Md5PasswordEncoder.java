package org.aming.web.qq.security;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author daming
 * @version 2017/10/1.
 */
@Deprecated
public class Md5PasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence charSequence) {
        return null;
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return false;
    }
}
