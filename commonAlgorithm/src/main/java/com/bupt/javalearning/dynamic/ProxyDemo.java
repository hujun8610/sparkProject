package com.bupt.javalearning.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Created by hujun on 16/10/30.
 * 动态代理
 * 1.定义一个通用的接口
 * 2.被代理的类实现这个接口中定义的方法。
 * 3.定义一个类实现InvocationHandler接口,完成其构造函数(类中使用一个Object对象存储需要代理的类)
 * 4.实现invoke方法,该方法中使用method方法调用被代理类的方法。
 * 5.主函数中调用Proxy.newProxyInstance方法初始化代理类,完成动态代理。
 *
 */
public class ProxyDemo {
    public static void main(String[] args) {
        CalculatorProtocol server = new Server();
        InvocationHandler handler = new ServerInvocationHandler(server);

        CalculatorProtocol client = (CalculatorProtocol) Proxy.newProxyInstance(server.getClass().getClassLoader(),
                server.getClass().getInterfaces(),handler);

        int addRes = client.add(3,5);
        System.out.println("The add result is " + addRes);
        int subRes = client.subtract(5,3);
        System.out.println("The sub result is " + subRes);

    }
}
