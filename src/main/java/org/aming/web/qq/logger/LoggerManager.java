package org.aming.web.qq.logger;

import com.google.common.collect.Maps;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @author daming
 * @version 2017/9/30.
 */
public class LoggerManager {

    private static Map<String,Logger> encache = Maps.newHashMap();

    public static Logger getLogger(Class<?> loggerClassName){
        return getLogger(loggerClassName.getName());
    }

    public static Logger getLogger(String loggerName){
        if(isNonExistLogger(loggerName)){
            newLogger(loggerName);
        }
        return encache.get(loggerName);
    }

    private static void newLogger(String loggerName){
        encache.put(loggerName,new Logger(LoggerFactory.getLogger(loggerName)));
    }

    private static boolean isExistLogger(String loggerName){
        return encache.containsKey(loggerName);
    }

    private static boolean isNonExistLogger(String loggerName){
        return !isExistLogger(loggerName);
    }
}
