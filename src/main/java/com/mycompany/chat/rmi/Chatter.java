package com.mycompany.chat.rmi;

import java.io.Serializable;

public class Chatter implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;

    public Chatter(String aName) {
        name = aName;
    }

    public String getName() {
        return name;
    }
}
