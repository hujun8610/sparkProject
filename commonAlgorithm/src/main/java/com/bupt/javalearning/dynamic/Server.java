package com.bupt.javalearning.dynamic;

/**
 * Created by hujun on 16/10/30.
 */
public class Server implements CalculatorProtocol {
    @Override
    public int add(int a, int b) {
        return a+b;
    }

    @Override
    public int subtract(int a, int b) {
        return a-b;
    }
}
