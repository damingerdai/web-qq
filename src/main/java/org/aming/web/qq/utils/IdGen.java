package org.aming.web.qq.utils;

import java.security.SecureRandom;
import java.util.UUID;

/**
 * @author daming
 * @version 2017/10/5.
 */
public class IdGen {

    private static final SecureRandom random = new SecureRandom();

    public static String uuid(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
