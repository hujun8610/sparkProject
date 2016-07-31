package com.bupt.rmi.learning;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by hujun on 2015/12/19.
 */
public interface RemoteInterface extends Remote{
    public String sayHello(String s) throws RemoteException;
}
