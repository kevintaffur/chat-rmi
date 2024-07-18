package com.mycompany.chat.rmi;
import java.rmi.RemoteException;

import java.io.Serializable;

public class Chatter implements Serializable {
    
    private String nombre;
    private ChatServiceRemote chatService;
    private ClientCallbackImpl clientCallback;
    private ChatRoomRemote chatRoom;

    private final Flag done = new Flag(false);
         
    private static final long serialVersionUID = 1L;
    private String name;

    public Chatter(String aName) {
        name = aName;
    }

    public String getName() {
        return name;
    }
}
