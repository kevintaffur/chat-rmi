package com.mycompany.chat.rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ChatServer {
    public static void main(String[] args) {
        try {
            final int ROOM_SIZE = 10;
            ChatRoom chatRoom = new ChatRoom(ROOM_SIZE);
//            ChatRoomRemote stub = (ChatRoomRemote) UnicastRemoteObject.exportObject(chatRoom, 0);
            ChatRoomRemote stub = (ChatRoomRemote) chatRoom;
            Registry registry = LocateRegistry.createRegistry(8888);
            registry.bind("ChatRoom", stub);
            System.out.println("Servidor de chat listo.");
        } catch (Exception e) {
            System.err.println("Error del servidor de chat: " + e.toString());
            e.printStackTrace();
        }
    }
}

