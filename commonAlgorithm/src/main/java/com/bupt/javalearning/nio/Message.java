package com.bupt.javalearning.nio;

/**
 * Created by hujun on 16/8/12.
 */
public enum Message {
    REGISTER("register");

    private String name;

    Message(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
