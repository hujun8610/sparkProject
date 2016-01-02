package com.bupt.rmi.learning;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * Created by hujun on 2015/12/19.
 */
public class Server {
    public static void main(String[] args) throws RemoteException, AlreadyBoundException, MalformedURLException {
        LocateRegistry.createRegistry(1098);
        Naming.bind("rmi://localhost:1098/RemoteInterface",new RemoteImpl());
    }
}
