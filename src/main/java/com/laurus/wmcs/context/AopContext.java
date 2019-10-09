package com.laurus.wmcs.context;


import com.laurus.wmcs.result.Result;

/**
 * 保存AOP中处理的结果，并使得后续的程序可以提取出来使用.
 *
 * @author wuzimei
 */
public class AopContext {

    private static ThreadLocal<Result> currentReturn = new ThreadLocal<>();

    public static void setCurrentReturn(Result obj) {
        currentReturn.set(obj);
    }

    public static Result getCurrentReturn() {
        return currentReturn.get();
    }

    public static void clear() {
        currentReturn.remove();
    }
}
