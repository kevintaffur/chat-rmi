package com.mycompany.chat.rmi;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatServiceRemote extends Remote {
    void putMessage(String msg) throws RemoteException;
    String getUserName() throws RemoteException;
    String executeCommand(String command, String line) throws IOException, RemoteException;
}

