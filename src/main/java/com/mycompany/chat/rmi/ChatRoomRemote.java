package com.mycompany.chat.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatRoomRemote extends Remote {
    void register(String aName) throws RemoteException;
    void leave(String aName, ChatServiceRemote service) throws RemoteException;
    void add(ChatServiceRemote cs) throws RemoteException;
    void broadcast(String requestor, String msg, ChatServiceRemote chatService) throws RemoteException;
    void registerClient(ClientCallback client) throws RemoteException;
    void unregisterClient(ClientCallback client) throws RemoteException;
}

