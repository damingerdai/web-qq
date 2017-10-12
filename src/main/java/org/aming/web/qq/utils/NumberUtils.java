package org.aming.web.qq.utils;

import java.util.function.Predicate;

/**
 * @author daming
 * @version 2017/10/5.
 */
public class NumberUtils {

    /**
     * 判断是否大于零
     * @param i
     * @return
     */
    public static boolean isGreaterThanZero(int i){
        return internalOperating(i,( s ) -> s > 0);
    }

    /**
     * 判断是否等于零
     * @param i
     * @return
     */
    public static boolean equalsZero(int i) {
        return internalOperating(i,(s) -> s == 0);
    }

    /**
     * 判断是否小于零
     * @param i
     * @return
     */
    public static boolean isLessThanZero(int i) {
        return internalOperating(i,(s) -> s < 0);
    }

    /**
     * 内部用于操作
     * @param i
     * @param predicate
     * @return
     */
    private static boolean internalOperating(int i,Predicate<Integer> predicate) {
        return predicate.test(i);
    }
}
