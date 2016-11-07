package com.bupt.javalearning.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by hujun on 16/10/30.
 */
public class ServerInvocationHandler implements InvocationHandler {

    private Object originObj;

    public ServerInvocationHandler(Object originObj) {
        this.originObj = originObj;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before calculate");
        Object result = method.invoke(originObj,args);
        System.out.println("after calculate");

        return result;
    }
}
