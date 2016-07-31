package com.bupt.rmi.learning;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by hujun on 2015/12/19.
 */
public class RemoteImpl extends UnicastRemoteObject implements RemoteInterface {

    //    构造方法
    public RemoteImpl() throws RemoteException {
        super();
    }

    //  rmi需要调用的方法
    @Override
    public String sayHello(String s) throws RemoteException {
        return "Hello" + s;
    }

    //    普通方法
    public void print(String string) {
        System.out.println(string);
    }

}
