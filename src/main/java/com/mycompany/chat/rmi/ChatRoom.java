package com.mycompany.chat.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatRoom extends UnicastRemoteObject implements ChatRoomRemote {
    private List<ChatServiceRemote> activeService;
    private int capacity;
    private Map<String, Chatter> chatterHash;
    private List<ClientCallback> clients;

    public ChatRoom(int aCapacity) throws RemoteException {
        capacity = aCapacity;
        chatterHash = Collections.synchronizedMap(new HashMap<>(capacity));
        activeService = Collections.synchronizedList(new ArrayList<>(capacity));
        clients = Collections.synchronizedList(new ArrayList<>());
    }

    @Override
    public synchronized void registerClient(ClientCallback client) throws RemoteException {
        clients.add(client);
    }

    @Override
    public synchronized void unregisterClient(ClientCallback client) throws RemoteException {
        clients.remove(client);
    }

    @Override
    public synchronized void register(String aName) throws RemoteException {
        chatterHash.put(aName, new Chatter(aName));
    }

    @Override
    public synchronized void leave(String aName, ChatServiceRemote service) throws RemoteException {
        chatterHash.remove(aName);
        activeService.remove(service);
    }

    @Override
    public synchronized void add(ChatServiceRemote cs) throws RemoteException {
        activeService.add(cs);
    }

    @Override
    public synchronized void broadcast(String requestor, String msg, ChatServiceRemote chatService) throws RemoteException {
        for (ChatServiceRemote cs : activeService) {
            if (cs != chatService && cs.getUserName() != null) {
                cs.putMessage(requestor + ": " + msg);
            }
        }
        for (ClientCallback client : clients) {
            client.receiveMessage(requestor + ": " + msg);
        }
    }
}
