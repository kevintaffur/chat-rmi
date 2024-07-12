package com.mycompany.chat.rmi;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ChatClient {
    public static void main(String[] args) {
        try {
            final int PORT = 8888;
            final String HOST = "localhost";

            System.out.println("Bienvenido al chat room!\n");
            System.out.println("Por favor entre su comando.");
            System.out.println("USO:  LOGIN usuario_o_nick");
            System.out.println("      CHAT mensaje");
            System.out.println("      LOGOUT");
            System.out.println("Presione ENTER para enviar su mensaje.\n");

            // Conexión RMI al servidor
            Registry registry = LocateRegistry.getRegistry(HOST, PORT);
            ChatRoomRemote chatRoom = (ChatRoomRemote) registry.lookup("ChatRoom");

            // Crear y exportar el servicio de chat del cliente
            ClientCallbackImpl clientCallback = new ClientCallbackImpl();
            chatRoom.registerClient(clientCallback);

            ChatServiceRemote chatService = new ChatService(chatRoom);

            BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
            final Flag done = new Flag(false);

            while (!done.getFlag()) {
                String line = console.readLine();
                int commandDelimiterPos = line.indexOf(' ');
                if (commandDelimiterPos < 0) commandDelimiterPos = line.length();
                String command = line.substring(0, commandDelimiterPos);
                line = line.substring(commandDelimiterPos).trim();

                if (command.equals("LOGIN")) {
                    chatService.executeCommand("LOGIN", line);
                } else if (command.equals("CHAT")) {
                    chatService.executeCommand("CHAT", line);
                } else if (command.equals("LOGOUT")) {
                    chatService.executeCommand("LOGOUT", "");
                    done.setFlag(true);
                    chatRoom.unregisterClient(clientCallback); // Desregistrar el cliente en el logout
                } else {
                    System.out.println("Comando inválido");
                }
            }
        } catch (Exception e) {
            System.out.println("Error del cliente de chat: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

