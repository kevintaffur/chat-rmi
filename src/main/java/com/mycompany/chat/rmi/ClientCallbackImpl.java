package com.mycompany.chat.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class ClientCallbackImpl extends UnicastRemoteObject implements ClientCallback {
    private JTextArea chatArea;

    public ClientCallbackImpl(JTextArea chatArea) throws RemoteException {
        super();
        this.chatArea = chatArea;
    }

    @Override
    public void receiveMessage(String msg) throws RemoteException {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                chatArea.append(msg + "\n");
            }
        });
    }
}
