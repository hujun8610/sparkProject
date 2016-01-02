package com.bupt.rmi.learning;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * Created by hujun on 2015/12/19.
 */
public class Client {

    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {

        RemoteInterface remoteInterface = (RemoteInterface)Naming.lookup("rmi://localhost:1098/RemoteInterface");
        System.out.println(remoteInterface.sayHello(" rmi"));
    }
}
