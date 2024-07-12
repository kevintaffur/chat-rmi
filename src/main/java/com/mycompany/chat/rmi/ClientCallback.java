package com.mycompany.chat.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientCallback extends Remote {
    void receiveMessage(String msg) throws RemoteException;
}
