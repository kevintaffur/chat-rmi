package com.mycompany.chat.rmi;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ChatService extends UnicastRemoteObject implements ChatServiceRemote {
    private String userName;
    private ChatRoomRemote chatRoom;
    private boolean loggedIn;

    public ChatService(ChatRoomRemote chatRoom) throws RemoteException {
        this.chatRoom = chatRoom;
        this.loggedIn = false;
    }

    @Override
    public void putMessage(String msg) throws RemoteException {
        System.out.println(msg);
    }

    @Override
    public String getUserName() throws RemoteException {
        return userName;
    }

    @Override
    public String executeCommand(String command, String line) throws RemoteException {
        if (command.equals("LOGIN")) {
            userName = line.trim();
            chatRoom.register(userName);
            chatRoom.broadcast(userName, "LOGIN", this);
            loggedIn = true;
            return "Administrador del chat room: Hola, " + userName + ".";
        } else if (!loggedIn) {
            return "Administrador del chat room: Usted debe hacer LOGIN primero";
        } else if (command.equals("CHAT")) {
            String message = line.trim();
            chatRoom.broadcast(userName, message, this);
            return userName + ": " + message;
        } else if (command.equals("LOGOUT")) {
            chatRoom.broadcast(userName, "LOGOUT", this);
            chatRoom.leave(userName, this);
            return "Adios!";
        } else {
            return "Administrador del chat room: Comando inválido";
        }
    }
}

