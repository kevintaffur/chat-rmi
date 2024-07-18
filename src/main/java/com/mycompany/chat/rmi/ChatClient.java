package com.mycompany.chat.rmi;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.swing.JOptionPane;

public class ChatClient {
 
     private ChatServiceRemote chatService;
    private ClientCallbackImpl clientCallback;
    private ChatRoomRemote chatRoom;
    
    
    
         public void connectToServer(String host, int port, ClientCallbackImpl callback) throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(host, port);
        chatRoom = (ChatRoomRemote) registry.lookup("ChatRoom");
        clientCallback = callback;
        chatRoom.registerClient(clientCallback);
        chatService = new ChatService(chatRoom);
    }

    public void login(String nombre) throws RemoteException, IOException {
        if (chatService != null) {
            chatService.executeCommand("LOGIN", nombre);
        }
    }

    public void logout() throws IOException {
        try {
            if (chatService != null) {
                chatService.executeCommand("LOGOUT", "");
            }
            if (chatRoom != null && clientCallback != null) {
                chatRoom.unregisterClient(clientCallback);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String mensaje) throws RemoteException, IOException {
        if (chatService != null) {
            chatService.executeCommand("CHAT", mensaje);
        }
    }
    

}

