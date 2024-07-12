package com.mycompany.chat.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ClientCallbackImpl extends UnicastRemoteObject implements ClientCallback {
    protected ClientCallbackImpl() throws RemoteException {
        super();
    }

    @Override
    public void receiveMessage(String msg) throws RemoteException {
        System.out.println(msg);
    }
}

